import java.util.*;
import java.util.regex.*;

public class Publication{
	private String Title = "---";
	private ArrayList<String> Authors = new ArrayList<String>();
	private int year = 0;
	private String pages = "---";
	private String volume = "---";
	private String journal = "---";
	private String url = "---";
	private int ExRel = 0,ExSpRel=0;
	
	public void setTitle(String _title){
		Title=_title;
	}
	public void addAuthor(String _author){
		Authors.add(_author);
	}
	public void setYear (int _year){
		year=_year;
	}
	public void setPages(String _pages){
		pages=_pages;
	}
	public void setVolume(String _volume){
		volume=_volume;
	}
	public void setJournal(String _journal){
		journal=_journal;
	}
	public void setURL(String _url){
		url=_url;
	}
	public ArrayList<String> getAuthors()
	{
		return Authors;
	}
	public boolean ifAuthor(String name){
		if(Authors.contains(name))
			return true;
		return false;
	}
	public String getTitle(){
		return this.Title;
	}
	public int getYear(){
		return this.year;
	}
	public String[] getStringArray(long i){
		String[] str ={""+i+"",Authors.toString(),Title,pages,""+year+"",volume,journal,url};
		return str;
	}
	public int compareYear(Publication arg0) {
		if(this.year==0)
			return Integer.MAX_VALUE;
		else if(arg0.year==0)
			return Integer.MIN_VALUE;
		else
			return (this.year-arg0.year);
	}
	public void setRelevanceByAuthor(String tag){
		String terms[] = tag.split(" ");
		int exact=0;int exactSplit=0;
		Iterator<String> AutIter = Authors.iterator();
		while(AutIter.hasNext()){
			String curName = AutIter.next();
			Matcher m = Pattern.compile("\\b"+tag.toLowerCase()+"\\b").matcher(curName.toLowerCase());
			if(m.find()){
				exact++;
			}
			else{
				int i;
				for(i=0;i<terms.length;i++){
					m = Pattern.compile("\\b"+terms[i].toLowerCase()+"\\b").matcher(curName.toLowerCase());
					while(m.find())
						exactSplit++;
				}
			}
		}
		ExRel=exact;ExSpRel=exactSplit;
	}
	public void setRelevanceByTitle(String tag){
		String terms[] = tag.split(" ");
		int exact=0;int exactSplit=0;
		if(Title.toLowerCase().contains(tag.toLowerCase())){
				exact++;
		}
		else{
			int i;
			for(i=0;i<terms.length;i++){
				Matcher m = Pattern.compile("\\b"+terms[i].toLowerCase()+"\\b").matcher(Title.toLowerCase());
				while(m.find())
					exactSplit++;
			}
		}
		ExRel=exact;ExSpRel=exactSplit;
	}
	public int compareRel(Publication arg0){
		if(this.ExRel>arg0.ExRel)
			return 1;
		else if(this.ExRel<arg0.ExRel)
			return -1;
		else{
			if(this.ExSpRel>arg0.ExSpRel)
				return 1;
			else if(this.ExSpRel<arg0.ExSpRel)
				return -1;
			else
				return 0;
		}
	}
}
