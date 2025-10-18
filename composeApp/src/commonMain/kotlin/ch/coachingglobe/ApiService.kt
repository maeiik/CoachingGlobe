package ch.coachingglobe


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class ApiService() {

    companion object {
        private const val API_KEY = "mQHG4eaiVC1IBAXpwZyIjB0q0WjfFwhpxQyxDmhyOvZ9WbfGvsygG9st"
        private const val IMAGES_URL = "https://api.pexels.com/v1/search"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 2_000
        }

//        install(Logging) {
//            logger = Logger.DEFAULT
//            level = LogLevel.ALL
//        }
    }



    suspend fun secretSanta(): Success =
        client.post("${getURL()}/subject") {
            createAuthorisedRequest(request = SecretSantaRequest(option), settings = settings)
        }.body()


    suspend fun downloadImage(url: String): ByteArray =
        client.get(url).body()
}

fun getURL() = "10.134.71.227:8080"