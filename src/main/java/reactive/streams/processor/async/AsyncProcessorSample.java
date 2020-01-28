package reactive.streams.processor.async;

import io.reactivex.processors.AsyncProcessor;
import reactive.streams.DebugSubscriber;

public class AsyncProcessorSample {
    public static void main(String[] args) {
        AsyncProcessor<Integer> processor = AsyncProcessor.create();

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
        processor.subscribe(new DebugSubscriber<>("------ No.3"));
    }
}
