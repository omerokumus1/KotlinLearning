import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// * The lifetime of the delegate is tied to the lifetime of another property
class LifetimeDependentDelegate<T, U>(
    obj: T?,
    on: U?
) : ReadWriteProperty<Any?, T?> {

    private var objRef = WeakReference(obj)
    private var onRef = WeakReference(on)

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        //* If either reference is null, return null and reset both references
        if (objRef.get() == null || onRef.get() == null) {
            objRef.clear()
            onRef.clear()
            return null
        }
        return objRef.get()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        //* If value is null, clear both references
        if (value == null) {
            objRef.clear()
            onRef.clear()
        } else {
            //* Only set if `onRef` is not null
            if (onRef.get() != null) {
                objRef = WeakReference(value)
            } else {
                objRef.clear()
            }
        }
    }
}

// * Usage
class Resource {
    var dependentResource: String? by LifetimeDependentDelegate("Resource Data", this)
}

fun main() {
    val resource = Resource()

    // Initial state
    println("Initial dependentResource: ${resource.dependentResource}") // Output: Resource Data

    // Set the resource to null, which should set dependentResource to null as well
    resource.dependentResource = null
    println("After setting to null: ${resource.dependentResource}") // Output: null
}
