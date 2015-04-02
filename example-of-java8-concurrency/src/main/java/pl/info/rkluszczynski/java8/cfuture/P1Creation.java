package pl.info.rkluszczynski.java8.cfuture;

import java.util.concurrent.CompletableFuture;

public class P1Creation {
    public static void main(String[] args) {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println("CFUTURE DONE");
            }
            return "100";
        });

        System.out.println("MAIN DONE");
    }
}
