package pl.info.rkluszczynski.java8.cfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class P2GetResult {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            return "100";
        });

        System.out.println("get  " + cf.get());
        System.out.println("get now " + cf.getNow("now"));
        System.out.println("get in 3 seconds " + cf.get(3, TimeUnit.SECONDS));

        System.out.println("MAIN DONE");
    }
}
