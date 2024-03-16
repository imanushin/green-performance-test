package com.github.imanushin.green.performance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static com.github.imanushin.green.performance.Constants.NUMBER_OF_DELAYS;
import static com.github.imanushin.green.performance.TestExecutors.classicThreadPool;
import static com.github.imanushin.green.performance.TestExecutors.greenThreadPool;
import static java.util.concurrent.CompletableFuture.completedFuture;

public final class AsyncIncrementerJ {
    private static final Executor DELAYED_CLASSIC = CompletableFuture.delayedExecutor(1, TimeUnit.MILLISECONDS, classicThreadPool);
    private static final Executor DELAYED_GREEN = CompletableFuture.delayedExecutor(1, TimeUnit.MILLISECONDS, classicThreadPool);

    public static int classicThread() {
        return runThread(classicThreadPool);
    }

    public static int greenThread() {
        return runThread(greenThreadPool);
    }

    public static int classicThreadAsync() {
        return runAsync(DELAYED_CLASSIC);
    }

    public static int greenThreadAsync() {
        return runAsync(DELAYED_GREEN);
    }

    private static int runAsync(Executor executor) {
        final var last = addNumbersRecursive(0, executor);

        try {
            return last.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static int runThread(ExecutorService executorService) {
        final var future = executorService.submit(() -> {
            var index = 0;

            for (int i = 0; i < NUMBER_OF_DELAYS; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                index++;
            }

            return index;
        });

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<Integer> addNumbersRecursive(final int index, final Executor delayedExecutor) {
        if (index < NUMBER_OF_DELAYS) {
            return CompletableFuture.supplyAsync(() -> index + 1, delayedExecutor) // wait and add in one call
                    .thenCompose(current -> addNumbersRecursive(current, delayedExecutor));
        } else {
            return completedFuture(index);
        }
    }
}
