import kotlin.properties.Delegates

class Player {
    var score: Int by Delegates.vetoable(0) { _, oldValue, newValue ->
        if (newValue >= 0) {
            true //* Accept the new value
        } else {
            println("Score cannot be negative! Keeping old value: $oldValue")
            false //! Reject the change
        }
    }
}

class Account {
    var balance: Int by Delegates.vetoable(0) { _, oldValue, newValue ->
        if (newValue <= 10000) {
            true // Accept the new balance
        } else {
            println("Balance cannot exceed $10000! Keeping old balance: $oldValue")
            false // Reject the change
        }
    }
}

class Config2 {
    var apiKey: String by Delegates.vetoable("") { _, oldValue, newValue ->
        if (oldValue.isEmpty()) {
            true // Allow first assignment
        } else {
            println("API key is already set and cannot be changed.")
            false // Reject subsequent changes
        }
    }
}

fun main() {
    val player = Player()
    player.score = 10    //* Changes score to 10
    println(player.score) //* Output: 10

    player.score = -5    //* Triggers veto, change is rejected
    println(player.score) //* Output: 10

    val account = Account()
    account.balance = 5000
    println("Balance: ${account.balance}") // Output: Balance: 5000

    account.balance = 15000 // Triggers veto, change is rejected
    println("Balance: ${account.balance}") // Output: Balance: 5000

    val config = Config2()
    config.apiKey = "my-secret-key" // First assignment, allowed
    println("API Key: ${config.apiKey}") // Output: API Key: my-secret-key

    config.apiKey = "new-key" // Triggers veto, change is rejected
    println("API Key: ${config.apiKey}") // Output: API Key: my-secret-key
}
