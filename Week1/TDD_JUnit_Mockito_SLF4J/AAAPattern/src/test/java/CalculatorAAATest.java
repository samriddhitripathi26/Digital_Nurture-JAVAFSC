package Week1.TDD_JUnit_Mockito_SLF4J.AAAPattern.src.test.java;

import org.junit.jupiter.api.*;

import Week1.TDD_JUnit_Mockito_SLF4J.AAAPattern.src.main.java.Calculator;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorAAATest {

    Calculator c;

    @BeforeEach
    void setup() {
        c = new Calculator();
    }

    @AfterEach
    void tearDown() {
        c = null;
    }

    @Test
    void testMultiply() {

        // Arrange
        int a = 5;
        int b = 4;

        // Act
        int result = c.multiply(a, b);

        // Assert
        assertEquals(20, result);
    }
}