package pl.info.rkluszczynski.tutorial.java8.concurrency

class StopWatchUtil {

    static long timing(Closure callable) {
        long start = System.currentTimeMillis()
        callable.call()
        long end = System.currentTimeMillis()

        return end - start
    }
}
