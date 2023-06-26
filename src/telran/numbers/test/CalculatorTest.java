package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import telran.numbers.CalcData;
import telran.numbers.Calculator;

class CalculatorTest {

	@Test
	void test() {
		BinaryOperator<Double> bo = (a, b) -> a + b;
		assertEquals(30, bo.apply(10.0, 20.0));
		DoubleBinaryOperator[] operators = {
				(a, b) -> a + b,
				(a, b) -> a - b,
				(a, b) -> a * b,
				(a, b) -> { 
					if (b == 0) {
						throw new ArithmeticException("division on 0");
					}
					return a / b;
				}
		};
		Double[] results = {30.0, 10.0, 200.0, 2.0};
		double op1 = 20;
		double op2 = 10;
		for (int i = 0; i < operators.length; i++) {
			operators[i].apply(op1, op2);
			assertEquals(results[i], operators[i].apply(op1, op2));
		}
		}
	@Test
	void calculateTest() {
		assertEquals(20, Calculator.calculate(new CalcData(40, 20, '-')));
		assertThrowsExactly(UnsupportedOperationException.class, () -> Calculator.calculate(new CalcData(40, 20, '&')));
		assertThrowsExactly(ArithmeticException.class, () -> Calculator.calculate(new CalcData(40, 0, '/')));
	}
}

interface DoubleBinaryOperator extends BinaryOperator <Double> {
	
}