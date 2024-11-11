import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class EncryptedDelegate(private val secretKey: String) : ReadWriteProperty<Any?, String> {
    private var encryptedValue: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return decrypt(encryptedValue, secretKey)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        encryptedValue = encrypt(value, secretKey)
    }

    private fun encrypt(value: String, key: String): String {
        // Placeholder for encryption logic
        return "$value-encrypted"
    }

    private fun decrypt(value: String, key: String): String {
        // Placeholder for decryption logic
        return value.replace("-encrypted", "")
    }
}

class SecureData {
    var sensitiveInfo: String by EncryptedDelegate("my-secret-key")
}

fun main() {
    val data = SecureData()
    data.sensitiveInfo = "SensitiveData"
    println(data.sensitiveInfo) // Output: SensitiveData (decrypted)
}
