package reactive.streams.util.repeat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class RepeatUntilSample {
    public static void main(String[] args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(3)
                .repeatUntil(() -> {
                    System.out.println("called");
                    return System.currentTimeMillis() - startTime > 500L;
                });
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
