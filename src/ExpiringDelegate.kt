import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//* A property that is expired after a given time period.
class ExpiringDelegate<T>(
    private val expirationMillis: Long,
    private var value: T? = null,
    private val defaultValue: T? = null
) : ReadWriteProperty<Any?, T?> {
    // * Or you can use a Timer to schedule the expiration
    private var lastAccessTime: Long = 0

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastAccessTime > expirationMillis) {
            lastAccessTime = currentTime
            value = defaultValue // * Expired, set to default
        }
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
        lastAccessTime = System.currentTimeMillis()
    }
}

//* Usage
class Storage {
    var data: String? by ExpiringDelegate(1000, "Hello")
}