import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.xml.sax.helpers.DefaultHandler;

public class Database extends DefaultHandler{
	protected ArrayList<Publication> DB = new ArrayList<Publication>();
	protected ArrayList<Person> Persons = new ArrayList<Person>();
	protected HashMap<String,ArrayList<Publication>> AtoPub = new HashMap<String,ArrayList<Publication>>();
	
	public ArrayList<Publication> SearchAuthor(String name){
		ArrayList<Publication> Result = new ArrayList<Publication>();
		ArrayList<String> RelevantNames = new ArrayList<String>();
		
		Iterator<String> names;
		
		Iterator<Person> iter = Persons.iterator();
		while(iter.hasNext()){
			Person temp=iter.next();
			names = temp.getNames().iterator();
			while(names.hasNext()){
				String curName = names.next();
				if(Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(curName).find()){
					RelevantNames.add(curName);
				}
			}
		}
		
		names = RelevantNames.iterator();
		
		while(names.hasNext()){
			Result.addAll(AtoPub.get(names.next()));
		}
		
		return Result;
	}
}
