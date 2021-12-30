package jp.start.kmm.app.data

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import jp.start.kmm.app.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

@DelicateCoroutinesApi
class ApplicationApi {
    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private val address = Url("https://cors-test.appspot.com/test")

    fun about(callback: (ResponseStatus) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result = client.get<ResponseStatus> {
                url(address.toString())
            }

            callback(result)
        }
    }
}