fun introduce(name:String = "hs", age: Int, phone:String){
    println("my name is $name(age==$age, phone==$phone)")
}

fun testVararg(vararg friends: String){
    println("argument type is ${friends::class.java.typeName}")
    println("my friends => ${friends.contentToString()}")
}

fun <T>testVararg1(vararg friends: T){
    println("argument type is ${friends::class.java.typeName}")
    println("my friends => ${friends.contentToString()}")
}

fun <T>testVararg2(vararg friends: T, groupName:String = ""){
    println("argument type is ${friends::class.java.typeName}")
    println("$groupName, my friends => ${friends.contentToString()}")
}

fun <T>testVararg3(groupName:String = "", vararg friends: T){
    println("argument type is ${friends.javaClass.typeName}")
    println("$groupName, my friends => ${friends.contentToString()}")
}
