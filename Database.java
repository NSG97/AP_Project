/*
 * This class is responsible for maintaining databases in form of array lists and hash maps
 */


import java.util.*;
import java.util.regex.*;

import org.xml.sax.helpers.DefaultHandler;

public class Database extends DefaultHandler{
	protected ArrayList<Person> Persons = new ArrayList<Person>();
	protected HashMap<String,Integer> autToNo = new HashMap<String,Integer>();
	
	public ArrayList<String> getRelevantNames(String tag){
		String[] terms = tag.split(" ");
		ArrayList<String> RelevantNames = new ArrayList<String>();	
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
					if(m.find())
						matches++;
				}
				if(matches==terms.length){
					RelevantNames.addAll(temp.getNames());
					break;
				}
			}
		}
		return RelevantNames;
	}
	public ArrayList<String> getExactNames(String name){
		Iterator<Person> PerIter = Persons.iterator();
		Person RelPerson=null;
		while(PerIter.hasNext()){
			boolean f=false;
			Person temp = PerIter.next();
			Iterator<String> names = temp.getNames().iterator();
			while(names.hasNext()){
				if(name.equalsIgnoreCase(names.next())){
					f=true;
					break;
				}
			}
			if(f){
				RelPerson=temp;
				break;
			}
		}
		if(RelPerson==null){
			return null;
		}
		return RelPerson.getNames();
	}
	public ArrayList<Person> SearchMoreK(int k){
		ArrayList<Person> Result= new ArrayList<Person>();
		Iterator<Person> PerIter = Persons.iterator();
		while(PerIter.hasNext()){
			int NoOfPubs = 0;
			Person temp = PerIter.next();
			Iterator<String> Pseudos = temp.getNames().iterator();
			while(Pseudos.hasNext()){
				String curName = Pseudos.next();
				if(autToNo.containsKey(curName)){
					NoOfPubs = NoOfPubs+autToNo.get(curName);
				}
			}
			if(NoOfPubs>=k)
				Result.add(temp);
		}
		return Result;
	}
}
