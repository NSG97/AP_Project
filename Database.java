import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;

public class Database extends DefaultHandler{
	ArrayList<Publication> DB = new ArrayList<Publication>();
	ArrayList<Person> Persons = new ArrayList<Person>();
}
