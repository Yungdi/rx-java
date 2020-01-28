package reactive.streams.legacy.concat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConcatEagerSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 =
                Flowable.interval(300L, TimeUnit.MILLISECONDS)
                        .take(5L)
                        .doOnNext(aLong -> System.out.println(aLong + ": " + System.currentTimeMillis()));
        Flowable<Long> flowable2 =
                Flowable.interval(500L, TimeUnit.MILLISECONDS)
                        .take(5L)
                        .map(data -> data + 100L)
                        .doOnNext(aLong -> System.out.println(aLong + ": " + System.currentTimeMillis()));
        ;

        List<Flowable<Long>> sources = Arrays.asList(flowable1, flowable2);
//        Flowable<Long> result = Flowable.concatEager(sources);
        Flowable<Long> result = Flowable.concatArrayEager(flowable1, flowable2);
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
