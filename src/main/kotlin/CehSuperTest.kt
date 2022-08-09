import kotlinx.coroutines.*

/**
 * GlobalScope: 전역 코루틴 스코프
 * 쉽게 사용하지만 계층 구조를 갖지 않아 관리가 어렵다.
 *
 * suspend 없이 코루틴 사용하는 방법
 * 1. runBlocking
 * 2. CoroutineScope
 * 3. GlobalScope
 *
 * 코루틴 익셉션 핸들러 coroutine exception handler
 *
 *
 * SupervisorJob: 계층 구조에서 에러 발생할 경우 자식에게만 전파.
 * supervisorScope 를 사용할 경우에는 ceh 를 붙여줘야 오류 발생되지 않는다.
 */
fun main() = runBlocking{
    val job = GlobalScope.launch(Dispatchers.IO) {
        printCoroutine(this, "GlobalScope.launch")
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch(ceh) {
        printCoroutine(this, "CoroutineScope.launch")
        throw IllegalArgumentException()
    }

    //모두 기다릴 경우 사용.
    joinAll(job, job2)
}

//전역 예외 처리기로 사용 가능함.
val ceh = CoroutineExceptionHandler { c, t ->
    println("exception == $t")
}

