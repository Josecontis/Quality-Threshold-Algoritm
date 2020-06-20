package data;
/**
 * @author Giuseppe
 */
public class ContinuousAttribute extends Attribute{

	private static final long serialVersionUID = 1L;

	/**
	 * min, max: extremes of the domain range that the attribute can assume
	 */
	private double max;
	private double min;

	ContinuousAttribute(String name, int index, double min, double max)
	{
		super(name,index);
		this.min = min;
		this.max = max;
	}

	/**
	 * @param v: numeric value to normalize
	 * @return V: Normalized value of v in range [0,1]
	 */
	double getScaledValue(double v)
	{
		double V;
		V=(v-min)/(max-min);
		return V;
	}
}