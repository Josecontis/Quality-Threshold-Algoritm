package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscreteAttributeTest {

	private DiscreteAttribute da;
	private TreeSet<String> v;

	@BeforeEach
	void setUp() throws Exception {
		v=new TreeSet<String>();
		da=new DiscreteAttribute(null, 0, v);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNumberOfDistinctValues() {
		assertEquals(0, da.getNumberOfDistinctValues());

	}

}
