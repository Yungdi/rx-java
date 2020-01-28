package reactive.streams.legacy.custom;

public interface Subscription {
    void request(long num);
    void cancel();
}
