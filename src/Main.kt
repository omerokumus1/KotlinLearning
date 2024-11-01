fun main() {
    println("Hello World!")

    when(getColor()) {
        SPrimaryColor.RED -> TODO()
        SSecondaryColor.GREEN -> TODO()
    }
}

fun getColor(): SColor = SPrimaryColor.RED