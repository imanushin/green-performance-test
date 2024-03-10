package com.github.imanushin.green.performance.benchmarks

import com.github.imanushin.green.performance.AsyncIncrementer
import kotlinx.coroutines.*
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@BenchmarkMode(Mode.All)
@Threads(100)
@Warmup
@Measurement
open class CompareInlineUseVsLambdaUse {
    @Benchmark
    fun coroutineClassic(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementer.coroutineClassic())
    }

    @Benchmark
    fun coroutineGreen(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementer.coroutineGreen())
    }

    @Benchmark
    fun classicThread(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementer.classicThread())
    }

    @Benchmark
    fun greenThread(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementer.greenThread())
    }
}