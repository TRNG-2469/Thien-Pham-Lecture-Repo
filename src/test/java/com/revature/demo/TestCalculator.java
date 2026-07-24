package com.revature.demo;
import org.junit.jupiter.api.*;

public class TestCalculator {

    // make sure to follow AAA

    // make a new reference var name calculator
    Calculator calculator = null;

    // before each test i want a new fresh object to test each method
    @BeforeEach
    public void setup(){
        // create a new object
        System.out.println("Create fresh object before every test");
        calculator = new Calculator();

    }

    @Test
    public void testAdd() {
        // postive test cases
        int a = 10;
        int b = 10;
        int expectedResult = 20;

        // act
        int actualResult = calculator.add(a, b);

        //Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddingNegative(){
        int a = -10;
        int b = -12;
        int expectedResult = -22;
        int actualResult = calculator.add(a, b);

        Assertions.assertEquals(expectedResult, actualResult);

    }

    //edge case
    @Test
    public void testAddingZero(){
        int a = 0;
        int b = 12;
        int expectedResult = 12;
        int actualResult = calculator.add(a, b);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testSubtractSmallToBigNumber(){
        int a = 12;
        int b = 20;
        int expectedOutput = -8;
        int actualOutput = calculator.subtract(a, b);

        // check whether the expected is = to the actual
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSubtractNegative(){
        int a = -12;
        int b = -20;
        int expectedOutput = 8;

        int actualOutPut = calculator.subtract(a, b);

        Assertions.assertEquals(expectedOutput, actualOutPut);
    }

    @Test
    public void testMultiply(){
        int a = 15;
        int b = 2;
        int expectedOutput = 30 ;
        int actualOutput = calculator.multiply(a, b);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDivide(){
        int a = 12;
        int b = 2;
        int expectedOutput = 6;
        int actualOutput = calculator.divide(a, b);
        Assertions.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void testDivideByZero(){
        int a = 10;
        int b = 0;

        // i expect this code to throw an exception
        Assertions.assertThrows(
                //  ArithmeticException.class is my expected type
                ArithmeticException.class, () -> calculator.divide(a, b) // execute this code here
        );
    }

    // after each test clean up the referecen variable
    @AfterEach
    public void teardown(){
        calculator = null;
        System.out.println("4. Runs after each test");
    }







    //plugin  that report for your test cases, name of the plugin AllureReports (if have time )









}
