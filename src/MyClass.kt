class MyClass {
    val intDelegation = IntDelegation(23)
    val myNumber: Int by intDelegation

//    val myNumber: Int by IntDelegation(23)

    fun test() {
        println(myNumber)
        println(intDelegation.readCount)
        intDelegation.resetReadCount()
    }

}

//class NewClass {
//    // ! Error
//    val myNumber: Int by IntDelegation(23)
//}
