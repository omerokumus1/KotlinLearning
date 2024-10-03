
//* Sealed interface 'Error' has implementations
//* only in the same package and module
sealed interface AnError

//* Sealed class 'IOError' extends 'Error' and
//* is extendable only within the same package
sealed class AnIOError(): AnError

//* Open class 'CustomError' extends 'Error'
//* and can be extended anywhere it's visible
open class CustomError(): AnError


