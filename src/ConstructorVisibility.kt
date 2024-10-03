sealed class Http_Method {

    class Get(val path: String) : HttpMethod()

//    class Post private constructor(val path: String, val body: String) : HttpMethod() {
//        companion object {
//            private var _instance: Post? = null
//            val instance = _instance
//                ?: Post("path", "body").also { _instance = it }
//
//            val threadSafeInstance = synchronized(this) {
//                _instance ?: Post("path", "body").also { _instance = it }
//            }
//
//            val isDestroyed = _instance == null
//
//            fun destroy() {
//                _instance = null
//            }
//        }
//    }

    data object Post : HttpMethod() {
        val path = "path"
        val body = "body"
    }

    class Put(val path: String) : HttpMethod()

    data object Delete : HttpMethod()
}