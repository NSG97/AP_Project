import java.util.*;
import java.util.regex.*;

import org.xml.sax.helpers.DefaultHandler;


public class Database extends DefaultHandler{
	protected ArrayList<Publication> DB = new ArrayList<Publication>();
	protected ArrayList<Person> Persons = new ArrayList<Person>();
	protected HashMap<String,ArrayList<Publication>> AtoPub = new HashMap<String,ArrayList<Publication>>();
	protected HashMap<String,Publication> TtoPub = new HashMap<String,Publication>();
	
	public ArrayList<Publication> SearchAuthor(String name){
		String[] terms = name.split(" ");
		ArrayList<Publication> Result = new ArrayList<Publication>();
		HashMap<String,Integer> RelevantNames = new HashMap<String,Integer>();	
		Iterator<String> names;
		Iterator<Person> iter = Persons.iterator();
		while(iter.hasNext()){
			Person temp=iter.next();
			names = temp.getNames().iterator();
			while(names.hasNext()){
				String curName = names.next();
				int i;
				int matches=0;
				for(i=0;i<terms.length;i++){
					Matcher m = Pattern.compile(terms[i].toLowerCase()).matcher(curName.toLowerCase());
					while(m.find())
						matches++;
				}
				if(matches!=0){
					RelevantNames.put(curName, matches);
				}
			}
		}
		names = RelevantNames.keySet().iterator();
		while(names.hasNext()){
			ArrayList<Publication> temp;
			String tempName = names.next();
			if((temp=AtoPub.get(tempName))!=null){
				Result.addAll(temp);
			}
		}
		return Result;
	}
}
