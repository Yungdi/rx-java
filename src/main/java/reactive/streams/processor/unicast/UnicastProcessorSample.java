package reactive.streams.processor.unicast;

import io.reactivex.processors.ReplayProcessor;
import io.reactivex.processors.UnicastProcessor;
import reactive.streams.DebugSubscriber;

public class UnicastProcessorSample {
    public static void main(String[] args) {
        UnicastProcessor<Integer> processor = UnicastProcessor.create();

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
