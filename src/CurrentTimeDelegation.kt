import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CurrentTimeDelegation
    : ReadOnlyProperty<Any, String> {
    override fun getValue(
        thisRef: Any, property:
        KProperty<*>
    ): String {
        return System.currentTimeMillis().toString()
    }

}

