package reactive.streams.util.repeat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class RepeatWhenSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(3L)
                .repeatWhen(completeHandler ->
                        completeHandler.delay(1000L, TimeUnit.MILLISECONDS)
                                .take(2L)
                                .doOnNext(data -> System.out.println("emit: " + data))
                                .doOnComplete(() -> {
                                    System.out.println("complete");
//                                    Thread.sleep(5000L);
                                })
                ).map(data -> System.currentTimeMillis() + "ms: " + data);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(5000L);
    }
}
