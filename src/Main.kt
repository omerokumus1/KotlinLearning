fun main() {
    NewError().printError()
    println(NewError() is DBError)
    when (getError()) {
        is NewError -> println("New Error")
        is AnotherError -> TODO()
        is OtherError -> TODO()
    }

    Http_Method.Post

    //* Iterate throuhg all the values of the sealed class
    for (value in UserEvent::class.nestedClasses) {
        println(value)
    }

}

fun getError(): DBError {
    return NewError()
}

class NewError : DBError() {
    fun printError() {
        println("New Error")
    }
}

class AnotherError : DBError() {
    fun printError() {
        println("Another Error")
    }
}

class OtherError : DBError() {
    fun printError() {
        println("Other Error")
    }
}

class Cls : Err(2) {

}

// * Sealed class as Enum but without nullability
// * and with additional/different properties
sealed class UserEvent {
    data object OnClick : UserEvent() {
        val x = 0
        val y = 0
    }

    data object OnLongClick : UserEvent() {
        val duration = 1000
    }

    data object OnSwipe : UserEvent() {
        val direction = "left"
    }
}

sealed class AddVehicleFormRow(val submitKey: String, val type: RowType) {
    data class VehicleName(val queryMapKey: String) : AddVehicleFormRow("VehicleName", RowType.Input)
    data class VehiclePlate(val queryMapCityKey: String, val queryMapPlateKey: String, val queryMapNumberKey: String) :
        AddVehicleFormRow("VehiclePlate", RowType.PlateInput)

    data class VehicleType(val queryMapKey: String) : AddVehicleFormRow("VehicleType", RowType.Picker)
    data class VehicleBrand(val queryMapKey: String) : AddVehicleFormRow("VehicleBrand", RowType.Picker)
    data class VehicleModel(val queryMapKey: String) : AddVehicleFormRow("VehicleModel", RowType.Picker)
    data class VehicleYear(val queryMapKey: String) : AddVehicleFormRow("VehicleYear", RowType.Picker)
    data class AdditionalInfoSwitch(val queryMapKey: String) : AddVehicleFormRow("AdditionalInfoSwitch", RowType.Switch)
    data class KaskoValidityDate(val queryMapKey: String) : AddVehicleFormRow("KaskoValidityDate", RowType.DatePicker)
    data object ConfirmButton : AddVehicleFormRow("ConfirmButton", RowType.Button)
    data object DeleteButton : AddVehicleFormRow("DeleteButton", RowType.Button)
}

enum class RowType {
    Input, PlateInput, Picker, Switch, DatePicker, Button
}