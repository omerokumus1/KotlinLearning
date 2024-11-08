import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// * Map Delegation
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

// * Mutable Map Delegation
class MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}

class Config(map: Map<String, Any?>) {
    val host: String by map
    val port: Int by map
    //* Default value
    val protocol: String by map.withDefault { "http" }
}

fun main() {
    val user = User(
        mapOf(
            "name" to "John Doe",
            "age" to 25
        )
    )
}

