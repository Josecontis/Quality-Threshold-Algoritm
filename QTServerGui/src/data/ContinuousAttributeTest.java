package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContinuousAttributeTest {

	private ContinuousAttribute a;

	@BeforeEach
	void setUp() throws Exception {
		a= new ContinuousAttribute("Temperature", 1, 0, 30.3);
	}

	@AfterEach
	void tearDown() throws Exception {
		a=null;
	}

	@Test
	void testGetScaledValue() {
		assertEquals(1, a.getScaledValue(30.3));
	}

}
