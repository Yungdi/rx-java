package reactive.streams.processor.publish;

import io.reactivex.processors.PublishProcessor;
import reactive.streams.DebugSubscriber;

public class PublishProcessorSample {
    public static void main(String[] args) {
        PublishProcessor<Integer> processor = PublishProcessor.create();
        System.out.println("Subscriber No.1 추가");
        processor.subscribe(new DebugSubscriber<>("No.1"));

        processor.onNext(1);
        processor.onNext(2);
        processor.onNext(3);

        System.out.println("Subscriber No.2 추가");
        processor.subscribe(new DebugSubscriber<>("--- No.2"));

        processor.onNext(4);
        processor.onNext(5);
        processor.onComplete();

        System.out.println("Subscriber No.3 추가");
        processor.subscribe(new DebugSubscriber<>("--- No.3"));

    }
}
