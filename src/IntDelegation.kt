import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class IntDelegation :
    ReadOnlyProperty<MyClass, Int> {
    private var readCount = 0
    override fun getValue(
        thisRef: MyClass,
        property: KProperty<*>
    ): Int {
        readCount++
        return 42
    }
}

