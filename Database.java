/*
 * This class is responsible for maintaining databases in form of array lists and hash Maps
 */


import java.util.*;
import java.util.regex.*;

import org.xml.sax.helpers.DefaultHandler;


public class Database extends DefaultHandler{
	protected ArrayList<Publication> DB = new ArrayList<Publication>();
	protected ArrayList<Person> Persons = new ArrayList<Person>();
	protected HashMap<String,ArrayList<Publication>> AtoPub = new HashMap<String,ArrayList<Publication>>();
	
	public ArrayList<Publication> SearchAuthor(String name){
		String[] terms = name.split(" ");
		ArrayList<Publication> Result = new ArrayList<Publication>();
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
		names = RelevantNames.iterator();
		while(names.hasNext()){
			ArrayList<Publication> temp;
			String tempName = names.next();
			if((temp=AtoPub.get(tempName))!=null){
				Iterator<Publication> tempIter = temp.iterator(); 
				while(tempIter.hasNext()){
					Publication tempPub = tempIter.next();
					if(!Result.contains(tempPub))
						Result.add(tempPub);
				}
			}
		}
		return Result;
	}
	public ArrayList<Publication> SearchTitle(String tag){
		ArrayList<Publication> Result = new ArrayList<Publication>();
		String[] terms = tag.split(" ");
		Iterator<Publication> iter = DB.iterator();
		while(iter.hasNext()){
			Publication temp = iter.next();
			String curTitle = temp.getTitle();
			int i;
			int matches=0;
			for(i=0;i<terms.length;i++){
				Matcher m = Pattern.compile(terms[i].toLowerCase()).matcher(curTitle.toLowerCase());
				if(m.find())
					matches++;
			}
			if(matches==terms.length)
				Result.add(temp);
		}
		return Result;
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
				if(AtoPub.get(curName)!=null){
					NoOfPubs = NoOfPubs+AtoPub.get(curName).size();
				}
			}
			if(NoOfPubs>=k)
				Result.add(temp);
		}
		return Result;
	}
	public int[] Predict(String name,int yearUntil){
		int[] pred_act = new int[2];
		Iterator<Person> PerIter = Persons.iterator();
		Person RelPerson;
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
		
		return pred_act;
	}
}
