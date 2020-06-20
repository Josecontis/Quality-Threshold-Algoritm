package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExampleTest {
	private Example e;
	private  List<Object> example;

	@BeforeEach
	void setUp() throws Exception {
		e=new Example();
		example=new ArrayList<Object>();
	}

	@AfterEach
	void tearDown() throws Exception {
		example.clear();
	}

	@Test
	void testAdd() {
		assertEquals(0, example.size());
		example.add(1);
		assertEquals(1, example.size());
		example.add(2);
		assertEquals(2, example.size());
	}

	@Test
	void testGet() {
		int i=0;
		e.add(1);
		assertEquals(1, e.get(i));
	}

	@Test
	void testCompareTo() {
		e.add(" ");
		assertEquals(0, e.compareTo(e));
	}

	@Test
	void testToString() {
		e.add(" ");
		assertEquals("  ",e.toString());
	}

}
