package pl.info.rkluszczynski.java8.cfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class P4Joining {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFutureBigCompute = CompletableFuture.supplyAsync(() -> {
            return "Kepler in Java 8";
        });

        // we can use thenCompose and thenApply to wait until a completable is ready and start with
        // the next one using the value of the first one as parameter
        CompletableFuture<String> thenCompose = completableFutureBigCompute
                .thenCompose(P4Joining::toLowerCaseFuture);

        CompletableFuture<CompletableFuture<String>> thenApply = completableFutureBigCompute
                .thenApply(P4Joining::toUpperCaseFuture);

        System.out.println("thenCompose " + thenCompose.get());
        System.out.println("thenApply " + thenApply.get()); // is already completed so the value
        // was used by the compose method

        System.out.println("thenApply " + thenApply.isDone()); // is already completed

        CompletableFuture<String> thenCompose2 = completableFutureBigCompute
                .thenCompose(P4Joining::toLowerCaseFuture);

        // difference between compose and apply
        System.out.println("thenCompose2 " + thenCompose2.get()); // then compose uses the value
        // of the source completable,
        // this is the main difference
    }

    private static CompletableFuture<String> toUpperCaseFuture(String str) {
        return CompletableFuture.supplyAsync(() -> {
            return str.toUpperCase();
        });
    }

    private static CompletableFuture<String> toLowerCaseFuture(String str) {
        return CompletableFuture.supplyAsync(() -> {
            return str.toLowerCase();
        });
    }
}
