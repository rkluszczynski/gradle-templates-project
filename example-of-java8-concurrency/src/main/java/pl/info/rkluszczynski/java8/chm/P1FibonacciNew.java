package pl.info.rkluszczynski.java8.chm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class P1FibonacciNew {
    private Map<Integer, Integer> cache = new ConcurrentHashMap<>();

    public int fib(int n) {
        if (n == 0 || n == 1) return n;

        return cache.computeIfAbsent(n, (key) -> {
            System.out.println("calculating fib(" + n + ")");
            return fib(n - 2) + fib(n - 1);
        });
    }

    public int lambdaFib(int n) {
        if (n == 0 || n == 1) return n;

        return cache.computeIfAbsent(n, (key) -> lambdaFib(n - 2) + lambdaFib(n - 1));
    }
}

