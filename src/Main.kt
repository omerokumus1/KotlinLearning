fun main() {
    NewError().printError()
    println(NewError() is DBError)
    when (getError()) {
        is NewError -> println("New Error")
        is AnotherError -> TODO()
        is OtherError -> TODO()
    }

    Http_Method.Post

}

fun getError(): DBError {
    return NewError()
}

class NewError : DBError() {
    fun printError() {
        println("New Error")
    }
}

class AnotherError : DBError() {
    fun printError() {
        println("Another Error")
    }
}

class OtherError: DBError() {
    fun printError() {
        println("Other Error")
    }
}

class Cls: Err(2) {

}