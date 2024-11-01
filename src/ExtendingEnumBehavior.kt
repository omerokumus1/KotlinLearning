// ? Using Interfaces

// * Start by defining the ColorType enum class
enum class ColorType {
    PRIMARY,
    SECONDARY;
}

// * Define the IColor interface to capture the
// * type() and paint() behavior
interface IColor {
    val type: ColorType

    fun paint(): String
}

// * Segregate the primary and secondary colors into
// * different groups of IPrimaryColor and ISecondaryColor
interface IPrimaryColor : IColor {
    override val type: ColorType
        get() = ColorType.PRIMARY
}

interface ISecondaryColor : IColor {
    override val type: ColorType
        get() = ColorType.SECONDARY
}

// * Define the PrimaryColor enum class and define the RED color
enum class PrimaryColor : IPrimaryColor {
    RED {
        override fun paint() = "red"
    };
}

// * Define the SecondaryColor enum class by adding the GREEN color
enum class SecondaryColor : ISecondaryColor {
    GREEN {
        override fun paint() = "green"
    };
}


// ? Using Sealed Interfaces/Classes

// * Define Color sealed class to capture the
// * type() and paint() behavior
sealed interface SColor {

    val type: ColorType

    fun paint(): String

}

enum class SPrimaryColor : SColor {
    RED {
        override val type: ColorType
            get() = ColorType.PRIMARY

        override fun paint() = "red"
    };
}

enum class SSecondaryColor : SColor {
    GREEN {
        override val type: ColorType
            get() = ColorType.SECONDARY

        override fun paint() = "green"
    };
}


// ? Objects to create enum-like structure
data object Red : SColor {
    override val type: ColorType
        get() = ColorType.PRIMARY

    override fun paint() = "red"
}

data object Green : SColor {
    override val type: ColorType
        get() = ColorType.SECONDARY

    override fun paint() = "green"
}

