package jp.start.kmm.app

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}