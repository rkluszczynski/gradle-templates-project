package pl.info.rkluszczynski.java8.chm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class P1FibonacciOld {
    public int recursiveFib(int n) {
        if (n == 0 || n == 1) return n;

        System.out.println("calculating recursiveFib(" + n + ")");
        return recursiveFib(n - 2) + recursiveFib(n - 1);
    }

    private Map<Integer, Integer> cache = new ConcurrentHashMap<>();

    public int cachedFib(int n) {
        if (n == 0 || n == 1) return n;
        Integer result = cache.get(n);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(n);

                if (result == null) {
                    System.out.println("calculating cachedFib(" + n + ")");
                    result = cachedFib(n - 2) + cachedFib(n - 1);
                    cache.put(n, result);
                }
            }
        }
        return result;
    }
}
