package reactive.streams.aggregation.reduce;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import reactive.streams.DebugMaybeObserver;
import reactive.streams.DebugSingleObserver;

public class ReduceSample {
    public static void main(String[] args) {
        // 초기값 X
        Maybe<Integer> maybe = Flowable.just(1, 10, 100, 1000, 10000)
                .reduce((sum, data) -> sum + data);
        maybe.subscribe(new DebugMaybeObserver<>());

        // 초기값 O
        Single<Integer> single =
                Flowable.just(1, 10, 100, 1000, 10000)
                        .reduce(0, (sum, data) -> sum + data);
        single.subscribe(new DebugSingleObserver<>());

        // 초기값 생성 콜백
        Single<Integer> single1 = Flowable.just(1, 10, 100, 1000, 10000)
                .reduceWith(() -> 0, (sum, data) -> sum + data);
        single1.subscribe(new DebugSingleObserver<>());

    }
}
