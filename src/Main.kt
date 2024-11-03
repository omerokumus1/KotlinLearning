fun main() {
    // ? in keyword example
    val consumer = StringConsumer()

    //* Output: Consumed: Hello, Kotlin
    consumer.consume("Hello, Kotlin")

    // ? Supertype Assignment example
    // * If you remove in keyword from Consumer interface,
    // * then below code will not work
    val consumer2: Consumer<String> = CharSequenceConsumer()
    consumer2.consume("" +
            "Hello, Kotlin")

}