package reactive.streams.legacy.contains;

import io.reactivex.Flowable;
import io.reactivex.Single;
import reactive.streams.DebugSingleObserver;

import java.util.concurrent.TimeUnit;

public class ContainsSample {
    public static void main(String[] args) throws InterruptedException {
        Single<Boolean> single = Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3L).contains(2L);
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000L);
    }
}
