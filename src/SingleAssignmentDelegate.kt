import SingleAssignmentDelegate.ReassignmentStrategy
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val car = Car()
    car.model = "Toyota"
    println(car.model) //* Toyota
    car.model = "Honda"
    println(car.model) //* Toyota

    val truck = Truck()
    truck.model = "Ford"
    println(truck.model) //* Ford
    truck.model = "Chevrolet" //! Throws IllegalStateException

}

class Car {
    var model: String by SingleAssignmentDelegate()
}

class Truck {
    var model: String by SingleAssignmentDelegate(ReassignmentStrategy.ThrowException())
}

//* Old versions are on OneNote
class SingleAssignmentDelegate<T>(
    val reassignmentStrategy: ReassignmentStrategy<T> = ReassignmentStrategy.Silent()
) : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    private var isAssigned = false

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!isAssigned) {
            throw IllegalStateException("Property '${property.name}' has not been initialized yet.")
        }
        return value as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (isAssigned) {
            reassignmentStrategy.handleReassignment(thisRef, property, value)
        } else {
            this.value = value
            isAssigned = true
        }
    }

    sealed interface ReassignmentStrategy<T> {
        fun handleReassignment(thisRef: Any?, property: KProperty<*>, value: T)

        class ThrowException<T> : ReassignmentStrategy<T> {
            override fun handleReassignment(thisRef: Any?, property: KProperty<*>, value: T) {
                throw IllegalStateException("Property can only be assigned once.")
            }
        }

        class Silent<T> : ReassignmentStrategy<T> {
            override fun handleReassignment(thisRef: Any?, property: KProperty<*>, value: T) {
                return
            }
        }

        class LogWarning<T> : ReassignmentStrategy<T> {
            override fun handleReassignment(thisRef: Any?, property: KProperty<*>, value: T) {
                println("Property '${property.name}' has already been initialized. Ignoring new value.")
            }
        }
    }
}
