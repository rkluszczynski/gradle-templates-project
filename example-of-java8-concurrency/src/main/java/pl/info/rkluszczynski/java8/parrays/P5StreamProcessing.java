package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class P5StreamProcessing extends DataClass {
    public void streamProcessing() {
        int[] src = getData();
        IntStream stream = Arrays.stream(src);
        int sum = stream.sum();
        System.out.println("\nSum: " + sum);

        Arrays.stream(src).filter(x -> x > 10).parallel().forEach(System.out::println);
    }

    public static void main(String[] args) {
        new P5StreamProcessing().streamProcessing();
    }
}
