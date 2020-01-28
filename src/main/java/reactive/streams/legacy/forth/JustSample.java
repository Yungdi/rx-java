package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class JustSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E");

        flowable.subscribe(new DebugSubscriber<>());
    }
}
