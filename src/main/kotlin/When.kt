fun testWhen(){
    println("100 == ${grade(100)}, 77 == ${grade(71)}, 50 == ${grade(50)}, 49 == ${grade(49)}")
}

fun grade(score: Int) = when{
    score > 90 -> "A"
    score in 71..90 -> "B"
    score in 50..70 -> "C"
    else -> "F"
}

fun grade2(score: Int) = when(score){
    in 90..100 -> "A"
    in 71..90 -> "B"
    in 50..70 -> "C"
    else -> "F"
}