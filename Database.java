/*
 * This class is responsible for maintaining databases in form of array lists and hash maps
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
	public float[] Predict(String name,int yearToPredict){
		float[] pred_act = new float[2];int actual=0;
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
			pred_act[0]=0;pred_act[1]=0;
			return pred_act;
		}
		System.out.println(RelPerson);
		HashMap<Integer,Integer> YearToNo = new HashMap<Integer,Integer>();
		Iterator<String> pseudos = RelPerson.getNames().iterator();
		while(pseudos.hasNext()){
			String curName = pseudos.next();
			Iterator<Publication> curPubs = AtoPub.get(curName).iterator();
			while(curPubs.hasNext()){
				Publication temp = curPubs.next();
				if(temp.getYear()<yearToPredict){
					if(!YearToNo.containsKey(temp.getYear())){
						YearToNo.put(temp.getYear(),1);
					}
					else{
						YearToNo.put(temp.getYear(),YearToNo.get(temp.getYear())+1);
					}
				}
				else if(temp.getYear()==yearToPredict)
					actual++;
			}
		}
		System.out.println(YearToNo);
		pred_act[0]=predictNext(YearToNo,yearToPredict);
		pred_act[1]=actual;
		return pred_act;
	}
	private float predictNext(HashMap<Integer,Integer> xToy, int x){
		System.out.println("predicting");
		Iterator<Integer> Xs = xToy.keySet().iterator();
		float xMean=0,yMean=0;
		while(Xs.hasNext()){
			Integer xi = Xs.next();
			xMean = xMean+xi;yMean = yMean+xToy.get(xi);
		}
		xMean = xMean/xToy.size();
		yMean = yMean/xToy.size();
		float xSig=0,ySig=0,x2Sig=0;
		Xs = xToy.keySet().iterator();
		while(Xs.hasNext()){
			Integer xi = Xs.next();
			xSig = xSig + (xi-xMean);
			ySig = ySig + (xToy.get(xi)-yMean);
			x2Sig = x2Sig + (xi-xMean)*(xi-xMean);
		}
		float b = (xSig*ySig)/x2Sig;
		float a = yMean-b*xMean;
		return (a+b*x);
	}
}
