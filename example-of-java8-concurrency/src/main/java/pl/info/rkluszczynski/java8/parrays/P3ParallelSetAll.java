package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.Random;

public class P3ParallelSetAll {
    public Integer[] createLargeArray() {
        Integer[] array = new Integer[1024 * 1024 * 4]; // 4M
        Arrays.parallelSetAll(array, i -> new Integer(new Random().nextInt()));
        return array;
    }

    public void createLargeArrayWithCustomGenerator() {
        Integer[] array = new Integer[1024 * 1024 * 4]; // 4M
        Arrays.parallelSetAll(array, i -> new Integer(customGenerator(getNextSensorValue())));
    }

    public int customGenerator(int arg) {
        return arg + 1; //  some fancy formula here...
    }

    public int getNextSensorValue() {
        // Just random for illustration
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        P3ParallelSetAll p3ParallelSetAll = new P3ParallelSetAll();
        Arrays.parallelSetAll(p3ParallelSetAll.createLargeArray(), x -> x * 100);
    }
}
