package reactive.streams.processor.publish;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

// 캐시 유무 판단

public class TestSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable =
                Flowable.interval(1L, TimeUnit.SECONDS, Schedulers.computation())
                        .take(5L);

        flowable.subscribe(new DebugSubscriber<>("No.1"));
        Thread.sleep(2000L);
        flowable.subscribe(new DebugSubscriber<>("No.2"));
        Thread.sleep(5000L);
    }
}
