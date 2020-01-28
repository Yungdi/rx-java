package reactive.streams.legacy.design_pattern.iterator;

public class MyListImpl implements MyList {
    public final String names[] = {"A", "B", "C", "D"};

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < names.length)
                return true;
            else
                return false;
        }

        @Override
        public String next() {
            return names[index++];
        }
    }

}
