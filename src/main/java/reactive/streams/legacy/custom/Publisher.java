package reactive.streams.legacy.custom;

public interface Publisher<T> {
    void subscribe(Subscriber <? super T> subscriber);
}
