package mining;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.Data;
/**
 * @author Giuseppe
 */
public class ClusterSet implements Iterable<Cluster>, Serializable{

	private static final long serialVersionUID = 1L;
	private Set<Cluster> C;
	/**
	 * contructor of class
	 */
	ClusterSet()
	{
		C = new TreeSet<Cluster>();
	} 

	void add(Cluster c)
	{
		C.add(c);
	}
	/**
	 * @return String: that contain centroid of clusterset C.
	 */
	public String toString(){
		String output="";
		int j=1;
		for(Cluster it: C)
		{
			output+=j+": "+it+"\n";
			j++;
		}
		return output;
	}
	/**
	 * @param data:
	 * @return String: that contain the statement of each cluster in C.
	 */
	public String toString(Data data)
	{
		String output = "";
		int i = 0;
		Iterator<Cluster> CIterator = C.iterator();
		while (CIterator.hasNext())
		{
			output += i+1 + ":" + CIterator.next().toString(data) + "\n";
			i++;
		}

		return output;
	}

	@Override
	public Iterator<Cluster> iterator()
	{
		return C.iterator();
	}

}
