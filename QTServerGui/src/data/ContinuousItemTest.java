package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContinuousItemTest {

	private ContinuousItem i;
	private ContinuousAttribute a;

	@BeforeEach
	void setUp() throws Exception {

		a= new ContinuousAttribute("Temperature", 1, 0, 30.3);
		i= new ContinuousItem(a, 30.3);
	}

	@AfterEach
	void tearDown() throws Exception {
		i=null;
		a=null;
	}

	@Test
	void testDistance() {
		assertEquals(0.0, i.distance(30.3));
	}

}
