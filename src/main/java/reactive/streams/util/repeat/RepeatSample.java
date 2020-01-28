package reactive.streams.util.repeat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class RepeatSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                .repeat(2L);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
