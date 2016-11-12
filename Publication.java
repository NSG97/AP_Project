import java.util.ArrayList;

public class Publication {
	String Title;
	ArrayList<String> Authors = new ArrayList<String>();
	int year;
	String pages;
	String volume;
	String journal;
	String url;
	
	public String toString(){
		String str = "Title: "+Title+"\nAuthors: "+Authors.toString()+
				"\nYear: "+year+"\nPages: "+pages+
				"\nVolume: "+volume+
				"\nJournal: "+journal+
				"\nURL: "+url;
		return str;
	}
}
