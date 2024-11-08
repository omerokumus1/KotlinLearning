import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CachedDelegate<T>(private val initializer: () -> T)
    : ReadWriteProperty<Any?, T> {
    private var cachedValue: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (cachedValue == null) {
            cachedValue = initializer()
            println("${property.name} initialized with $cachedValue")
        }
        return cachedValue!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        cachedValue = value
        if (value == null) {
            println("${property.name} manually set to null (invalidated)")
        } else {
            println("${property.name} manually set to $cachedValue")
        }
    }

}

class DataFetcher {
    var expensiveData: String? by CachedDelegate {
        println("Computing expensive data...")
        "Fetched Data"
    }
}

fun main() {
    val fetcher = DataFetcher()
    println(fetcher.expensiveData)  // Computes and caches
    println(fetcher.expensiveData)  // Uses cached value

}
