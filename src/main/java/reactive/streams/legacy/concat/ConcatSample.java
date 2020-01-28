package reactive.streams.legacy.concat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class ConcatSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5L);
        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2L)
                .map(data -> data + 100L);
        Flowable<Long> result = Flowable.concat(flowable1, flowable2);
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
