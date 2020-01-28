package reactive.streams.legacy.design_pattern.iterator;

public class IteratorPatternDemo {
    public static void main(String[] args) {
        MyList myList = new MyListImpl();

        Iterator iterator = myList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
