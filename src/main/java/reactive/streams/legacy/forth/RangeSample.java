package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class RangeSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.range(10, 3);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
