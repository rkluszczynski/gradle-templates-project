package com.danibuiza.javacodegeeks.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureNeverEndsExample
{

    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
        // you can create a completable future directly using its constructor
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();

        // if we try to use it now, our program will never end
        completableFuture.get();

    }

}
