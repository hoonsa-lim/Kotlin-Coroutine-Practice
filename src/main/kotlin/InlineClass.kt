//컴파일 타임에 inline class 가 primitive 타입으로 변환.
//inline class 를 매개변수로 받는 함수에서는 타입 체크까지 해줌.

//질문: 그러면 inline class 는 멤버로 가질 수 있는 primitive 멤버가 하나만 가능한가???, 왜냐면, 컴파일 중에 바뀐다고 하니까 ===> 0 하나만 가질 수 있음.
//근데 함수는 어떻게 가지고 있는거지??????


/**
 * 문자열로 관리하던 전화번호를 예로 들었을 때.
 *
 * 장점
 * 1. 문자열로 되어 있는 전화번호를 사용하는 함수에 전화번호가 아닌 이름, 나이 등의 정보가 들어가 오류가 발생할 수 있는 상황을 방지 할 수 있다.
 * 2. 그렇다고 일반 class 로 PhoneNumber 객체를 생성하여 사용하면, 객체 생성 및 메모리 사용에 대한 오버해드 발생.
 * 3.
 */

interface NameChecker{
    fun checkFirstName(name: Name)
    fun checkName(name: Name)
}

/**
 * Inline class 는 함수를 가지고 있을 수 있기 때문에.
 * inline class 에 interface 구현이 가능함.
 *
 * class 상속은 할 수 없음.
 */
inline class Name(
    val rawValue: String,
): NameChecker {
    fun introduce(){
        println("hello my name is $rawValue")
    }

    override fun checkFirstName(name: Name) {
        println("checkFirstName: $name")
    }

    override fun checkName(name: Name) {
        println("checkName: $name")
    }
}


inline class PhoneNumber(val rawValue: String)
inline class DeviceId(val rawValue: String)

fun testInlineClass(){
    canCall(PhoneNumber("010"))
}

fun canCall(phone: PhoneNumber) = false