package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class FromArraySample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");
        flowable.subscribe(new DebugSubscriber<>());
    }
}
