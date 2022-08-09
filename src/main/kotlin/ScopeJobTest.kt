import kotlinx.coroutines.*
//suspend 키워드가 붙은 함수가 호출될 때, 호출함수 이후 함수는 잠시 중단 됨.
//dart 에서 async 키워드 붙은 함수 실행할 때 await 키워드 붙여서 실행하는 것과 유사함.

//코루틴 빌더 중 runBlocking 과 withContext 는 쓰레드를 멈춤, 나머지는 코루틴 처럼 일하고자하는 코루틴이 있다면 협력함.
fun main() = runBlocking<Unit>{
    println(1)

    val job = launch {
        printCoroutine(this, "launch 1")
    }

    job.join()  //실행 중단.
    launch {
        printCoroutine(this, "launch 2")
    }

//    job.join()
    println(2)

    //100만개도 돌아감. 10만개는 거뜬함.
    repeat(1_000_000){
        launch {
            printCoroutine(this, "launch repeat test $it")
        }
    }
}


//이론적으로 10만개의 간단한 일처리를 하는 코루틴을 만들어도 큰 부담되지 않음.

