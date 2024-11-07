import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class IntDelegation(private val defValue: Int? = null) :
    ReadOnlyProperty<MyClass, Int> {
    private var readCount = 0
    override fun getValue(
        thisRef: MyClass,
        property: KProperty<*>
    ): Int {
        readCount++
        return defValue ?: 42
    }
}

