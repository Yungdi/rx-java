package reactive.streams.legacy.async;

import java.util.Date;

public final class ImmutableObject {
    private final Date value;

    public ImmutableObject(Date value) {
        this.value = (Date) value.clone();
    }

    public Date getValue() {
        return (Date) value.clone();
    }

    public ImmutableObject changeValue(Date value) {
        return new ImmutableObject(value);
    }
}
