package com.adnanafzalbajwa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("Before All Setup Method");
    }

    @BeforeEach
    public void objectsInitialization() {
        calculator = new Calculator();
    }

    @AfterEach
    public void objectsCleanUp() {
        System.out.println("After Each Method");
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("After All Method");
    }

    @DisplayName("Divide Valid Inputs, Valid Output")
    @Test
    public void divideTest_WhenValidInputs_ReturnValidOutput() {
        double result = calculator.divide(10, 5);
        assertEquals(2, result, "10 divide by 2 should be 5");
    }

    @DisplayName("Divide by Zero, Arithmetic Exception")
    @Test
    public void divideTest_WhenDivideByZero_ThrowArithmeticException() {
        int dividend = 9;
        int divisor = 0;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.divide(dividend, divisor),
                "Divide by zero should give Arithmetic Exception");

        assertEquals("/ by zero", exception.getMessage());
    }

    @DisplayName("Subtract Valid Inputs, Valid Output (Method Source)")
    @ParameterizedTest
    @MethodSource()
    public void divideSubtract_WhenValidInputs_ReturnValidOutput(int a, int b, int expectedResult) {

        double result = calculator.subtract(a, b);
        assertEquals(expectedResult, result, () -> a + " subtract " + b + " should be " + expectedResult);
    }

    private static Stream<Arguments> divideSubtract_WhenValidInputs_ReturnValidOutput() {
        return Stream.of(
                Arguments.of(5, 3, 2),
                Arguments.of(7, 7, 0),
                Arguments.of(33, 2, 31)

        );
    }

    @DisplayName("Subtract Valid Inputs, Valid Output (CSV Source)")
    @ParameterizedTest
    @CsvSource({
            "33,22,11",
            "50,22,28",
            "18,17,1",
    })
    public void divideSubtract_WhenValidInputsFromCsvSource_ReturnValidOutput(int a, int b, int expectedResult) {

        double result = calculator.subtract(a, b);
        assertEquals(expectedResult, result, () -> a + " subtract " + b + " should be " + expectedResult);
    }

}