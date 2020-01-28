package reactive.streams.util.delay;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import reactive.streams.DebugSubscriber;

import java.util.concurrent.TimeUnit;

public class DelaySample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("처리 시작: " + System.currentTimeMillis());

        // FlowableOnSubscribe.subscribe(FlowableEmitter emitter)
        Flowable<Object> flowable = Flowable.create(emitter -> {
            System.out.println("구독 시작: " + System.currentTimeMillis());
            // 구독 시작과 동시에 통지하는 게 아니라 0.2초 후에 통지
            emitter.onNext("A");
            emitter.onNext("B");
            emitter.onNext("C");
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .delay(2000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("통지 시각: " + System.currentTimeMillis()));
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
