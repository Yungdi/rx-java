package reactive.streams.legacy.count;

import io.reactivex.Flowable;
import io.reactivex.Single;
import reactive.streams.DebugSingleObserver;

import java.util.concurrent.TimeUnit;

public class CountSample {
    public static void main(String[] args) throws InterruptedException {
        Single<Long> single = Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3).count();
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000L);
    }
}
