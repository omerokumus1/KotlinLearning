class BaseImpl(
    private val num: Int,
    private val num2: Int
) : Base {
    override fun sum() = num + num2
    override fun minus() = num - num2
    override fun times() = num * num2
}

