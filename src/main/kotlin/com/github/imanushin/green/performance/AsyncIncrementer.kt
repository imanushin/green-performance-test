package com.github.imanushin.green.performance

import com.github.imanushin.green.performance.Constants.NUMBER_OF_DELAYS
import com.github.imanushin.green.performance.TestExecutors.classicThreadPool
import com.github.imanushin.green.performance.TestExecutors.greenThreadPool
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService

object AsyncIncrementer {
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