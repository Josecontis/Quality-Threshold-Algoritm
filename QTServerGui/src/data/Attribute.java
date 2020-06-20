package data;

import java.io.Serializable;
/**
 * @author Giuseppe
 */
public abstract class Attribute implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int index;
	/**
	 * @param name: name of attribute
	 * @param index: position of attribute
	 */
	public Attribute(String name, int index)
	{
		this.name = name;
		this.index = index;
	};

	String getName() 
	{
		return name;
	};

	int getIndex()
	{
		return index;
	};
	/**
	 * @return name: String name 
	 */
	public String toString()
	{
		return name;
	};


}
