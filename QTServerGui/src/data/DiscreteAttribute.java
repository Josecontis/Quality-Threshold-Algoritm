package data;

import java.util.Iterator;
import java.util.TreeSet;
/**
 * @author Giuseppe
 */
public class DiscreteAttribute extends Attribute implements Iterable<String>
{
	private static final long serialVersionUID = 1L;
	private TreeSet<String> values;

	public DiscreteAttribute(String name, int index, TreeSet<String> values) 
	{
		super(name,index);
		this.values=values;
	}
	/**
	 * @return size of treeset values
	 */
	int getNumberOfDistinctValues()
	{
		return values.size();
	}

	@Override
	/**
	 * @return position of treeset values
	 */
	public Iterator<String> iterator()
	{
		return values.iterator();
	}
}
