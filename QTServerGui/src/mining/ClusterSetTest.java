package mining;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Data;

class ClusterSetTest {
	private Cluster c;
	private ClusterSet cs;

	@BeforeEach
	void setUp() throws Exception {
		c= new Cluster(null);
		cs=new ClusterSet();
	}

	@AfterEach
	void tearDown() throws Exception {
		c=null;
		cs=null;
	}

	@Test
	void testAdd() {
		cs.add(c);
		assertNotNull(cs.iterator().next());
	}

	@Test
	void testToString() {
		assertEquals("",cs.toString());
	}

	@Test
	void testToStringData() {
		Data data=null;
		assertEquals("",cs.toString(data));
	}

	@Test
	void testIterator() {
		assertFalse(cs.iterator().hasNext());
	}

}
