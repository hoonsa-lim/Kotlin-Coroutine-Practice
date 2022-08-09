import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//coroutineScope: coroutine scope를 생성하는 방법
// - runBlocking 과 차이점
// 1. coroutineScope 호출하는 함수는 suspend 함수가 되어야함.

//kotlin 에서의 coroutine은 계층적, 구조적
// - 부모 코루틴은 자식 코루틴이 끝날 때까지 기다림
// - 또한 부모 코루틴을 취소하면 자식 코루틴도 취소 됨.
// - 이를 계층적이라고 함.
// - 다른 언어에서는 계층적이지 않은 경우가 있음.

fun main(){
    runBlocking {
        coroutineScope {
            printCoroutine(this, "runBlocking")
            delay(1000)
        }

        runBlocking {
            printCoroutine(this, "runBlocking 2")
        }
    }
}

suspend fun testFunc() = coroutineScope {
    printCoroutine(this, "coroutineScope")
}
