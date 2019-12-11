package reactive.streams.basic;

import io.reactivex.Flowable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HelloWorldSample {
    public static void main(String[] args) {
        // 데이터를 통지하는 생산자 생성
        Flowable<String> flowable = Flowable.just("Hello", "World");
        // 통지 받은 데이터 출력
        flowable.subscribe(data -> System.out.println(data));

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("hello world"));
    }
}
