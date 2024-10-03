//* Sealed class allows sub-classes to have different types & number of properties
//* Enum class allows sub-types to have the same number of properties
sealed class Post
{
    class Status(var text: String) : Post()
    class Image(var url: String, var caption: String) : Post()
    class Video(var url: String, var timeDuration: Int, var encoding: String): Post()
}

enum class Month(val days: Int)
{
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31)
}