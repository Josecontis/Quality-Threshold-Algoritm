package mining;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Data;
import database.DbAccess;

class QTMinerTest {

	private double r=3;
	private QTMiner q;
	private QTMiner q1;
	String table="playtennis";
	String f="playtennis2.0.dmp"; 
	String f1="playtennis2.0.dmp";
	private Data data;
	boolean isClustered[];
	Connection con;
	ResultSet res;

	@BeforeEach
	void setUp() throws Exception {
		DbAccess.initConnection();		
		con=DbAccess.getConnection();
		DatabaseMetaData meta = con.getMetaData();
		res = meta.getColumns(null, null, table, null);

		q=new QTMiner(r);
		data= new Data(table);
		isClustered= new boolean[data.getNumberOfExamples()];
	}

	@AfterEach
	void tearDown() throws Exception {
		r=0.0;
		f=null;
		f1=null;
		q=null;
		q1=null;
		table=null;
		data=null;
		con=null;
		res=null;
	}

	@Test
	void testQTMinerString() throws FileNotFoundException, ClassNotFoundException, IOException {
		q1= new QTMiner(f);
		assertNotNull(q1,f);
	}

	@Test
	void testSalva() throws FileNotFoundException, IOException {
		q.salva(f1);
		assertEquals(f,f1);
	}

	@Test
	void testGetC() {
		assertNotNull(q.getC());
	}

	@Test
	void testCompute() throws ClusteringRadiusException {
		assertEquals(2,q.compute(data)); //equal 2 because this.r=3
	}

	@Test
	void testBuildCandidateCluster() {
		assertEquals("Centroid=( overcast 30.0 high weak yes )", q.buildCandidateCluster(data, isClustered).toString());
	}

	@Test
	void testToString() {
		assertNotNull(q.toString());
	}

}
