package reactive.streams.legacy.sequence;

import io.reactivex.Flowable;
import io.reactivex.Single;
import reactive.streams.DebugSingleObserver;

import java.util.concurrent.TimeUnit;

public class SequenceEqualSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3L);
        Flowable<Long> flowable2 = Flowable.just(0L, 1L, 3L);

        Single<Boolean> single = Flowable.sequenceEqual(flowable1, flowable2);
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000L);
    }
}
