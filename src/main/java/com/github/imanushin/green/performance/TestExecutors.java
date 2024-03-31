package com.github.imanushin.green.performance;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class TestExecutors {
    /**
     * Create a very big thread pool to avoid waiting for free thread.
     */
    private static final int THREAD_POOL_SIZE = 1000;

    /**
     * JMH complains that some threads continue running
     */
    private static final ThreadFactory daemonThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(@NotNull Runnable runnable) {
            final var thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    };

    static final ExecutorService classicThreadPool = Executors.newScheduledThreadPool(THREAD_POOL_SIZE, daemonThreadFactory);
    static final ExecutorService greenThreadPool = Executors.newVirtualThreadPerTaskExecutor();
}
