import kotlinx.coroutines.*


/**
 * 코루틴 계층 구조를 끊어내는 방법은 신규 Job 객체를 할당하면 가능하다.
 */
fun main() = runBlocking<Unit>{
    //매개변수 x: 부모의 컨텍스트를 따름. ==> main 쓰레드에서 동작함.
    launch() {
        printCoroutine(this, "launch 1")
    }
    //main
    launch(Dispatchers.Unconfined) {
        //어디에도 속하지 않은 스레드 풀. 예상 불가함.
        printCoroutine(this, "launch 2")
    }
    //DefaultDispatcher-worker-1
    launch(Dispatchers.Default) {
        //복잡한 연산을 위한 쓰레드 풀
        printCoroutine(this, "launch 3")
    }
    //DefaultDispatcher-worker-1
    launch(Dispatchers.IO) {
        //네트워크, 파일 io 를 위한 쓰레드 풀
        printCoroutine(this, "launch 4")
    }
    //신규 쓰레드 생성 후 그곳에서 동작
    launch(newSingleThreadContext("my thread")) {
        //Default, IO 등의 쓰레드 풀이 이미 많은 작업을 하고 있어 작업 할당을 받지 못할 수 있을 때. 신규 쓰레드 생성.
        printCoroutine(this, "launch 5")
    }

    launch {
//   launch(Job()) {    //계층 구조 벗어남.
//    launch(SupervisorJob()) { //계층 구조 유지하며, 예외 발생 시 자식에게만 전파.
        delay(500)
        throw IllegalArgumentException()
    }

    launch {
        //위 launch 에서 예외 발생 시 계층 구조에 의해서 종료가 되지만.
        //위 launch에 신규 Job을 할당하면 계층 구조을 벗어나게 됨.
        //가족관계 끝!
        delay(501)
        printCoroutine(this, "launch 6")
    }

    //코루틴 엘리먼트 결합하기
    // + 연산자를 사용할 수 있음.
    launch(Dispatchers.Default + CoroutineName("만든 이름!")) {
        printCoroutine(this, "launch 7")
    }
}