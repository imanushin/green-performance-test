package com.github.imanushin.green.performance.benchmarks

import com.github.imanushin.green.performance.AsyncIncrementer
import com.github.imanushin.green.performance.AsyncIncrementerWithNestedMethods
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Threads
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.infra.Blackhole

@BenchmarkMode(Mode.All)
@Threads(100)
@Warmup
@Measurement
open class ToGreenOrNotToGreen {
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

    @Benchmark
    fun coroutineClassicWithNestedMethods(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementerWithNestedMethods.coroutineClassic())
    }

    @Benchmark
    fun coroutineGreenWithNestedMethods(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementerWithNestedMethods.coroutineGreen())
    }

    @Benchmark
    fun classicThreadWithNestedMethods(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementerWithNestedMethods.classicThread())
    }

    @Benchmark
    fun greenThreadWithNestedMethods(blackhole: Blackhole) {
        blackhole.consume(AsyncIncrementerWithNestedMethods.greenThread())
    }
}