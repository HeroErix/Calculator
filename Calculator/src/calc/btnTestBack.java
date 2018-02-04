package calc;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.ActionEvent;

import org.junit.Test;

public class btnTestBack {

	@Test
	public void test() {
		CalculatorGUI test = new CalculatorGUI();
		test.actionPerformed(new ActionEvent(test, 0, "1"));
		test.actionPerformed(new ActionEvent(test, 0, "+"));
		test.actionPerformed(new ActionEvent(test, 0, "1"));
		test.actionPerformed(new ActionEvent(test, 0, "Back"));
		assertEquals("1+",test.equation);
	}
}
