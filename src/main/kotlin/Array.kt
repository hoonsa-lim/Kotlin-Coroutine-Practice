fun arrayTest(){
    val array = arrayOf(1,2,4,5,6,)
    println("array == ${array.javaClass.typeName}, ${array.contentToString()}")
    println("indices == ${array.indices.joinToString()}")

    val intArray = intArrayOf(1,2,3,4,5,6,7)
    println("프리머티브 타입 intArray == ${intArray.javaClass.typeName}, ${intArray.contentToString()}")
    println()
    for ((i, num) in intArray.withIndex()) print("index $i($num), ")

}