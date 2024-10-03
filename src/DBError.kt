sealed class DBError {
    //* A sealed class constructor has protected visibility by default.
    //* It's visible inside this class and its subclasses
    constructor() { /*...*/ }

    //* Private constructor, visible inside this class only.
    //* Using a private constructor in a sealed class allows for even stricter
    //* control over instantiation, enabling specific initialization
    //* procedures within the class.
    private constructor(description: String): this() { /*...*/ }

    //* This will raise an error because public and
    //* internal constructors are not allowed in sealed classes
    //! public constructor(code: Int): this() {}
}

open class Err protected constructor(value: Int) {
    companion object {
        fun create(): Err {
            return Err(2)
        }
    }
}