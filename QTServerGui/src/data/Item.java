package data;

import java.io.Serializable;
/**
 * @author Giuseppe
 */
public abstract class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	public Attribute attribute;
	public Object value;

	Item(Attribute attribute, Object value)
	{
		this.attribute=attribute;
		this.value=value;
	}
	/**
	 * @return attribute
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @return String: name of value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	abstract double distance(Object a);
}
