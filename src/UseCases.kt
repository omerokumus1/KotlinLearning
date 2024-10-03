
//* Representing State with Sealed Classes
sealed class UIState {
    data object Loading : UIState()
    data class Success(val data: String) : UIState()
    data class Error(val exception: Exception) : UIState()
}

fun updateUI(state: UIState) {
    when (state) {
        is UIState.Loading -> showLoadingIndicator()
        is UIState.Success -> showData(state.data)
        is UIState.Error -> showError(state.exception)
    }
}

fun showError(exception: Exception) {
    TODO("Not yet implemented")
}

fun showData(data: String) {
    TODO("Not yet implemented")
}

fun showLoadingIndicator() {
    TODO("Not yet implemented")
}


//* Handling Different Types for an Action
sealed class Payment {
    data class CreditCard(val number: String, val expiryDate: String) : Payment()
    data class PayPal(val email: String) : Payment()
    data object Cash : Payment()
}

fun processPayment(payment: Payment) {
    when (payment) {
        is Payment.CreditCard -> processCreditCardPayment(payment.number, payment.expiryDate)
        is Payment.PayPal -> processPayPalPayment(payment.email)
        is Payment.Cash -> processCashPayment()
    }
}

fun processCashPayment() {
    TODO("Not yet implemented")
}

fun processPayPalPayment(email: String) {
    TODO("Not yet implemented")
}

fun processCreditCardPayment(number: String, expiryDate: String) {
    TODO("Not yet implemented")
}
