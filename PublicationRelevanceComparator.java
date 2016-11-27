/*
 * 
 */


import java.util.Comparator;


public class PublicationRelevanceComparator implements Comparator<Publication> {

	public int compare(Publication arg0, Publication arg1) {
		return arg0.compareRel(arg1);
	}

}
