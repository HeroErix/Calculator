package calc;

import static org.junit.Assert.*;

import org.junit.Test;

public class solveTest {

	@Test
	public void test() {
		CalculatorGUI test = new CalculatorGUI();
		Object result = test.eval("5+8/2");
		assertEquals(9.0,result);
	}

}
