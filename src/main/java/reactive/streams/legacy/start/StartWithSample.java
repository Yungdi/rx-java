package reactive.streams.legacy.start;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class StartWithSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS).take(5L);
        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS).take(3L).map(data -> data + 100L);
        Flowable<Long> startWithFlowable = flowable1.startWith(flowable2);

        startWithFlowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
