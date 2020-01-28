package reactive.streams.legacy.map.flat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

public class FlatMapSample3 {
    public static void main(String[] args) {
        Flowable<Integer> original = Flowable.just(1, 2, 0, 4, 5)
//        Flowable<Integer> original = Flowable.just(1, 2, 4, 5)
                .map(data -> 10/data);

        Flowable<Integer> flowable = original.flatMap(
                Flowable::just,
                error -> Flowable.just(-1),
                () -> Flowable.just(100)
        );
        flowable.subscribe(new DebugSubscriber<>());
    }
}
