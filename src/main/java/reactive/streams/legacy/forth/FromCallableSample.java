package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class FromCallableSample {
    public static void main(String[] args) {
        Flowable<Long> flowable = Flowable.fromCallable(() -> System.currentTimeMillis());
        flowable.subscribe(new DebugSubscriber<>());
    }
}
