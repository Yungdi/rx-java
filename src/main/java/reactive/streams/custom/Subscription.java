package reactive.streams.custom;

public interface Subscription {
    void request(long num);
    void cancel();
}
