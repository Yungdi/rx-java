package reactive.streams.legacy.forth;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.Arrays;

public class FromIterableSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.fromIterable(Arrays.asList("A", "B", "C", "D", "E"));
        flowable.subscribe(new DebugSubscriber<>());
    }
}
