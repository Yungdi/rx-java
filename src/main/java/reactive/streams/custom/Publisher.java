package reactive.streams.custom;

public interface Publisher<T> {
    void subscribe(Subscriber <? super T> subscriber);
}
