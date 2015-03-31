package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;
import static java.util.stream.IntStream.range;
import static java.util.stream.LongStream.rangeClosed;

public class ParallelTest {

    public static void main(String[] args) throws InterruptedException {
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));




//        new ParallelTest().runExecutors();
//        new ParallelTest().runParallelTestWIthForkJoinPool();
//        IntStream.range(1, 10).parallel().collect(Collectors.toList());
//        IntStream.range(1, 10).a .collect(Collectors.toList());
    }

//    private void runParallelTestWIthForkJoinPool() {
//        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
//        forkJoinPool.submit(() ->
//                        //parallel task here, for example
//                        IntStream.range(1, 1_000_000)
//                                .parallel()
////                                .filter(this::isPrime)
//                                .collect(Collectors.toList())
//        ).get();
//    }

    private void runExecutors() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(() -> runTask(1000)); //incorrect task
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));

        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);
    }

    private void runTask(int delay) {
        range(1, 1_000_000).parallel().filter(this::isPrime).peek(i -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).max()
                .ifPresent(max -> System.out.println(Thread.currentThread() + " " + max));
    }

    public boolean isPrime(int n) {
        return n > 1 && rangeClosed(2, (int) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }
}
