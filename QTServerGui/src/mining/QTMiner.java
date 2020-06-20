package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.Data;
/**
 * @author Giuseppe
 */
public class QTMiner implements Serializable {

	private static final long serialVersionUID = 1L;
	private ClusterSet C;
	private double radius;

	/**
	 * @param radius: radius of Clustering 
	 */
	public QTMiner(double radius) {
		C = new ClusterSet(); 
		this.radius = radius; 
	}

	/**
	 * Method for de-serialization that open fileName, read 
	 * the memorized object to assign at C.
	 * @param fileName: path+filename to de-serialization
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		C = (ClusterSet) in.readObject(); 
		in.close();
	}
	/**
	 * Method for serialization that open fileName to save
	 * the object C in fileName.
	 * @param fileName: path+filename to serialization
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException {

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(C);
		out.close();
	}
	/**
	 * @return C: that is the set of clusters.
	 */
	public ClusterSet getC() {
		return C;
	}


	/**
	 * This method build a cluster for each tuple (not yet clustered),
	 * including in cluster the points (not yet clustered) that are near
	 * the tuples with radius.
	 * @param data: 
	 * @return numclusters: number of cluster funded
	 * @throws ClusteringRadiusException
	 */
	public int compute(Data data) throws ClusteringRadiusException {
		int numclusters = 0;
		boolean isClustered[] = new boolean[data.getNumberOfExamples()];

		for (int i = 0; i < isClustered.length; i++)
			isClustered[i] = false;
		int countClustered = 0;

		while (countClustered != data.getNumberOfExamples()) {
			// Ricerca e salvataggio del cluster più popoloso
			Cluster c = buildCandidateCluster(data, isClustered);
			C.add(c);
			numclusters++;

			// Rimuovo tutti i punti di tale cluster dall'elenco delle tuple da clusterizzare

			for (Integer it : c) {
				isClustered[it] = true;
			}
			countClustered += c.getSize();
			// Ripete il while finchè ci sono ancora tuple da assegnare ad un cluster

		}

		if (numclusters == 1) {
			throw new ClusteringRadiusException();
		} else {
			return numclusters;
		}

	}


	/**
	 * build a cluster for each tuple of data not yet clustered in
	 * a cluster of C
	 * @param data:
	 * @param isClustered: parameter to indicate if(isClustered[i]==FALSE) then
	 * the tuple i-esima of data has not been assigned to cluster of  C, 
	 * TRUE else.
	 * @return CP: that is the candidate cluster most Populous

	 */
	public Cluster buildCandidateCluster(Data data, boolean isClustered[]) { // CP(ClusterPopulous) cluster vuoto che conterrà il più popoloso
		Cluster CP = null;
		for (int i = 0; i < isClustered.length; i++) {
			// inizializza il cluster candidato C con tutte le tuple che rientrano in radius
			Cluster C = new Cluster(data.getItemSet(i));
			if (isClustered[i] == false) {
				for (int j = 0; j < isClustered.length; j++) {
					if (isClustered[j] == false) {
						if (data.getItemSet(i).getDistance(data.getItemSet(j)) <= radius) {
							C.addData(j);
						}
					}
				}

				if (CP == null)
					CP = C;
				else if (C.getSize() > CP.getSize())// qui decido quale cluster tra CP e C è più popoloso
					CP = C;
			}
		}
		return CP;
	}

	public String toString()
	{
		return C.toString();
	}
}
