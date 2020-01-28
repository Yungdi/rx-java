package reactive.streams.legacy.buffer;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferSample2 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<List<Long>> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(7)
                .buffer(() -> Flowable.timer(1000L, TimeUnit.MILLISECONDS));
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);

    }
}
