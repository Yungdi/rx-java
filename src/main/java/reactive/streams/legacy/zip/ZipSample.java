package reactive.streams.legacy.zip;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class ZipSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS).take(5L);
        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS).take(3L).map(data -> data + 100L);
        Flowable<Long> zip = Flowable.zip(flowable1, flowable2, (f1, f2) -> f1 + f2);
        zip.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
