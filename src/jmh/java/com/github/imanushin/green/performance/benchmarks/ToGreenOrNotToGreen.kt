package com.github.imanushin.green.performance.benchmarks

import com.github.imanushin.green.performance.AsyncIncrementer
import com.github.imanushin.green.performance.AsyncIncrementerWithNestedMethods
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Threads
import org.openjdk.jmh.annotations.Warmup

@BenchmarkMode(Mode.Throughput)
@Threads(100)
@Warmup
@Measurement
open class ToGreenOrNotToGreen {
    @Benchmark
    fun coroutineClassic(): Int {
        return AsyncIncrementer.coroutineClassic()
    }

    @Benchmark
    fun coroutineGreen(): Int {
        return AsyncIncrementer.coroutineGreen()
    }

    @Benchmark
    fun classicThread(): Int {
        return AsyncIncrementer.classicThread()
    }

    @Benchmark
    fun greenThread(): Int {
        return AsyncIncrementer.greenThread()
    }

    @Benchmark
    fun coroutineClassicWithNestedMethods(): Int {
        return AsyncIncrementerWithNestedMethods.coroutineClassic()
    }

    @Benchmark
    fun coroutineGreenWithNestedMethods(): Int {
        return AsyncIncrementerWithNestedMethods.coroutineGreen()
    }

    @Benchmark
    fun classicThreadWithNestedMethods(): Int {
        return AsyncIncrementerWithNestedMethods.classicThread()
    }

    @Benchmark
    fun greenThreadWithNestedMethods(): Int {
        return AsyncIncrementerWithNestedMethods.greenThread()
    }
}