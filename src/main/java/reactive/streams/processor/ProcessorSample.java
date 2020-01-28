package reactive.streams.processor;

import io.reactivex.processors.PublishProcessor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ProcessorSample {
    public static void main(String[] args) {
        PublishProcessor<Object> processor = PublishProcessor.create();
        processor.subscribe(new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
        processor.onNext(1);
        processor.onNext(2);
        processor.onNext(3);

    }
}
