package reactive.streams.util.timeout;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class Timeout {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Object> flowable = Flowable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            try {
                Thread.sleep(1200L);
            } catch (InterruptedException e) {
                emitter.onError(e);
                return;
            }
            emitter.onNext(3);
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .timeout(1000L, TimeUnit.MILLISECONDS);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
