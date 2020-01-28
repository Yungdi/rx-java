package reactive.streams.processor.replay;

import io.reactivex.processors.ReplayProcessor;
import reactive.streams.DebugSubscriber;

public class ReplayProcessorSample {
    public static void main(String[] args) {
        // 무제한 캐시
//        ReplayProcessor<Integer> processor = ReplayProcessor.create();
        // 2개 캐시
        ReplayProcessor<Integer> processor = ReplayProcessor.createWithSize(2);

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
