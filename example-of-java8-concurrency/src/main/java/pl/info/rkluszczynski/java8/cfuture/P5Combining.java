package pl.info.rkluszczynski.java8.cfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class P5Combining {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // thenCombine offers the possibility to combine two completables that are totally
        // independent
        String login = "dani", password = "pass", land = "spain";
        CompletableFuture<Boolean> loginCompletable = checkLogin(login, password);
        CompletableFuture<Boolean> checkLandCompletable = checkLand(land);
        CompletableFuture<String> welcomeOrNot = loginCompletable.thenCombine(
                checkLandCompletable,
                (cust, shop) -> welcome(cust, shop));

        System.out.println(welcomeOrNot.get());
    }

    private static String welcome(Boolean login, Boolean land) {
        if (login && land)
            return "welcome";
        else
            return "not welcome";
    }

    private static CompletableFuture<Boolean> checkLand(String land) {
        // only Spanish are allowed
        return CompletableFuture.supplyAsync(() -> {
            // big task with back end dependencies
            return "spain".equals(land);
        });
    }

    private static CompletableFuture<Boolean> checkLogin(String login, String password) {
        return CompletableFuture.supplyAsync(() -> {
            // very hard authentication process
            return login != null && password != null;
        });
    }
}
