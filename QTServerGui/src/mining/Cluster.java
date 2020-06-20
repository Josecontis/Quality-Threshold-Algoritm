package mining;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import data.Data;
import data.Tuple;
/**
 * @author Giuseppe
 *
 */
class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable{

	private static final long serialVersionUID = 1L;
	private Tuple centroid;
	private Set<Integer> clusteredData; 

	/**constructor of class.
	 * @param centroid
	 */
	Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet<Integer>();

	}
	/**
	 * @return centroid: centroid of cluster
	 */
	Tuple getCentroid(){
		return centroid;
	}

	/**
	 * @param id
	 * @return Boolean: true, if the tuple change the cluster, else false
	 */
	boolean addData(int id){
		return clusteredData.add(id);

	}

	/**
	 * @param id
	 * @return Boolean: true, if the tuple is clustered in cluster, else false
	 */
	boolean contain(int id){
		return clusteredData.contains(id);
	}

	/**
	 * remove the tuple that have been changed the cluster
	 * @param id
	 */
	void removeTuple(int id){
		clusteredData.remove(id);

	}

	/**
	 * @return Size of cluster
	 */
	int  getSize(){
		return clusteredData.size();
	}


	public Iterator<Integer> iterator()
	{
		return clusteredData.iterator();
	}


	public String toString(){
		String str="Centroid=( ";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+" ";
		str+=")";
		return str;

	}

	public String toString(Data data){
		String str=" Centroid=( ";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")\nExamples:\n";
		for (Integer it: clusteredData)
		{
			str+="[";
			str+= data.getItemSet(it);
			str+="] dist="+getCentroid().getDistance(data.getItemSet(it))+"\n";

		}
		str+= "\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;

	}
	
	@Override
	public int compareTo(Cluster c) {
		if(clusteredData.size()==c.getSize())
		{
			return 0;
		}
		else
		{
			if(clusteredData.size()>c.getSize())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
	}	
}
