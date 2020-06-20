package data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DataTest {

	Data d;

	@BeforeEach
	void setUp() throws Exception {

		String tablename="playtennis";
		d= new Data(tablename);
	}

	@AfterEach
	void tearDown() throws Exception {
		d=null;


	}

	@Test
	void testData() {
		assertNotNull(d);
	}

	@Test
	void testGetNumberOfExamples() {
		assertEquals(14,d.getNumberOfExamples());
	}

	@Test
	void testGetNumberOfAttributes() {
		assertEquals(5,d.getNumberOfAttributes());
	}

	@Test
	void testGetAttributeValue() {
		assertNotNull(d.getAttributeValue(0, 0));
	}

	@Test
	void testGetAttribute() {
		assertNotNull(d.getAttribute());
	}

	@Test
	void testToString() {
		assertEquals("   Outlook,  Temperature,  Humidity,  Wind,  PlayTennis,\r\n" + 
				"0: sunny,     30.3,     high,     weak,     no,     \r\n" + 
				"1: sunny,     30.3,     high,     strong,     no,     \r\n" + 
				"2: overcast,     30.0,     high,     weak,     yes,     \r\n" + 
				"3: rain,     13.0,     high,     weak,     yes,     \r\n" + 
				"4: rain,     0.0,     normal,     weak,     yes,     \r\n" + 
				"5: rain,     0.0,     normal,     strong,     no,     \r\n" + 
				"6: overcast,     0.1,     normal,     strong,     yes,     \r\n" + 
				"7: sunny,     13.0,     high,     weak,     no,     \r\n" + 
				"8: sunny,     0.1,     normal,     weak,     yes,     \r\n" + 
				"9: rain,     12.0,     normal,     weak,     yes,     \r\n" + 
				"10: sunny,     12.5,     normal,     strong,     yes,     \r\n" + 
				"11: overcast,     12.5,     high,     strong,     yes,     \r\n" + 
				"12: overcast,     29.21,     normal,     weak,     yes,     \r\n" + 
				"13: rain,     12.5,     high,     strong,     no,     \r\n" + 
				"",d.toString());
	}

	@Test
	void testGetItemSet() {
		assertNotNull(d.getItemSet(0));
	}

}
