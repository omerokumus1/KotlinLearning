import kotlin.random.Random

fun example() {
    val value by lazy {
        println("Computing the value...")
        "Hello, World!"
    }
    println(value) // Accesses and triggers lazy initialization
    println(value) // Uses cached value
}

fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    if (memoizedFoo.isValid()) { // Accesses and triggers lazy initialization
        memoizedFoo.doSomething() // Uses cached value
    }
}

class Foo {
    fun isValid(): Boolean = true
    fun doSomething() {
        println("Doing something...")
    }
}



fun fetchUserNames(onSuccess: (List<String>) -> Unit, onError: (Throwable) -> Unit) {
    val result by lazy {
        try {
            // Fetch user names from the network
            listOf("Alice", "Bob", "Charlie")
        } catch (e: Throwable) {
            onError(e)
            throw e
        }
    }
    onSuccess(result) // Accesses and triggers lazy initialization
}