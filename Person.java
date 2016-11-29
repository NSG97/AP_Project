import java.util.ArrayList;
/*
 * Persons, storing names of author for entity resolution
 */
public class Person {
	//! names used by author
	private ArrayList<String> Names = new ArrayList<String>();
	public void add(String s){
		Names.add(s);
	}
	//! return the number of names
	public int size(){
		return Names.size();
	}
	//! retur the names
	public ArrayList<String> getNames(){
		return Names;
	}
	//! String array to shown on result panel
	public String[] getStringArray(long i){
		String[] str ={""+i+"",Names.toString(),"","","","","",""};
		return str;
	}
}
