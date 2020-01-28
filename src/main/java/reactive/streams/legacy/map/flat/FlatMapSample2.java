package reactive.streams.legacy.map.flat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class FlatMapSample2 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(1, 3)
                .flatMap(data -> Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3), // 123 생성
                        (sourceData, newData) -> "[" + sourceData + "]" + newData);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(1000L);
    }
}
