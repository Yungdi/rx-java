package reactive.streams.legacy.map.concat;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class ConcatMapEagerSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(10, 3)
                .concatMapEager(sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                        .take(2)
                        .map(data -> {
                            long time = System.currentTimeMillis();
                            return time + "ms: [" + sourceData + "] " + data;
                        }));
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);
    }
}
