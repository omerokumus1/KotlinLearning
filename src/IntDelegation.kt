import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class IntDelegation(private val defValue: Int? = null) :
    ReadOnlyProperty<MyClass, Int> {

    private var _readCount = 0
    val readCount: Int get() = _readCount

    override fun getValue(
        thisRef: MyClass,
        property: KProperty<*>
    ): Int {
        _readCount++
        return defValue ?: 42
    }

    fun resetReadCount() {
        _readCount = 0
    }
}

