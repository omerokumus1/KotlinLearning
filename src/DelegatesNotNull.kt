import kotlin.properties.Delegates

class User2 {
    var id: Int by Delegates.notNull()  // `Int` is allowed with `notNull`

    fun initializeId(value: Int) {
        id = value
    }

    fun printId() {
        println("User ID: $id")
    }
}

fun main() {
    val user = User2()
    user.initializeId(123) // Set value before access
    user.printId() // Output: User ID: 123
    user.initializeId(234) // Set value before access
    user.printId() // Output: User ID: 123

}
