package com.mycompany.mathutil.core;

import static com.mycompany.mathutil.core.MathUtil.getFactorial;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MathUtilTest {

    // 1. Kiểm thử các trường hợp dữ liệu hợp lệ (Normal Cases)
    @Test
    public void testGetFactorialGivenRightArgumentReturnsWell() {
        assertEquals(1, getFactorial(0)); // 0! = 1
        assertEquals(1, getFactorial(1)); // 1! = 1
        assertEquals(2, getFactorial(2)); // 2! = 2
        assertEquals(6, getFactorial(3)); // 3! = 6
        assertEquals(24, getFactorial(4)); // 4! = 24
        assertEquals(120, getFactorial(5)); // 5! = 120
        assertEquals(720, getFactorial(6)); // 6! = 720
    }

    // 2. Kiểm thử bắt ngoại lệ khi truyền tham số sai (Abnormal Case)
    @Test
    public void testGetFactorialGivenWrongArgumentThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> getFactorial(-5)
        );
        assertEquals("n must be between 0 .. 20", ex.getMessage());
    }

    // 3. Kỹ thuật DDT (Data-Driven Testing) với @MethodSource
    public static Object[][] initData() {
        return new Integer[][] {
            {1, 1},
            {2, 2},
            {5, 120},
            {6, 720},
            {4, 24}
        };
    }

    @ParameterizedTest
    @MethodSource("initData")
    public void testGetFactorialGivenRightArgReturnWell(int input, long expected) {
        assertEquals(expected, getFactorial(input));
    }
}