package data;
/**
 * @author Giuseppe
 * class to model Continuous Item
 */
public class ContinuousItem extends Item
{
	private static final long serialVersionUID = 1L;

	ContinuousItem(Attribute attribute, double value)
	{
		super(attribute, value);
	}
	/**
	 * @param a: parameter to calculate distance
	 * @return double: absolute distance between normalized 
	 * value this and parameter a
	 */
	double distance(Object a)
	{

		double currVal = ((ContinuousAttribute) this.getAttribute()).getScaledValue((Double) this.getValue());

		double aVal = ((ContinuousAttribute) this.getAttribute()).getScaledValue((Double) a);

		return Math.abs(currVal - aVal);
	}
}