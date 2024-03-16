package com.github.imanushin.green.performance.benchmarks;

import com.github.imanushin.green.performance.AsyncIncrementerJ;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.Throughput)
@Threads(100)
@Warmup
@Measurement
public class ToGreenOrNotToGreenJ {
    @Benchmark
    int classicThread() {
        return AsyncIncrementerJ.classicThread();
    }

    @Benchmark
    int greenThread() {
        return AsyncIncrementerJ.greenThread();
    }

    @Benchmark
    int classicThreadAsync() {
        return AsyncIncrementerJ.classicThreadAsync();
    }

    @Benchmark
    int greenThreadAsync() {
        return AsyncIncrementerJ.greenThreadAsync();
    }
}
