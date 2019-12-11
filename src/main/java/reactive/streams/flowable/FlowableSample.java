package reactive.streams.flowable;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;

public class FlowableSample {
    public static void main(String[] args) throws InterruptedException {
        // Flowable 생성

        Flowable<String> flowable =
                Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": subscribe");
                        String[] data = {"Hello, World!", "안녕, RxJava!"};
                        for (String datum : data) {
                            if (emitter.isCancelled())
                                return;
                            emitter.onNext(datum);
                        }
                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER);
        // 숨겨진 부분: onSubscribe(new Subscription), 배압?, 예외는 상속 X?, 예외 발생 시 onError 호출은? emitter.onError(throwable)

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onNext(String data) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);
                        if (new Random().nextInt(10) % 2 == 0)
                            throw new RuntimeException();
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("error!!!!!!!!!!!");
                    }
                });
        Thread.sleep(1000);
    }
}
