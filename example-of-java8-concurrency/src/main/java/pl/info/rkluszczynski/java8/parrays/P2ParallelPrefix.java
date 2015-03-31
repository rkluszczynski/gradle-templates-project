package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class P2ParallelPrefix extends DataClass {
    public class MyIntOperator implements IntBinaryOperator {
        @Override
        public int applyAsInt(int left, int right) {
            return left + right;
        }
    }

    public void accumulate() {
        int[] src = null;

        // accumulate test
        System.out.println("\nParallel prefix:");
        src = getData();
        IntBinaryOperator op = new P2ParallelPrefix.MyIntOperator();
        long start = System.currentTimeMillis();

        Arrays.parallelPrefix(src, new MyIntOperator());

        long end = System.currentTimeMillis();
        System.out.println("--Elapsed sort time: " + (end - start));
    }

    private void showLambda() {
        Integer[] array = new Integer[4]; // 4M
        Arrays.parallelSetAll(array, i -> i + 1);
        Arrays.spliterator(array).forEachRemaining(System.out::println);
        System.out.println("--");
        Arrays.parallelPrefix(array, 0, array.length, (x, y) -> x + y);
        Arrays.spliterator(array).forEachRemaining(System.out::println);
    }

    public static void main(String[] args) {
//        new P2ParallelPrefix().accumulate();
        new P2ParallelPrefix().showLambda();
    }
}
