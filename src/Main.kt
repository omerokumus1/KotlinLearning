fun main() {
    // ! in keyword example
    val consumer = StringConsumer()

    //* Output: Consumed: Hello, Kotlin
    consumer.consume("Hello, Kotlin")

    // ? Supertype Assignment example
    // * If you remove in keyword from Consumer interface,
    // * then below code will not work
    val consumer2: Consumer<String> = CharSequenceConsumer()
    consumer2.consume("" +
            "Hello, Kotlin")

    // ! out keyword example
    val producer = StringProducer()
    println(producer.produce())

    // ? Subtype Assignment example
    // * If you remove out keyword from Producer interface,
    // * then below code will not work
    val producer2: Producer<CharSequence> = StringProducer()
    println(producer2.produce())


    // ! Covariance (out) Use Cases

    // ? Read-Only Collections
    // * List is covariant: List<out T>
    fun printAnimals(animals: List<Animal>) {
        animals.forEach { println(it) }
    }
    val dogs: List<Dog> = listOf(Dog(), Dog())
    printAnimals(dogs)


    // Contravariance (in) Use Cases

    // ? Programming to supertype example
    val cs: CharSequence = "Hello, Kotlin"

    val genericCS: Producer<CharSequence> = StringProducer()

    // ? Functions with Flexible Input Parameters
    fun printDetails(items: Consumer<Dog>) {
        items.consume(Dog())
    }
    val animalConsumer = AnimalConsumer()
    printDetails(animalConsumer)

}

open class Animal
class Dog : Animal()

class AnimalConsumer : Consumer<Animal> {
    override fun consume(item: Animal) {}
}