package reactive.streams;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class DebugMaybeObserver<T> implements MaybeObserver<T> {
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

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
