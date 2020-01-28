package reactive.streams.legacy.combine;

import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CombineLatestSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS).take(5L);
        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS).take(3L).map(data -> data + 100L);
        Flowable<List<Long>> result = Flowable.combineLatest(flowable2, flowable1, (f1, f2) -> Arrays.asList(f2, f1));
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
        // 같이 1500ms 에 끝나야되는데... 시간을 찍어서 확인해보자
        // 마지막 2개 [3,102] [4,102] vs [4,101], [4,102]
    }
}
