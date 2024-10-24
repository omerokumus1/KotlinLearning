import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class TimerDelegation
    : ReadOnlyProperty<Any, String> {
    private var millis = 0L
    private var timer = Timer()
    private var isStarted = false

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        if (!isStarted) {
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    millis += 1000
                }
            }, 0, 1000)
            isStarted = true
        }
        return millis.toString()
    }

}

// ! Not working expectedly
class TimerTest {
    val timer by TimerDelegation()

    fun test() {
        println(timer)
    }
}

fun main() {
    TimerTest().test()
    Thread.sleep(5000)
    TimerTest().test()
    Thread.sleep(5000)
    TimerTest().test()
}