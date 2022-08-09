import kotlinx.coroutines.*

fun main1() = runBlocking<Unit>{
    val job1 = launch {
        printCoroutine(this, "launch 1")
        delay(500)
        println("test")
    }

    delay(100)
    job1.cancel()
    println("end test")
}

fun main() = runBlocking<Unit>{
    //launch(Dispatchers.Default): Dispatchers.Default 를 활용하여 다른 쓰레드에서 동작하게 됨.
    val job1 = launch(Dispatchers.Default) {
        printCoroutine(this, "launch 1")
        delay(500)
        println("test")
    }

    delay(100)
    job1.cancelAndJoin()    //취소가 될 때까지 중지 시키기 위해서 사용함. cancel 과 join을 연속해서 사용한 것과 동일함.
    println("end test")

    this.isActive   //코루틴의 활성화 상태를 가지고 있는 속성. 활성화 상태일 때만 cancel 시킬 수 있는 코루틴이 됨.
}