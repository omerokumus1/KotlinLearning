sealed class SdkError(val message: String) {
    class NetworkError : SdkError("Network failure")
    class DatabaseError : SdkError("Database cannot be reached")
    class UnknownError : SdkError("An unknown error has occurred")
}

