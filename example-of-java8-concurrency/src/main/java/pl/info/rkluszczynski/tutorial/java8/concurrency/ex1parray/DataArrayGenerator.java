package pl.info.rkluszczynski.tutorial.java8.concurrency.ex1parray;

import java.util.Arrays;
import java.util.Random;

public class DataArrayGenerator {
    private static final Random random = new Random();

    public static int[] sequential(int size) {
        int[] data = new int[size];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt();
        }
        return data;
    }

    public static int[] parallel(int size) {
//        FIXME

        int[] data = new int[size];
        Arrays.parallelSetAll(data, i -> random.nextInt());
        return data;
    }
}
