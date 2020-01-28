package reactive.streams.legacy.basic;

import io.reactivex.Flowable;

public class MethodChainSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(data -> data % 2 == 0)
                .map(data -> data * 10);
        flowable.subscribe(System.out::println);

        Flowable<Long> just = Flowable.just(System.currentTimeMillis());
        Flowable<Long> callable = Flowable.fromCallable(System::currentTimeMillis);
        just.subscribe(System.out::println);
        callable.subscribe(System.out::println);
    }
}
