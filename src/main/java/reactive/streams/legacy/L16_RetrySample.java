package reactive.streams.legacy;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static io.reactivex.Flowable.create;

public class L16_RetrySample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> {
            System.out.println("Flowable 처리 시작");
            for (int i = 1; i <= 3; i++) {
                if (i == 3)
                    throw new Exception("예외 발생");
                emitter.onNext(i);
            }
        }, BackpressureStrategy.BUFFER)
                .doOnSubscribe(subscription -> System.out.println("flowable: doOnSubscribe"))
                .retry(3L);

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscriber: onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer data) {
                System.out.println("data=" + data);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("에러=" + error);
            }

            @Override
            public void onComplete() {
                System.out.println("종료");
            }
        });
    }
}
