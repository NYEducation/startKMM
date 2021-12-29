package jp.start.kmm.app.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

@DelicateCoroutinesApi
class ApplicationApi {
    private val client = HttpClient()

    private val address = Url("https://cors-test.appspot.com/test")

    fun about(callback: (String) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result: String = client.get {
                url(address.toString())
            }

            callback(result)
        }
    }
}