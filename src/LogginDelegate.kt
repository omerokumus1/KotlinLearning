import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LoggingDelegate<T>(private var value: T) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        println("Accessing ${property.name}, value: $value")
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        println("Updating ${property.name} from $value to $newValue")
        value = newValue
    }
}

//* Usage
class User4 {
    var name: String by LoggingDelegate("Unknown")
}

fun main() {
    val user = User4()
    user.name = "Alice" // Logs update
    println(user.name)   // Logs access
}
