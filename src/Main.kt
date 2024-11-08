fun main() {
    //* Create a regular map
    val map = mapOf("a" to 1, "b" to 2)

    //* Create a MapWithDefault, where the default value is -1
    val mapWithDefault = map.withDefault { -1 }

    // ! Can't check because MapWithDefault is a private class
//    println(mapWithDefault is MapWithDefault) // Output: true

    println(mapWithDefault["a"]) // Output: 1
    println(mapWithDefault["c"]) // Output: -1, because "c" is not in the map


    //* Default value is computed based on the key length
    val mapWithComputedDefault = map.withDefault { key -> key.length * 2 }

    //* Via Mutable Map
    val mutableMap = mutableMapOf("x" to 5, "y" to 10)

    //* Create a MutableMapWithDefault
    val mutableMapWithDefault = mutableMap.withDefault { key -> key.length }

    println(mapWithComputedDefault["apple"])  // Output: 10 (existing key)
    println(mapWithComputedDefault["cherry"]) // Output: 12 (default: key length * 2)
    println(mutableMapWithDefault["x"])       // Output: 5 (existing key)
}