package data;
import java.io.Serializable;
import java.util.Set;
/**
 * @author Giuseppe
 */
public class Tuple implements Serializable{

	private static final long serialVersionUID = 1L;
	private Item [] tuple;

	/**
	 * @param size: number of tuple's item
	 * build an Object referenced by tuple
	 */
	public Tuple(int size)
	{
		this.tuple=new Item[size];
	}
	/**
	 * @return length of tuple
	 */
	public int getLength()
	{
		return tuple.length;
	}
	/**
	 * @param i: index
	 * @return Item: in index i
	 */
	public Item get(int i)
	{
		return tuple[i];
	}
	/**
	 * @param c: item
	 * @param i: index
	 * store c in tuple[i]
	 */
	void add(Item c,int i)
	{
		tuple[i]=c;
	}


	/**
	 * @param obj: tuple
	 * @return dist: distance between current tuple ad obj tuple
	 */
	public double getDistance(Tuple obj)
	{
		double dist=0.0;
		for(int i=0;i<obj.getLength();i++) 
		{
			if(tuple[i].getAttribute().getName().equals(obj.get(i).getAttribute().getName()))
			{
				dist+=this.get(i).distance(obj.get(i).getValue());//Fare uso di double distance(Object a) di Item
			}
		}
		return dist;
	}

	/**
	 * @param data: table that contain value of database table
	 * @param clusteredData: Set 
	 * @return avgD: average of distance between current tuple and data in index clusteredData
	 */
	public double avgDistance(Data data, Set<Integer> clusteredData)
	{
		double avgD=0.0,sumD=0.0;
		for (Integer it : clusteredData)
		{
			double distance= getDistance(data.getItemSet(it));
			sumD+=distance;
		}
		avgD=sumD/clusteredData.size();
		return avgD;
	}

	public String toString ()
	{
		String str = " ";
		for (int i = 0; i < this.getLength(); i++)
		{
			str += this.get(i) + " ";
		}
		return str;
	}

}

