package reactive.streams.legacy.design_pattern;

import java.util.ArrayList;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }
        for (int i = 0; i < integerList.size(); i++) {
            Integer integer = integerList.get(i);
            if (i % 2 == 0) {
                System.out.println(integer);
                integerList.remove(i);
            }
        }
    }
}
