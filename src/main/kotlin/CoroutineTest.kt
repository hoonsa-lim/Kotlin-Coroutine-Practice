import kotlinx.coroutines.*

/**
 * 코루틴
 *
 * 비동기 및 병렬을 쉽게 처리하는 라이브러리 RxJava. 하지만, 항상 스트림을 사용해야하고, flatMap 지옥의 구조 등 가독성을 해치는 상황이 있을 수 있음
 *
 * 이가 개선된 것이, 코루틴.
 *
 */

fun main(){
    println("======================== main start")

    //1. runBlocking: 코루틴을 생성하는 방법, 모두 실행되기 전까지 블락
    runBlocking {

        //2. launch: 코루틴 내부에서 코루틴을 생성하는 방법
        launch {
            //launch 가 나중에 실행되는 이유:
            // - runBlocking 과 launch 모두 같은 쓰레드에서 실행
            // - 그렇지만 runBlocking 함수는 코루틴이 모두 실행될 때까지 기다림.
            // -> runBlocking 함수를 모두 기다리다가 실행됐기 때문.
            printCoroutine(this, "launch 1")      //StandaloneCoroutine
        }

        printCoroutine(this, "runBlocking")     //BlockingCoroutine

        //delay: 코루틴을 잠시 멈춤.
        // - 이때 runBlocking 내부에 있는 launch 함수가 먼저 실행 됨.
        // - Thread.sleep 과 차이: delay는 코루틴을 양보하여 launch 가 실행되지만, Thread.sleep 은 양보하지 않음.
        delay(500)
//        Thread.sleep(500)
        printCoroutine(this, "runBlocking")     //BlockingCoroutine

        launch {
            delayTest(this)
        }
    }

    println("======================== main end")
}

//suspend: dart 의 async 키워드와 유사. 
suspend fun delayTest(scope: CoroutineScope){
    delay(1000)
    printCoroutine(scope, "launch 2")
}

fun printCoroutine(scope: CoroutineScope, builderName: String){
    println("- $builderName \n  thread name == ${Thread.currentThread().name}, coroutineContext == ${scope.coroutineContext}")
}

