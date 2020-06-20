package data;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.TableData;
/**
 * @author Giuseppe
 */
public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * data: table that contain value of database table.
	 * attributeSet: list that contain attributes of database table.
	 */
	private List<Example> data;
	private List<Attribute> attributeSet;

	public Data(String table) throws EmptyDatasetException, SQLException, EmptySetException{

		data=new ArrayList<Example>();
		attributeSet = new LinkedList<Attribute>();

		//memorizzo le transazioni secondo lo schema della tabella

		TreeSet<String> outLookValues=new TreeSet<String>();
		outLookValues.add("overcast");
		outLookValues.add("rain");
		outLookValues.add("sunny");
		//avvalorare elementi di attributeSet con un oggetto della classe DiscreteAttribute
		//apparte il Temperature che sarà avvalorato con un oggetto della classe ContinuousAttribute
		attributeSet.add(0, new DiscreteAttribute("Outlook",0, outLookValues));

		attributeSet.add(1, new ContinuousAttribute("Temperature",1, 0, 30.0));

		TreeSet<String> humidityValues=new TreeSet<String>();
		humidityValues.add("high");
		humidityValues.add("normal");
		attributeSet.add(2, new DiscreteAttribute("Humidity",2, humidityValues));


		TreeSet<String> windValuesValues=new TreeSet<String>();
		windValuesValues.add("weak");
		windValuesValues.add("strong");
		attributeSet.add(3, new DiscreteAttribute("Wind",3, windValuesValues));


		TreeSet<String> playTennisValues=new TreeSet<String>();
		playTennisValues.add("no");
		playTennisValues.add("si");
		attributeSet.add(4, new DiscreteAttribute("PlayTennis",4, playTennisValues));

		DbAccess db = null;
		TableData T=new TableData(db);
		data=T.getDistinctTransazioni(table);

		if(data.size()==0)
		{
			throw new EmptyDatasetException();
		}
		if(attributeSet.isEmpty())
		{
			throw new EmptyDatasetException();
		}
	}
	/**
	 * @return Number of rows (14)
	 */
	public int getNumberOfExamples(){

		return data.size();
	}
	/**
	 * @return Number of columns (5)
	 */
	public int getNumberOfAttributes(){

		return attributeSet.size();
	}
	/**
	 * @param exampleIndex: index of example in data
	 * @param attributeIndex: index of attribute in data
	 * @return Value in position (exampleIndex,attributeIndex)
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex) {

		return data.get(exampleIndex).get(attributeIndex);
	}
	/**
	 * @return ArrayList: that is the array column 
	 */
	List<Attribute> getAttribute(){

		return attributeSet;
	}

	/**
	 * @return output: String that contain the table and 
	 * the number of transaction of data
	 */
	public String toString(){

		String output=" ";

		for(int i=0; i<getNumberOfAttributes(); i++){

			output = output +"  "+((Attribute) attributeSet.get(i)).getName() + ",";

		}
		output = output+"\n";

		for(int i=0; i<data.size(); i++) {
			output = output + i + ": ";

			for(int j=0; j<getNumberOfAttributes(); j++) {

				output = output + data.get(i).get(j)+ ",     ";

			}

			output = output+"\n";
		}

		return output;
	}

	/**
	 * @param index: position of data
	 * @return Tuple: Object to model i-esima data row as sequence of couples Attribute-value
	 */
	public Tuple getItemSet(int index)
	{
		Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++)
		{

			if (attributeSet.get(i) instanceof ContinuousAttribute)
			{
				tuple.add(new ContinuousItem((ContinuousAttribute) attributeSet.get(i), new Double (data.get(index).get(i).toString())),i);
			}
			else
			{
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data.get(index).get(i)),i);
			}

		}

		return tuple;
	}

}
