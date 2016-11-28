/*
 * class responsible for storing person records
 */


import java.util.ArrayList;

public class Person {
	private ArrayList<String> Names = new ArrayList<String>();
	public void add(String s){
		Names.add(s);
	}
	public int size(){
		return Names.size();
	}
	public ArrayList<String> getNames(){
		return Names;
	}
	public String toString(){
		return "Authors: "+Names;
	}
	public String[] getStringArray(long i){
		String[] str ={""+i+"",Names.toString(),"","","","","",""};
		return str;
	}
}
