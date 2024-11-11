
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


//* A lazy value that expires after a given time period.
class ExpiringLazy<T>(
    private val expirationMillis: Long,
    initializer: () -> T
) : ReadWriteProperty<Any?, T> {
    private var lastAccessTime: Long = 0
    private var value: T? = null
    private val initializer = initializer

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val currentTime = System.currentTimeMillis()
        if (value == null || currentTime - lastAccessTime > expirationMillis) {
            value = initializer()
            lastAccessTime = currentTime
        }
        return value!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        lastAccessTime = System.currentTimeMillis()
    }
}

fun <T> expiringLazy(expirationMillis: Long, initializer: () -> T): ExpiringLazy<T> {
    return ExpiringLazy(expirationMillis, initializer)
}

//* Usage
class Cache {
    var data: String by expiringLazy(2000) { "Loaded at ${System.currentTimeMillis()}" }
}

fun main() {
    val cache = Cache()
    println(cache.data) // Initialized first time
    Thread.sleep(2500) // Wait past expiration
    println(cache.data) // Reinitialized after expiration
}
