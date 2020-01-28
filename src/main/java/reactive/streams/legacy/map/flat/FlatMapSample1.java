package reactive.streams.legacy.map.flat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

// 패턴4 예제
public class FlatMapSample1 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "", "B", "", "C")
                .flatMap(data -> {
                    if ("".equals(data)) {
                        return Flowable.empty();
                    } else {
                        return Flowable.just(data.toLowerCase());
                    }
                });
        flowable.subscribe(new DebugSubscriber<>());
    }
}
