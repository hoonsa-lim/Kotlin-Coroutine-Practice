fun testRange(){
    val oneToTen = 1..10
    println("oneToTen == $oneToTen")

    val aToZ = 'a'..'z'
    println("aToZ == $aToZ, ${aToZ.joinToString()}")

    val ㄱToㅎ = 'ㄱ'..'ㅎ' //한글은 잘 안 됨.
    println("ㄱToㅎ == $ㄱToㅎ, ${ㄱToㅎ.joinToString()}")
}

fun testFor() {
    for (i in 0..10) print("$i, ")
    println()
    for (i in 0 until 10) print("$i, ")
    println()
    for (i in 0.until(10)) print("$i, ")
    println()
    for (i in 0 until 10 step 2) print("$i, ")
    println()
    for (i in 0..10 step 2) print("$i, ")
    println()

}

fun testForDown(){
    for (i in 10 downTo 0) print("$i, ")
    println()
    for (i in 10.downTo(0)) print("$i, ")
    println()
    for (i in 10 downTo 0 step 3) print("$i, ")
}