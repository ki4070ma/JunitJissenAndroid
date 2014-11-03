package junit.tutorial;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void multiply‚Å3‚Æ4‚ÌæZŒ‹‰Ê‚ªæ“¾‚Å‚«‚é() {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(3, 4);
		assertThat(actual, is(expected));
	}

	@Test
	public void multiply‚Å5‚Æ7‚ÌæZŒ‹‰Ê‚ªæ“¾‚Å‚«‚é() {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(5, 7);
		assertThat(actual, is(expected));
	}
}
