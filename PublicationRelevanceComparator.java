import java.util.Comparator;


public class PublicationRelevanceComparator implements Comparator<Publication> {

	public int compare(Publication arg0, Publication arg1) {
		if(arg0.compareRel(arg1)<0)
			return -1;
		else if(arg0.compareRel(arg1)>0)
			return 1;
		else
			return 0;
	}

}