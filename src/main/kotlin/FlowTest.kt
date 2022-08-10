import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking <Unit>{
    //collect == rxJava.subscribe 와 유사함.
    flowTest().collect{
        println("flowFunction == $it")
        printCoroutine(this, "flowTest")
    }
    flowOf(1,2,3,4,5).collect{
        println("flowOf == $it")
    }
    listOf(1,2,3,4,5).asFlow()
        .map {
            printCoroutine(this, "asFlow1")
            it + 1
        }
        .flowOn(Dispatchers.Default)
        .collect{
            println("asFlow == $it")
            printCoroutine(this, "asFlow2")
        }

    launch(Dispatchers.IO) {
        (0..5).asFlow().collect{
            println("asFlow 2 == $it")
            printCoroutine(this, "flowTest")
        }
    }


    /**
     * flow 묶기
     *
     * - zip: 짝을 맞춰서 방출,
     * - combine: 먼저나오는 값과 이전값을 방출,
     */
    val i = flowTest()
    val d = flowTest1()


    /**
     * 예외 처리
     *
     * - try/catch
     * -
     */
    flowTest()
        .catch {
            println(it)
        }
        .collect {
            println(it)
    }.runCatching {
            println(this)
    }

    /**
     * 완료 처리
     *
     * onComplete
     */
    flowTest()
        //코드 마지막에 실행 됨. cause가 null 일 경우에는 완료되는 동안 exception 이 발생하지 않았음을 의미.
        .onCompletion { cause ->
            if (cause != null){
                println("exception == $cause")
            }else{
                println("오류 없이 완료.")
            }
        }
        .collect{
            println(it)
        }
}

fun flowTest(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 100))
        delay(100)
    }
}

fun flowTest1(): Flow<Double> = flow {
    repeat(10) {
        emit(Random.nextDouble(0.0, 100.0))
        delay(100)
    }
}