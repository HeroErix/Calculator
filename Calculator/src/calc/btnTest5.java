package calc;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.ActionEvent;

import org.junit.Test;

public class btnTest5 {

	@Test
	public void test() {
		CalculatorGUI test = new CalculatorGUI();
		test.actionPerformed(new ActionEvent(test, 0, "5"));
		assertEquals("5",test.equation);
	}
}
