package reactive.streams.legacy.list;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import java.util.List;

public class ToListSample {
    public static void main(String[] args) {
        Single<List<String>> single =
                Flowable.just("A", "B", "C", "D", "E")
                .toList();
        single.subscribe(new SingleObserver<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<String> strings) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
