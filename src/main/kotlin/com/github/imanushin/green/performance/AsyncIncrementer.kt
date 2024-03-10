package com.github.imanushin.green.performance

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object AsyncIncrementer {
    private const val THREAD_POOL_SIZE = 10
    private const val NUMBER_OF_DELAYS = 100

    private val classicThreadPool = Executors.newScheduledThreadPool(THREAD_POOL_SIZE)
    private val greenThreadPool = Executors.newVirtualThreadPerTaskExecutor()

    private val classicThreadDispatcher = classicThreadPool.asCoroutineDispatcher()
    private val greenThreadDispatcher = greenThreadPool.asCoroutineDispatcher()

    fun coroutineClassic(): Int {
        return runCoroutine(classicThreadDispatcher)
    }

    fun coroutineGreen(): Int {
        return runCoroutine(greenThreadDispatcher)
    }

    fun classicThread(): Int {
        return runThread(classicThreadPool)
    }

    fun greenThread(): Int {
        return runThread(greenThreadPool)
    }

    private fun runThread(executorService: ExecutorService): Int {
        return executorService.submit<Int> {
            var index = 0
            (0..NUMBER_OF_DELAYS).forEach { _ ->
                Thread.sleep(1)
                index++
            }
            index
        }.get()
    }

    private fun runCoroutine(coroutineDispatcher: CoroutineDispatcher): Int {
        return runBlocking {
            withContext(coroutineDispatcher) {
                var index = 0
                (0..NUMBER_OF_DELAYS).forEach { _ ->
                    delay(1)
                    index++
                }
                index
            }
        }
    }
}