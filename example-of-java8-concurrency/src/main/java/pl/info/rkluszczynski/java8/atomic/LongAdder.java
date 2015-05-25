package pl.info.rkluszczynski.java8.atomic;

public class LongAdder {
    private static java.util.concurrent.atomic.LongAdder adder;

    public static void main(String[] args) {
        adder = new java.util.concurrent.atomic.LongAdder();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                incrementOrDecrement();
                show();
            }, "" + i).start();
        }
    }

    private static void show() {
        System.out.println(Thread.currentThread().getName() + " value of adder " + adder.intValue());
    }

    private static void incrementOrDecrement() {
        int value = adder.intValue();
        if (value == 5) {
            System.out.println(Thread.currentThread().getName() + " decrementing value... ");
            adder.decrement();
        } else {
            System.out.println(Thread.currentThread().getName() + " incrementing value... ");
            adder.increment();
        }
    }
}
