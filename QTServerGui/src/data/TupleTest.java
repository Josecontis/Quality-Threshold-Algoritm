package data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TupleTest {

	private Tuple t;
	private Data data;
	private Set<Integer> clusteredData = new HashSet<Integer>();
	private Item [] tuple;
	private Item c;
	
	@BeforeEach
	void setUp() throws Exception {
		t=new Tuple(2);		
		c=null;
		tuple=new Item[] {c,c};
	}

	@Test
	void testAdd() {
		Item c = null;
		t.add(c,1);
		assertEquals(tuple[1], t.get(0));
	}

	@Test
	void testGet() {
		assertEquals(null,t.get(0));
	}


	@Test
	void testGetDistance() {
		assertEquals(4.0,t.getDistance(t));
	}

	@Test
	void testAvgDistance() {
		assertNotNull(t.avgDistance(data, clusteredData));
	}

	@Test
	void testToString() {
		assertEquals(" null null ",t.toString());
	}
}
