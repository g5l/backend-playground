package com.example;

import com.code_intelligence.jazzer.junit.FuzzTest;

public class CalculatorFuzzTest {
    Calculator calc = new Calculator();

    @FuzzTest
    void fuzzDivide(int a, int b) {
        try {
            calc.divide(a, b);
        } catch (ArithmeticException e) {
            // expected for b == 0
        }
    }

    @FuzzTest
    void fuzzParse(String input) {
        try {
            calc.unsafeParse(input);
        } catch (NumberFormatException e) {
            // expected for invalid numbers
        }
    }
}
