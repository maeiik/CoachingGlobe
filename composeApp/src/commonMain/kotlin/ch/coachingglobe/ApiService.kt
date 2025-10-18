package ch.coachingglobe


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class ApiService() {

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

    suspend fun loadData(): DataDto =
        client.get("${getURL()}/data") {
            contentType(ContentType.Application.Json)
        }.body()

    suspend fun downloadImage(url: String): ByteArray =
        client.get(url).body()
}

fun getURL(): String {
    return "http://10.134.71.227:8080"
    return "http://192.168.60.58:8080"
}
