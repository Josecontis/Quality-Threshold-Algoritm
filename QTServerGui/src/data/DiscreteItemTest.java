package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscreteItemTest {

	private DiscreteItem i;
	private DiscreteAttribute a;
	private TreeSet<String> v;

	@BeforeEach
	void setUp() throws Exception {
		v=new TreeSet<String>();
		a=new DiscreteAttribute(null, 0, v);
		i= new DiscreteItem(a, "yes");
	}

	@AfterEach
	void tearDown() throws Exception {
		i=null;
		a=null;
	}

	@Test
	void test() {
		assertEquals(0, i.distance("yes"));
	}

}
