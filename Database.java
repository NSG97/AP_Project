import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;

public class Database extends DefaultHandler{
	protected ArrayList<Publication> DB = new ArrayList<Publication>();
	protected ArrayList<Person> Persons = new ArrayList<Person>();
}
