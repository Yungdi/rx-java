package debug.scheduler;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestSchedulerTest {

    @Test
    public void TestScheduler_실행() {
        long start = System.currentTimeMillis();
        TestScheduler testScheduler = new TestScheduler();
        Flowable<Long> flowable = Flowable.interval(500L, TimeUnit.MILLISECONDS, testScheduler);
        TestSubscriber<Long> result = flowable.test();

        System.out.println("data=" + result.values());
        result.assertEmpty();

        // 0.5초간 실행
        testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS);

        // onNext 에서 받은 데이터 리스트
        System.out.println("data=" + result.values());

        testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS);
        System.out.println("data=" + result.values());
        result.assertValues(0L, 1L);

        testScheduler.advanceTimeTo(2000L, TimeUnit.MILLISECONDS);
        System.out.println("data=" + result.values());
        result.assertValues(0L, 1L, 2L, 3L);

        System.out.println("testScheduler#now=" + testScheduler.now(TimeUnit.MILLISECONDS));
        long totalTime = System.currentTimeMillis() - start;
        System.out.println("테스트에 걸린 시간 = " + totalTime);
    }

}
