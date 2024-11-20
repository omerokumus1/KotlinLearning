fun main() {
    val beverage = Espresso()
    //* No decoration
    println("${beverage.description} $${beverage.cost()}")

    var beverage2: Beverage = HouseBlend()
    //* Double Mocha
    beverage2 = Mocha(beverage2)
    beverage2 = Mocha(beverage2)
    println("${beverage2.description} $${beverage2.cost()}")
}


interface Beverage {
    var description: String

    fun cost(): Double
}

class Espresso : Beverage {
    override var description = "Espresso"

    override fun cost() = 1.99

}

class HouseBlend : Beverage {
    override var description = "House Blend Coffee"

    override fun cost() = 0.89
}

// ? Decorator without Delegation, but with inheritance
//abstract class CondimentDecorator : Beverage {
//    abstract val beverage: Beverage
//}
//
//class Mocha(override val beverage: Beverage) : CondimentDecorator() {
//    override var description = "${beverage.description}, Mocha"
//
//    override fun cost() = beverage.cost() + 0.20
//}

// ? Decorator with Delegation
//abstract class CondimentDecorator(
//    val beverage: Beverage
//) : Beverage by beverage
//
//class Mocha(beverage: Beverage) : CondimentDecorator(beverage) {
//    init {
//        description = "${beverage.description}, Mocha"
//    }
//
//    override fun cost() = beverage.cost() + 0.20
//}

//* CondimentDecorator removed
class Mocha(val beverage: Beverage) : Beverage by beverage {
    init {
        description = "${beverage.description}, Mocha"
    }

    override fun cost() = beverage.cost() + 0.20
}