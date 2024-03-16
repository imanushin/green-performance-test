package com.github.imanushin.green.performance

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestFunctions {
    @ParameterizedTest
    @MethodSource("testCases")
    fun testIfReturnsExpectedResult(functionToTest: () -> Int) {
        // when
        val result = functionToTest()

        // then
        assertEquals(100, result)
    }

    @Suppress("UnusedPrivateMember")
    private fun testCases(): Stream<Arguments> {
        return listOf(
            AsyncIncrementer::greenThread,
            AsyncIncrementer::classicThread,
            AsyncIncrementer::coroutineClassic,
            AsyncIncrementer::coroutineGreen,

            AsyncIncrementerWithNestedMethods::greenThread,
            AsyncIncrementerWithNestedMethods::classicThread,
            AsyncIncrementerWithNestedMethods::coroutineClassic,
            AsyncIncrementerWithNestedMethods::coroutineGreen,

            AsyncIncrementerJ::greenThread,
            AsyncIncrementerJ::classicThread,
            AsyncIncrementerJ::greenThreadAsync,
            AsyncIncrementerJ::classicThreadAsync
        ).map { Arguments.of(it) }
            .stream()
    }
}
