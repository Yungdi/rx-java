package debug.blocking;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class BlockingTest {

    @Test
    public void
    첫번째_통지_데이터_얻기() {
        Long actual = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .blockingFirst();
        Assert.assertThat(actual, CoreMatchers.is(0L));
    }

    @Test
    public void 특정값으로_첫번째_통지_데이터_얻기() {
        Long actual = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .blockingFirst();
        Assert.assertThat(actual, CoreMatchers.is(2L));
    }

    @Test
    public void 마지막_통지_데이터_얻기() {
        Long actual = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(3L)
                .blockingLast();
        Assert.assertThat(actual, CoreMatchers.is(2L));
    }

    @Test
    public void 통지_데이터를_얻는_Iterable_가져오기() throws InterruptedException {
        Iterable<Long> result = Flowable.interval(1000L, 300L, TimeUnit.MILLISECONDS)
                .take(3L)
                .blockingIterable();
        Iterator<Long> iterator = result.iterator();

        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), CoreMatchers.is(0L));
        Assert.assertThat(iterator.next(), CoreMatchers.is(1L));
        Assert.assertThat(iterator.next(), CoreMatchers.is(2L));
        Thread.sleep(1000L);
        Assert.assertThat(iterator.next(), CoreMatchers.is(3L));
        Assert.assertThat(iterator.next(), CoreMatchers.is(4L));
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void Flowable_실행하고_처리_결과_확인() {
        Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5L);
        Counter counter = new Counter();
        flowable.blockingSubscribe(new DisposableSubscriber<Long>() {
            @Override
            public void onNext(Long aLong) {
                counter.increase();
            }

            @Override
            public void onError(Throwable t) {
                Assert.fail(t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        Assert.assertThat(counter.get(), CoreMatchers.is(5));
    }

    class Counter {
        private volatile int count;

        void increase() {
            count++;
        }

        int get() {
            return count;
        }
    }

}