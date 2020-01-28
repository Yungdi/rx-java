package reactive.streams.legacy.empty;

import io.reactivex.Flowable;
import io.reactivex.Single;
import reactive.streams.DebugSingleObserver;

import java.util.concurrent.TimeUnit;

public class IsEmptySample {
    public static void main(String[] args) throws InterruptedException {
        Single<Boolean> single = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .take(3L)
                .filter(data -> data >= 2)
//                .filter(data -> data >= 3)
                .isEmpty();
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000L);
    }
}
