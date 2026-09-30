package com.mycompany.mathutil.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class MathUtilTestFromcsv {

    // 1. Đọc dữ liệu từ file CSV bên ngoài
    @ParameterizedTest
    @CsvFileSource(
        resources = "/data/factorial_test_data.csv",
        numLinesToSkip = 1
    )
    void testGetFactorial_ValidInput_FromCsv(int input, long expected) {
        assertEquals(expected, MathUtil.getFactorial(input));
    }

    // 2. Kiểm thử các giá trị biên (Boundary Values) bằng @CsvSource
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "20, 2432902008176640000"
    })
    void testGetFactorial_BoundaryValues(int input, long expected) {
        assertEquals(expected, MathUtil.getFactorial(input));
    }

    // 3. Kiểm thử các trường hợp ném ngoại lệ bằng @CsvSource
    @ParameterizedTest
    @CsvSource({
        "-1",
        "21"
    })
    void testGetFactorial_InvalidInput_ThrowsException(int input) {
        assertThrows(
            IllegalArgumentException.class,
            () -> MathUtil.getFactorial(input)
        );
    }
}
