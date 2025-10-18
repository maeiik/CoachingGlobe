package ch.coachingglobe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun <T> ViewModel.handleRequest(
    response: ResponseX<T>,
    name: String,
    counter: Int = 2,
    onSuccess: (suspend (T) -> Unit)? = null,
    onSuccessKeepState: ((T) -> Unit)? = null,
    request: suspend () -> T,
) {
    response.setState(StateX.Loading())
    println("DataViewModel::$name loading")
    viewModelScope.launch(Dispatchers.Main) {
        try {
            val result = request()
            if (onSuccess == null) {
                response.setState(StateX.Success(result))
                onSuccessKeepState?.invoke(result)
            } else {
                onSuccess.invoke(result)
                response.setState(null)
            }
            println("DataViewModel::$name success")
        } catch (e: HttpRequestTimeoutException) {
            println("DataViewModel::$name error: $e")
            response.setState(StateX.Error("No connection to server possible"))
            if (counter > 0) {
                handleRequest(
                    response = response,
                    name = name,
                    counter = counter - 1,
                    onSuccess = onSuccess,
                    request = request
                )
            }
        } catch (e: java.net.UnknownHostException) {
            println("DataViewModel::$name error: $e")
            response.setState(StateX.Error("No Internet"))
        } catch (e: NoTransformationFoundException) {
            println("DataViewModel::$name error: $e")
            response.setState(
                StateX.Error(
                    when {
                        (e.message.contains("400")) -> "400 Bad Request"
                        (e.message.contains("401")) -> "401 Unauthorized"
                        (e.message.contains("404")) -> "404 Not Found"
                        (e.message.contains("500")) -> "500 Internal Server Error"
                        else -> "Unknown Error"
                    }
                )
            )
        } catch (e: Exception) {
            println("DataViewModel::$name error: $e")
            println(e::class)
            response.setState(
                StateX.Error(
                    when {
                        e.message?.contains("java.net.UnknownHostException") == true -> "No Internet"
                        else -> "Unknown Exception"
                    }
                )
            )
        }
        println("DataViewModel::$name end")
    }
}


class ResponseX<T>(init: StateX<T>? = null) {

    companion object {
        val allResponses = mutableListOf<ResponseX<Any>>()
        fun resetAll() {
            allResponses.forEach { it.reset() }
        }
    }

    init {
        allResponses.add(this as ResponseX<Any>)
    }

    private val _state: MutableStateFlow<StateX<T>?> = MutableStateFlow(init)
    val state: StateFlow<StateX<T>?> = _state.asStateFlow()

    fun setState(state: StateX<T>?) {
        println("setState: $state")
        _state.value = state
    }

    fun reset() {
        _state.value = null
    }

    fun <U> filter(filter: (T) -> U) = state.map {
        when (it) {
            is StateX.Success -> filter(it.data)
            else -> null
        }
    }


    fun isLoading() = state.value is StateX.Loading

    @Composable
    fun collectAsState() = state.collectAsState(initial = null)
}


sealed class StateX<out T> {
    data class Success<T>(val data: T) : StateX<T>()
    data class Error<T>(val error: String) : StateX<T>()
    class Loading<T> : StateX<T>()
}