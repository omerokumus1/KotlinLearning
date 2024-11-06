import java.math.BigDecimal
import java.util.*

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

    // ? Commutativity
    println(Point(1, 2) * 3)
    // ! Does not work unless we overload the operator for Int
    3 * Point(1, 2)


    // ? Compound assignment: Ready as soon as you overload binary operators
    var point = Point(0, 0)
    point += Point(2, 2)
    point -= Point(1, 1)
    point *= Point(2, 2)
    point /= Point(1, 1)
    point /= Point(2, 2)
    point *= 2

    // * For each arithmetic operator, there is a corresponding compound assignment operator which all have the “Assign” suffix.
    // * That is, there are plusAssign, minusAssign, timesAssign, divAssign, and remAssign:


    // ? Comparison
    val money1 = Money(
        BigDecimal(100),
        Currency.getInstance("USD")
    )
    val money2 = Money(
        BigDecimal(100),
        Currency.getInstance("USD")
    )
    val result = money1 > money2

    // ? In operator (contains)
    val page = Page<String>()
    println("Hello" in page)

    // ? Getter Indexer
    val newPage = Page<String>()
    val firstPage = page[0]

    val ch1 = newPage[Chapter.ONE]

    val elements = newPage[0, 10]
    val chapters = newPage[Chapter.ONE, Chapter.THREE]

    // ? Setter Indexer
    newPage[0] = "Hello"

    newPage[Chapter.ONE] = "Hello"

    newPage[0..1] = listOf("Hello", "World")

    newPage[Chapter.ONE, Chapter.TWO] = listOf("Hello", "World")


    // ? Invoke operator
    // * Invoke an instance
    val getUserInfo = GetUserInfo()
    val userInfo = getUserInfo(1)

    // * Invoke a companion object
    val fragment = Fragment(mapOf())

    // * Invoke a companion object with lambda
    val userFragment = UserFragment {
        println("UserFragment")
    }


    // ? Iterator operator
    val pageIterator = Page<String>()
    for (element in pageIterator) {
        println(element)
    }

    // ? RangeTo operator
    val range = Number.ZERO..Number.FIVE
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

// ? Commutativity
operator fun Point.times(scale: Int): Point = Point(x * scale, y * scale)
operator fun Int.times(point: Point): Point = point * this

class Money(
    val amount: BigDecimal,
    val currency: Currency
) : Comparable<Money> {

    override fun compareTo(other: Money): Int {
        return amount.compareTo(other.amount)
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Money) return false

        if (amount != other.amount) return false
        if (currency != other.currency) return false

        return true
    }

    // An equals compatible hashcode implementation
    override fun hashCode(): Int {
        return super.hashCode()
    }

}

enum class Chapter {
    ONE, TWO, THREE
}

class Page<T> {
    private val elements = mutableListOf<T>()
    private val chapters = mutableMapOf<Chapter, Int>()

    operator fun iterator() = elements.iterator()

    operator fun set(index: Int, value: T) {
        elements[index] = value
    }

    operator fun set(chapter: Chapter, value: T) {
        val chapterPage = chapters[chapter]
        if (chapterPage != null) {
            elements[chapterPage] = value
        } else {
            elements.add(value)
            chapters[chapter] = elements.size - 1
        }
    }

    operator fun set(range: IntRange, values: List<T>) {
        for ((index, value) in values.withIndex()) {
            elements[range.first + index] = value
        }
    }

    operator fun set(fromChapter: Chapter, toChapter: Chapter, values: List<T>) {
        val from = chapters[fromChapter]!!
        val to = chapters[toChapter]!!
        for ((index, value) in values.withIndex()) {
            elements[from + index] = value
        }
    }


    operator fun get(index: Int): T {
        return elements[index]
    }

    operator fun get(chapter: Chapter) : T {
        val chapterPage = chapters[chapter]
        return elements[chapterPage!!]
    }

    operator fun get(range: IntRange): List<T> {
        return elements.subList(range.first, range.last)
    }

    operator fun get(start: Int, end: Int): List<T> {
        return elements.subList(start, end)
    }

    operator fun get(fromChapter: Chapter, toChapter: Chapter): List<T> {
        val from = chapters[fromChapter]!!
        val to = chapters[toChapter]!!
        return elements.subList(from, to)
    }

    operator fun contains(element: T): Boolean {
        return element in elements
    }
}

class GetUserInfo {
    operator fun invoke(id: Int): String {
        return "User $id"
    }
}

class UserFragment private constructor() {
    companion object {
        operator fun invoke(init: UserFragment.() -> Unit): UserFragment {
            val fragment = UserFragment()
            fragment.init()
            return fragment
        }
    }
}

abstract class Fragment {
    companion object {
        operator fun invoke(args: Map<Any, Any>): Fragment {
            val fragment = object : Fragment() {}
            return fragment
        }
    }
}

enum class Number {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;

    operator fun rangeTo(end: Number): IntRange {
        return ZERO.ordinal..end.ordinal
    }

    operator fun rangeUntil(end: Number): IntRange {
        return ZERO.ordinal..<end.ordinal
    }
}

