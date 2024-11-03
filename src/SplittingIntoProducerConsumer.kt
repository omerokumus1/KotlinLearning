//* Since Storage both consumes and produces T, it cannot use
//* in or out because it needs T in both input and output positions.
//* This makes it invariant, meaning you cannot assign
//* a Storage<Dog> to a Storage<Animal> or vice versa.
interface Storage<T> {
    fun store(item: T)    // Consumes T
    fun retrieve(): T     // Produces T
}

// * Solution: Separate into Producer and Consumer Interfaces
interface TProducer<out T> {
    fun produce(): T
}

interface TConsumer<in T> {
    fun consume(item: T)
}

open class TAnimal
class TDog : Animal()
class Cat : Animal()

class DogProducer : Producer<Dog> {
    override fun produce(): Dog = Dog()
}

class TAnimalConsumer : Consumer<Animal> {
    override fun consume(item: Animal) {
        println("Consumed an animal: ${item::class.simpleName}")
    }
}

fun main() {
    //* Covariant Assignment with Producer
    val dogProducer: Producer<Dog> = DogProducer()

    // Covariant assignment: Producer<Dog> can be assigned to Producer<Animal>
    val animalProducer: Producer<Animal> = dogProducer
    val animal: Animal = animalProducer.produce()  // Safe, returns an Animal

    println("Produced an animal: ${animal::class.simpleName}")

    // * Contravariant Assignment with Consumer
    val animalConsumer: Consumer<Animal> = AnimalConsumer()

    // Contravariant assignment: Consumer<Animal> can be assigned to Consumer<Dog>
    val dogConsumer: Consumer<Dog> = animalConsumer
    dogConsumer.consume(Dog())  // Safe, consumes a Dog

    // Output: Consumed an animal: Dog
}

