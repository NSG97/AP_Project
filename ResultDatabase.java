import java.util.*;

import org.xml.sax.helpers.DefaultHandler;
/*!
 * Database for the result from parsing
 */
public class ResultDatabase extends DefaultHandler{
	/*!Arraylist resulted publications*/
	ArrayList<Publication> ResultDB = new ArrayList<Publication>();
	/*!return the results found*/
	public ArrayList<Publication> getResult(){
		return ResultDB;
	}
}
