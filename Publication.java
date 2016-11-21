import java.util.ArrayList;

public class Publication{
	private String Title = "---";
	private ArrayList<String> Authors = new ArrayList<String>();
	private int year = 0;
	private String pages = "---";
	private String volume = "---";
	private String journal = "---";
	private String url = "---";
	private int relevance = 0;
	
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
	public void setRelevance(int _rel){
		relevance = _rel;
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
	public int compareRel(Publication arg0){
		return (this.relevance-arg0.relevance);
	}
}
