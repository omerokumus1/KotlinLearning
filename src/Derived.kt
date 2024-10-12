class Derived(val base: Base) : Base by base {

    override fun sum(): Int {
        println("overriden sum")
        return base.sum()
    }

    fun sumAndMinus(): Int {
        return sum() + minus()
    }
}

