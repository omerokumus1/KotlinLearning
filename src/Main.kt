fun main() {
    val p1 = Point(0, 1)
    val p2 = Point(1, 2)

    // ? Add two Point objects
    println(p1 + p2)

    println(p1 - p2)
    println(p1 * p2)
    println(p1 / p2)
    println(p1 % p2)

    // ? Unary plus operator
    val s = Shape().apply {
        +Point(0, 0)
        +Point(1, 1)
        +Point(2, 2)
        +Point(3, 4)
    }
    println(s)

    val s1 = shape {
        +Point(0, 0)
        +Point(1, 1)
        +Point(2, 2)
        +Point(3, 4)
    }

}

data class Point(val x: Int, val y: Int)

// ? Overload the + operator
operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)

// ? Other binary operators
operator fun Point.minus(other: Point): Point = Point(x - other.x, y - other.y)
operator fun Point.times(other: Point): Point = Point(x * other.x, y * other.y)
operator fun Point.div(other: Point): Point = Point(x / other.x, y / other.y)
operator fun Point.rem(other: Point): Point = Point(x % other.x, y % other.y)

// ? Overload unary plus operator (The same applies to unary minus, increment, decrement, and not operators)
class Shape {
    private val points = mutableListOf<Point>()

    operator fun Point.unaryPlus() {
        points.add(this)
    }
}

// * Shape DSL
fun shape(init: Shape.() -> Unit): Shape {
    val shape = Shape()
    shape.init()

    return shape
}