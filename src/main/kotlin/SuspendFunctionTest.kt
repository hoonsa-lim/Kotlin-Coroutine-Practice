import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    test1()
    test2(this)

    /**
     * test1
     * hoonsa, url == https://github.com/hoonsa-lim
     * 소요시간 1 == 1015
     *
     * test2
     * hoonsa, url == https://github.com/hoonsa-lim
     * 소요시간 2 == 515
     */

    //여러가지 api 통신 중 하나에 문제가 있다면 다른 것이 의미가 없을 때 계층적인 구조를 잘 활용하면 좋음.
    val result = try{
        test3()
        "찍힘?"
    } catch (e: Exception){
        "형제의 exception 부모까지 전파"
    }
    println(result)
}

suspend fun test2(scope: CoroutineScope) {
    val time = measureTimeMillis {
        val name = scope.async { getUserName() }
        val githubUrl = scope.async { getUserGitHubUrl() }
//        val n = name.await()
//        val g = githubUrl.await()
        println("${name.await()}, url == ${githubUrl.await()}")
//        println("${n}, url == ${g}")
    }
    println("소요시간 2 == $time")
}

suspend fun test1(){
    val time = measureTimeMillis {
        val name = getUserName()
        val githubUrl = getUserGitHubUrl()
        println("$name, url == $githubUrl")
    }
    println("소요시간 1 == $time")
}

suspend fun getUserName(): String{
    delay(500)
    return "hoonsa"
}

suspend fun getUserGitHubUrl(): String{
    delay(500)
    return "https://github.com/hoonsa-lim"
}

suspend fun test3(){
    val time = measureTimeMillis {
        val name = getUserName1()
        val githubUrl = getUserGitHubUrl1()
        println("$name, url == $githubUrl")
    }
    println("소요시간 3 == $time")
}

suspend fun getUserName1(): String{
    try {
        delay(500)
    }finally {
        println("형제의 코루틴에서 Exception 이 발생하면 부모와 형제까지 전파 됨.")
    }

    return "hoonsa"
}

suspend fun getUserGitHubUrl1(): String{
    delay(200)
    throw IllegalArgumentException()
    return "https://github.com/hoonsa-lim"
}