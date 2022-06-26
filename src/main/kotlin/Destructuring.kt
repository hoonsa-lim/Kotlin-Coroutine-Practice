data class Destructuring (
    val name: String,
    val age: Int,
    val phone: String,
)

fun testDestructuring(){
    val instance = Destructuring("hs", 20, "010-")
    val (name, age, phone) = instance
    println("name = $name, age = $age, phone = $phone")
}

fun testTriple() {
    val triple = Triple("", 123, 5664)
    triple.first
    triple.second
    triple.third
    println("1 == ${triple.first}, 2 == ${triple.second}, 3 == ${triple.third}")

    val (one, two, three) = triple
    println("1 == ${one}, 2 == ${two}, 3 == ${three}")

    val (_, _, three2) = triple
    println("333 == $three2")

    val (_, second) = triple
    println("222 == $second")
}
