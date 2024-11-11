import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ObservableListDelegate<T>(private val list: MutableList<T> = mutableListOf()) :
    ReadOnlyProperty<Any?, MutableList<T>> {
    private var onAdd: ((T) -> Unit)? = null
    private var onRemove: ((T) -> Unit)? = null

    fun onAdd(action: (T) -> Unit) {
        onAdd = action
    }

    fun onRemove(action: (T) -> Unit) {
        onRemove = action
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): MutableList<T> {
        return list.apply {
            add = { item ->
                onAdd?.invoke(item)
                add(item)
            }
            remove = { item ->
                onRemove?.invoke(item)
                remove(item)
            }
        }
    }
}
