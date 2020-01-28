package reactive.streams.aggregation.scan;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class ScanSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.just(1, 10, 100, 1000, 10000)
                .scan(0, (sum, data) -> sum + data);
        flowable.subscribe(new DebugSubscriber<>());

        Flowable<Integer> flowable1 = Flowable.just(1, 10, 100, 1000, 10000)
                .scan((sum, data) -> sum + data);
        flowable1.subscribe(new DebugSubscriber<>());
    }
}
