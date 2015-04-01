package pl.info.rkluszczynski.java8.chm;

/**
 * Created by Rafal on 2015-04-01.
 */
public class P1Main {

    public static void main(String[] args) {
        System.out.println("fib(7) = " + new P1FibonacciOld().recursiveFib(7));
        System.out.println("fib(7) = " + new P1FibonacciOld().cachedFib(7));
        System.out.println("fib(7) = " + new P1FibonacciNew().fib(7));
        System.out.println("fib(7) = " + new P1FibonacciNew().lambdaFib(7));
    }
}
