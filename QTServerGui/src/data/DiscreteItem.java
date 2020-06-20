package data;
/**
 * @author Giuseppe
 * This class model a couple for example Outlook=”Sunny”
 */
public class DiscreteItem extends Item
{

	private static final long serialVersionUID = 1L;

	public DiscreteItem(Attribute attribute, Object value)
	{
		super(attribute, value);
	}
	/**
	 * @return double: 0 if(getValue().equals(a)), else 1
	 */
	double distance(Object a)
	{
		if(getValue().equals(a))
		{
			return 0;
		}
		else 
		{
			return 1;
		}

	}
}
