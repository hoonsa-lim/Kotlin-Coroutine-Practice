class My{
    /**
     * 멀티 스레드 환경에서 상수가 아닌 변수를 companion 에서 사용할 경우 조심.
     * ??? 그러면 static 이 아니라는건가???? 아닌가????
     *
     *
     *
     */
    companion object{
       const val TAG = "My"
    }
}

class My1{
    companion object Ego{}
}


/**
 * companion class 를 사용해서 factory 패턴 구현 가능함.
 *
 */
class Phone private constructor(
    private val model: String
){
    companion object PhoneFactory{
        fun create(model: String) = Phone(model)
    }
}



fun testCompanion(){
    val companion = My.Companion
    println("type == ${companion::class.java.typeName}")
    //type == My$Companion

    My1.Ego     //companion class name 지정

}