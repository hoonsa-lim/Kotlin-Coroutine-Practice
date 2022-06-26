fun testGeneric(){
    val list = listOf(Apple(), Orange())
    val array = arrayOf<Any>(Apple(), Orange())
    generic(Apple())

    printArraySize(array)
    printArraySize2(array)
}

fun printArraySize(data: Array<*>){  //스타 프로젝션은 타입을 알 필요가 없을 때 사용함. 함수 내에서는 어레이 정보만 사용하고 어레이 내의 타입은 알필요 없음.
    println("array size is ${data.size}")
    println("array generic type == ${data[0]?.javaClass}")
    //data[0] = data[1]       //타입 모르기 때문.


    val d = data[0]
    if (d is Apple) {
        d.name = "watermelon"
        println(d.name)
    }
}

fun printArraySize2(data: Array<Any>){  //스타 프로젝션과 다르게 제네릭으로 Any 타입을 받을 경우. 스타프로젝션 처럼 모든 타입을 받을 수 있지만, 스타프로젝션은 읽기 전용으로 사용, 값 변경은 불가.
    println("array size is ${data.size}")
    println("array generic type == ${data[0]?.javaClass}")
    data[0] = data[1]       //이건 Any 타입이기 때문에 가능함.


    val d = data[0]
    if (d is Apple) {
        d.name = "banana"
        println(d.name)
    }
}

fun <T: Fruit>generic(data: T){
    println("data == ${data.javaClass.typeName}")
}
fun <T>generic(data: T){
    println("data == ${data!!::class.java.typeName}")
}
inline fun <reified T>genericInline(data: T){       //T 제네릭 타입이 컴파일 타임에 결정되서 런타임 중에는 타입을 알 수 없음. 하지만 reified 를 사용하면 런 타입 중에 타입을 알 수 있음.
    println("data == ${data!!::class.java.typeName}")
}

open class Fruit(
    var name: String,
    val color: String
)

class Apple: Fruit("apple", "ffffff")
class Orange: Fruit("orange", "ffffff")