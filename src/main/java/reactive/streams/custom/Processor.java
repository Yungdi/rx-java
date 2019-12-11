package reactive.streams.custom;

public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
