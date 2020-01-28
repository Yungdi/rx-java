package reactive.streams.legacy;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class L05_SyncFasterSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("emit: " + System.currentTimeMillis() + " 밀리초: " + data))
                .subscribe(data -> Thread.sleep(500L));
        Thread.sleep(3000L);
    }
}
