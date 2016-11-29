import java.util.*;

import org.xml.sax.helpers.DefaultHandler;

public class ResultDatabase extends DefaultHandler{
	ArrayList<Publication> ResultDB = new ArrayList<Publication>();
	public ArrayList<Publication> getResult(){
		return ResultDB;
	}
}
