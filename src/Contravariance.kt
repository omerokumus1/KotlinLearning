// ? Contravariance (in) example
interface Consumer<in T> {
    fun consume(item: T)
    //! Error: Type parameter T is declared as 'in' but occurs in 'out' position in type T
    //  fun get(): T
}

class StringConsumer : Consumer<String> {
    override fun consume(item: String) {
        println("Consumed: $item")
    }
}

class CharSequenceConsumer : Consumer<CharSequence> {
    override fun consume(item: CharSequence) {
        println("Consumed: $item")
    }
}