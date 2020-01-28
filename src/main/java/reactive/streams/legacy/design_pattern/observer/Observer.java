package reactive.streams.legacy.design_pattern.observer;

public abstract class Observer {
    public abstract void update();
    protected Subject subject;
}