package pl.info.rkluszczynski.tutorial.java8.concurrency.ex1parray;

import java.util.Arrays;

public class Exercise1Processor {
    private final int[] data;

    public Exercise1Processor(int[] data) {
        this.data = data;
    }

    public int[] seqSort(int[] data) {
        int[] result = data.clone();
        Arrays.sort(result);
        return result;
    }

    public int[] parSort(int[] data) {
//        FIXME

        int[] result = data.clone();
        Arrays.parallelSort(result, 1, data.length);
        return result;
    }

    public int[] seqPrefix(int[] data) {
        int[] result = data.clone();
        for (int i = 1; i < result.length; ++i) {
            result[i] += result[i - 1];
        }
        return result;
    }

    public int[] parPrefix(int[] data) {
//        FIXME

        int[] result = data.clone();
        Arrays.parallelPrefix(result, (x, y) -> x + y);
        return result;
    }
}
