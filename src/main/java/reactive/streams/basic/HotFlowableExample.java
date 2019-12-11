package reactive.streams.basic;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;

import java.util.concurrent.TimeUnit;

public class HotFlowableExample {
    public static void main(String[] args) throws InterruptedException {
        ConnectableFlowable<Integer> publish = ConnectableFlowable
                .interval(1L, TimeUnit.SECONDS)
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .publish();
        publish.subscribe();
        Thread.sleep(1000L);
        publish.connect();
    }
}
