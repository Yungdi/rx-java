package reactive.streams.processor.behavior;

import io.reactivex.processors.BehaviorProcessor;
import reactive.streams.DebugSubscriber;

public class BehaviorProcessorSample {
    public static void main(String[] args) {
        BehaviorProcessor<Integer> behaviorProcessor = BehaviorProcessor.<Integer>create();
        behaviorProcessor.subscribe(new DebugSubscriber<>("No.1"));

        behaviorProcessor.onNext(1);
        behaviorProcessor.onNext(2);
        behaviorProcessor.onNext(3);

        System.out.println("Subscriber No.2 추가");
        behaviorProcessor.subscribe(new DebugSubscriber<>("--- No.2"));

        behaviorProcessor.onNext(4);
        behaviorProcessor.onNext(5);

        behaviorProcessor.onComplete();

        System.out.println("Subscriber No.3 추가");
        behaviorProcessor.subscribe(new DebugSubscriber<>("------ No.3"));
    }
}
