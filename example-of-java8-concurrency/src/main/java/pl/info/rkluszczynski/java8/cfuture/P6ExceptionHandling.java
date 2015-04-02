package pl.info.rkluszczynski.java8.cfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class P6ExceptionHandling {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFutureException = CompletableFuture.supplyAsync(() -> {
            return 10 / 2;
            // return 10/0 // produces an exception, division by zero
        });

        CompletableFuture<Integer> fallback = completableFutureException.exceptionally(x -> 0);
        System.out.println("results: " + fallback.get());

        CompletableFuture<Integer> completableFutureHandleOk = CompletableFuture.supplyAsync(() -> {
            return 10 / 0; // exception division by zero
            // return 10 / 2;
        });

        CompletableFuture<Integer> handleOkError = completableFutureHandleOk.handle((ok, ex) -> {
            if (ok != null) {
                // return the value if everything ok
                return ok;
            } else {
                // in case of an exception print the stack trace and return null
                ex.printStackTrace();
                return null;
            }
        });
        System.out.println("ok or error ? " + handleOkError.get());
    }
}
