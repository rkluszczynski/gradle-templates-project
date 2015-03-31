package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.Spliterator;

public class P4Spliterator extends DataClass {

    public void spliterate() {
        System.out.println("\nSpliterate:");
        int[] src = getData();
        Spliterator<Integer> spliterator = Arrays.spliterator(src);
        spliterator.forEachRemaining( n -> action(n) );
    }

    public void action(int value) {
        System.out.println("value:"+value);
        // Perform some real work on this data here...
    }

    public static void main(String[] args) {
        new P4Spliterator().spliterate();
    }
}
