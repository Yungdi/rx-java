package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.time.LocalTime;

public class DeferSample {
    public static void main(String[] args) {
        Flowable<LocalTime> flowable = Flowable.defer(() -> Flowable.just(LocalTime.now()));
        flowable.subscribe(new DebugSubscriber<>("No. 1"));
        flowable.subscribe(new DebugSubscriber<>("No. 2"));

    }
}
