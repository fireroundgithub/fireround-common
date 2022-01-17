package fireround.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class CollectionsTest {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 9, 6, 7, 8, 10, 19);
        Collection<List<Integer>> result = Collections.groupByFixedSize(numbers, 4);
        System.out.println(result);
    }
}