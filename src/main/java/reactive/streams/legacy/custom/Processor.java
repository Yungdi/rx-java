package reactive.streams.legacy.custom;

public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
