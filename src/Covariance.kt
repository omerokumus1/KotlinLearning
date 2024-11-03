// ? Covariance (out) example
interface Producer<out T> {
    fun produce(): T
    //! Error: Type parameter T is declared as 'out' but occurs in 'in' position in type T
//    fun consume(item: T)
}

class StringProducer : Producer<String> {
    override fun produce(): String = "Hello, Kotlin"
}

