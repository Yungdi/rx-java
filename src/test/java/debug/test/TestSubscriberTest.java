package debug.test;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestSubscriberTest {
    @Test
    public void TestSubscriber_사용의_간단한_예제() throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS);

        TestSubscriber<Long> testSubscriber = flowable.test();

        // 데이터 통지 확인
        testSubscriber.assertEmpty();
        // 지정 시간 동안 대기
        testSubscriber.await(150L, TimeUnit.MILLISECONDS);
        // 지금까지 통지된 데이터 확인
        testSubscriber.assertValues(0L);

        testSubscriber.await(100L, TimeUnit.MILLISECONDS);
        testSubscriber.assertValues(0L, 1L);
    }

    @Test
    public void 빈_flowable_테스트() {
        Flowable.empty()
                .test()
                // 데이터 없으면 성공
                .assertNoValues()
                // 에러 없으면 성공
                .assertNoErrors()
                // 완료 했으면 성공
                .assertComplete();
    }

}
