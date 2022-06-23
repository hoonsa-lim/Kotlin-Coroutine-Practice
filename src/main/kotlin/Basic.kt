import kotlinx.coroutines.*

//https://kotlinlang.org/docs/coroutines-basics.html#your-first-coroutine
/**
 * 질문
 * 1. runBlocking 의 이름은 왜 blocking 이 들어갔지? 뭘 중단하나??
 * 2. 코루틴 실행방법이 다양한 것 같다. 어떤 차이가 있을까?
 *   - runBlocking
 *   - GlobalScope
 *   - CoroutineScope
 *   - launch: 신규 코루틴을 생성
 *   - async
 *
 * 3. coroutineScope 소문자로 시작하는 이건 뭐지?
 * 4. 코루틴에서 scope 란 무엇을 의미하는 것일까?
 *
 */





/**
 * runBlocking:  coroutine 스코프를 실행하는? 만드는? 함수
 * launch: 신규 코루틴을 만드는 함수. 코루틴 빌더
 *
 * 결과: launch 내부 코드가 delay 에 의해서 나중에 실행 됨.
 */
fun basic1() = runBlocking {
    this.launch {
        delay(1000L)
        println("created by launch! == ${this.hashCode()}")
    }

    println("created by runBlocking! == ${this.hashCode()}")
}

/**
 * basic1() = runBlocking { 와 동일하지만
 * inline 형식으로 작성된 코드를 풀어 사용한 것.
 *
 * 근대 왜 블로킹이지?
 *
 */
fun basic1Transform() {
    runBlocking {
        this.launch {
            delay(1000L)
            println("created by launch! == ${this.hashCode()}")
        }

        println("created by runBlocking! == ${this.hashCode()}")
    }
}


/**
 * 이건 내부 내용 실행되지 않음.
 *
 * 코루틴 실행 시 runBlocking 사용 예제와 GlobalScope 또는 CoroutineScope 를 사용하는 것을 봤던 것 같은데
 * 어떤 차이가 있을까???????
 */
fun basic1Transform1() {
    GlobalScope.launch {
        this.launch {
            delay(1000L)
            println("created by launch! == ${this.hashCode()}")
        }

        println("created by GlobalScope! == ${this.hashCode()}")
    }
}

/**
 * delay 함수는 코루틴 내에서 사용가능한 함수다.
 * 하지만 일반 함수에서는 deleay 함수를 사용할 수 없지만,
 * suspend 예약어를 사용할 경우 코루틴이 필요한 함수를 실행할 수 있다.
 *
 * Dart 언어에서 Future 함수를 사용하기 위해서는 future.then 을 사용하거나,
 * 함수명 뒤에 async 를 붙여주는 것과 동일한 컨셉같다.
 */
suspend fun suspendFunction() {
    delay(1000L)
    println("World!")
}

fun basic2() = runBlocking { // this: CoroutineScope
    launch { suspendFunction() }
    println("Hello")
}


/**
 * coroutineScope vs runBlocking
 *
 * - 공통: 본체와 자식이 완료될 때까지 기다림
 *
 * - 차이
 *     - runBlocking: 대기를 위해 현재 쓰레드를 차단
 *     - coroutineScope: 일시 중단하여 기본 스레드를 해제
 */
fun basic3() = runBlocking {
    doWorld()
}

suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World! == $this")
    }
    println("Hello == $this")
}