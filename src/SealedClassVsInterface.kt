//* If sealed class has no property, then use sealed interface
sealed class HttpMethod {

    class Get(val path: String) : HttpMethod()

    class Post(val path: String, val body: String) : HttpMethod()

    class Put(val path: String): HttpMethod()

    data object Delete : HttpMethod()
}

sealed interface HTTPMethod {
    class Get(val path: String) : HttpMethod()

    class Post(val path: String, val body: String) : HttpMethod()

    class Put(val path: String): HttpMethod()

    data object Delete : HttpMethod()
}