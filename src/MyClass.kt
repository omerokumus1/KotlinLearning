class MyClass {
    val myNumber: Int by IntDelegation(23)

    fun test() {
        println(myNumber)
    }

}

//class NewClass {
//    // ! Error
//    val myNumber: Int by IntDelegation(23)
//}