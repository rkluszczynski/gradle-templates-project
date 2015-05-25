package pl.info.rkluszczynski.tutorial.java8.concurrency.ex1parray;

import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorExample {

    public static void main(String[] args) {
        int[] data = DataArrayGenerator.parallel(20);

        Spliterator<Integer> spliterator = Arrays.spliterator(data);
        spliterator.forEachRemaining(System.out::println);

        Arrays.stream(data)
                .filter(x -> x > 0)
                .parallel()
                .forEach(System.out::println);
    }
}
