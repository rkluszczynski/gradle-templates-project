package com.danibuiza.javacodegeeks.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureGet
{

    public static void main( String[] args )
    {
        try
        {
            CompletableFuture<String> cf = new CompletableFuture<String>();

            // it is possible to get the number of dependents on the completable Future
            System.out.println( "number of dependents " + cf.getNumberOfDependents() );

            // to get directly the value, if not available, the passed value will be returned
            System.out.println( "get now " + cf.getNow( "now" ) );

            // to get it after waiting 3 seconds, an exception otherwise
            System.out.println( "get in 3 seconds " + cf.get( 3, TimeUnit.SECONDS ) );

            // get will wait for ever in this case
            // System.out.println( "get  " + cf.get() );

        }
        catch( ExecutionException | InterruptedException | TimeoutException ex )
        {
            ex.printStackTrace();
        }
    }

}
