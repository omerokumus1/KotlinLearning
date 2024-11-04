fun main() {
    // ? Generic Type Constraints in Functions
    // * you need to create a generic function that
    // * accepts a type parameter but wants to cover
    // * the case where no valid value is possible.
    val nothingList: List<Nothing> = emptyList()
    val intList: List<Int> = nothingList


    // ? Exhaustive when Expressions
    val view = when (getListView()) {
        View.ListView -> ViewType.LIST
        View.RecyclerView -> ViewType.LIST
        else -> fail("Unknown view")
    }

    val view2 = when (getListView()) {
        View.ListView -> ViewType.LIST
        View.RecyclerView -> ViewType.LIST
        else -> Unit
    }
}
enum class ViewType {
    LIST,
    GRID,
    PROFILE
}
enum class View(val type: ViewType) {
    ListView(ViewType.LIST),
    RecyclerView(ViewType.LIST),
    GridView(ViewType.GRID),
    ProfileView(ViewType.PROFILE);
}

fun getListView(): View {
    return View.entries.first { it.type == ViewType.LIST }
}

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
fun fail2(message: String) {
    throw IllegalArgumentException(message)
}
