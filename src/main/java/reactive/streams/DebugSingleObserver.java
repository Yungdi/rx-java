package reactive.streams;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class DebugSingleObserver<T> implements SingleObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + t);
    }

    @Override
    public void onError(Throwable e) {

    }
}
