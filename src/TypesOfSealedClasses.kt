//* Using sealed class enum-like
sealed class Color {
    data object Red : Color()
    data object Green : Color()
    data object Blue : Color()
}

//* Using sealed class enum-like with constants
sealed class Message(val message: String) {
    data object Success : Message("Success")
    data object Error : Message("Error")
}

//* Using sealed class with constant + properties
sealed class Point(val description: String) {
    data class Point2D(val x: Int, val y: Int) : Point("2D")
    data class Point3D(val x: Int, val y: Int, val z: Int) : Point("3D")

    data class Point4D(
        val x: Int, val y: Int, val z: Int,
        val t: Int, val desc: String
    ) : Point(desc)
}

fun main() {
    println(Color.Red)

    println(Message.Success.message)

}