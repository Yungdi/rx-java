package reactive.streams.legacy;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class L06_MainThreadSample {
    public static void main(String[] args) {
        System.out.println("start");

        Flowable.just(1, 2, 3)
                .subscribe(new ResourceSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer data) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }

                    @Override
                    public void onError(Throwable t) {
                            t.printStackTrace();
                    }
                });

        System.out.println("end");
    }
}
