import kotlinx.coroutines.*

//코루틴인 취소될 때 JobCancellationException 를 던짐.
//이를 활용하여 자원 해제 작업을 수행할 수 있음.
fun main() = runBlocking<Unit>{
    val job1 = launch {
        try {
            printCoroutine(this, "launch 1")
            delay(500)
            println("test")
        }catch (e: Exception){
            println("error == $e")      //JobCancellationException
        }finally {
            println("finally")
        }
    }

    val job2 = launch {
        //withContext(NonCancellable): 취소 할 수 없는 코루틴 스코프.
        withContext(NonCancellable){
            try {
                printCoroutine(this, "withContext(NonCancellable)")
                delay(500)
                println("test 2")
            }catch (e: Exception){
                println("error 2 == $e")      //withContext(NonCancellable) 사용 했기 때문에 해당 블럭은 취소 되지 않음. JobCancellationException 발생하지 않음.
            }finally {
                println("finally 2")
            }
        }
    }

    //withTimeout
    //500 밀리초 후 예외 발생: TimeoutCancellationException
    try {
        withTimeout(500){
            printCoroutine(this, "withTimeout")
            delay(501)
        }
    }catch (e: Exception){
        println(e)
    }

    //withTimeoutOrNull
    val result = withTimeoutOrNull<Boolean>(500){
        delay(501)
        true
    } ?: "null 입니다."

    println("$result  withTimeoutOrNull 을 활용하면 try catch 없이 간결한 코드도 사용할 수 있음.")


    delay(100)
    job1.cancelAndJoin()
    job2.cancelAndJoin()
    println("end test")
}