package reactive.streams.legacy.async;

public class Counter {
    private volatile int count;

    void increment() {
        count++;
    }

    int get() {
        return count;
    }
}
