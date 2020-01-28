package reactive.streams.legacy;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class L07_NonMainThreadSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long data) {
                        Thread thread = Thread.currentThread();
                        System.out.println(thread.getName() + ": " + data + ", isDaemon: " + thread.isDaemon());
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

        Thread.sleep(1000L);
    }
}
