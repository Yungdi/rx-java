package reactive.streams.legacy.all;

import io.reactivex.Flowable;
import io.reactivex.Single;
import reactive.streams.DebugSingleObserver;

import java.util.concurrent.TimeUnit;

public class AllSample {
    public static void main(String[] args) throws InterruptedException {
        Single<Boolean> single = Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3).all(data -> data % 1 == 0);
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000L);
    }
}
