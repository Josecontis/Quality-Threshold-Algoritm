package mining;

import static org.junit.jupiter.api.Assertions.*;

import data.Tuple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Data;
class ClusterTest {

	private Cluster c;
	private Cluster c1;
	Tuple ctr = new Tuple(2);

	@BeforeEach
	void setUp() throws Exception {
		c= new Cluster(ctr);
		c1= new Cluster(ctr);
	}

	@AfterEach
	void tearDown() throws Exception {
		c=null;
		c1=null;
		ctr=null;
	}

	@Test
	void testGetCentroid() {
		assertEquals(ctr, c.getCentroid());
	}

	@Test
	void testAddData() {
		assertTrue(c.addData(1));
	}

	@Test
	void testContain() {
		c.addData(1);
		assertTrue(c.contain(1));
	}

	@Test
	void testRemoveTuple() {
		c.removeTuple(1);
		assertEquals(0,c.getSize());
	}

	@Test
	void testGetSize() {
		c.addData(1);
		assertEquals(1,c.getSize());
	}

	@Test
	void testIterator() {
		assertFalse(c.iterator().hasNext());
	}

	@Test
	void testToString() {
		assertEquals("Centroid=( null null )",c.toString());
	}

	@Test
	void testToStringData() {
		Data data=null;
		assertEquals(" Centroid=( null null )\n"
				+ "Examples:\n\n"

				+ "AvgDistance=NaN",c.toString(data));
	}

	@Test
	void testCompareTo() {
		c=c1;
		assertEquals(0, c.compareTo(c1));
		c.addData(0);
		assertEquals(1, c.compareTo(c1));
		c1=c;
		c1.addData(0);
		assertEquals(-1, c.compareTo(c1));
	}

}
