package reactive.streams.legacy.basic;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class EffectedSample {
    private enum State {
        ADD, MULTIPLY
    }

    private static State calcMethod;

    public static void main(String[] args) throws InterruptedException {
        calcMethod = State.ADD;

        Flowable<Long> scan = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(7)
                .scan((sum, data) -> calcMethod == State.ADD ? sum + data : sum * data);
        scan.subscribe(System.out::println);
        Thread.sleep(1000);
        System.out.println("계산 방법 변경");
        calcMethod = State.MULTIPLY;
        Thread.sleep(1000);
    }
}


