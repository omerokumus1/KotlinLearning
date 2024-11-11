import kotlin.properties.Delegates

class User3 {
    var name: String by Delegates.observable("Unknown") { property, oldValue, newValue ->
        println("${property.name} changed from '$oldValue' to '$newValue'")
    }
}

class Temperature {
    var celsius: Int by Delegates.observable(0) { _, oldValue, newValue ->
        if (newValue < -273) {
            println("Temperature can't be below absolute zero!")
        } else {
            println("Temperature changed from $oldValue°C to $newValue°C")
        }
    }
}

class Playlist {
    var songs: MutableList<String> by Delegates.observable(mutableListOf()) { _, old, new ->
        println("Playlist updated. Previous songs: $old, New songs: $new")
    }
}

fun main() {
    val user = User3()
    user.name = "Alice"  // Output: name changed from 'Unknown' to 'Alice'
    user.name = "Bob"    // Output: name changed from 'Alice' to 'Bob'

    val temperature = Temperature()
    temperature.celsius = 25    // Output: Temperature changed from 0°C to 25°C
    temperature.celsius = -280  // Output: Temperature can't be below absolute zero!

    val playlist = Playlist()
    playlist.songs.add("Song A")       // No output (reference hasn't changed)
    playlist.songs = mutableListOf("Song B", "Song C") // Triggers observable

}
