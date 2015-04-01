package com.danibuiza.javacodegeeks.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureFactories
{

    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {

        // we can create a completable in asynchronus mode and pass it directly a task to do
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync( ( ) -> {
            // big task
                return "100";
            } );

        // it is possible to "join" completables, even if their type is not the same
        CompletableFuture<Integer> thenApplyThis = completableFuture.thenApply( Integer::parseInt );
        // several times
        CompletableFuture<Double> andThenThis = thenApplyThis.thenApply( r -> r * 10.0 );

        // the completables are going to be executed one after the other
        System.out.println( "third " + andThenThis.get() );
        System.out.println( "second " + thenApplyThis.get() );
        System.out.println( "first " + completableFuture.get() );

        // it is also possible to combine completables using lambdas
        completableFuture.thenAcceptAsync( x -> x.length() );
        System.out.println( "Lambdas: " + completableFuture.get() );

        // if we want to handle exceptions there are to ways mainly
        CompletableFuture<Integer> completableFutureException = CompletableFuture.supplyAsync( ( ) -> {
            // big task
                return 10 / 2;
                // return 10/0 // produces an exception, division by zero
            } );

        // directly providing a fallback
        CompletableFuture<Integer> fallback = completableFutureException.exceptionally( x -> 0 );
        System.out.println( "results: " + fallback.get() );

        CompletableFuture<Integer> completableFutureHandleOk = CompletableFuture.supplyAsync( ( ) -> {
            // big task
                return 10 / 0; // exception division by zero
                // return 10 / 2;
            } );

        // or handling exceptions in case they arise
        CompletableFuture<Integer> handleOkError = completableFutureHandleOk.handle( ( ok, ex ) -> {
            if( ok != null )
            {
                // return the value if everything ok
                return ok;
            }
            else
            {
                // in case of an exception print the stack trace and return null
                ex.printStackTrace();
                return null;
            }
        } );

        System.out.println( "ok or error ? " + handleOkError.get() );
    }

}
