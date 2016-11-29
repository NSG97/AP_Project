import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class TitleSearchParser extends ResultDatabase{
	Publication P;
	String titleTag;
	private boolean bTitle = false, bAuthors = false,bPages = false,bYear = false;
	private boolean bVolume = false,bJournal = false,bURL = false;
	
	TitleSearchParser(String tag){
		titleTag=tag;
		ResultDB = new ArrayList<Publication>();
	}
	
	public void startElement(String uri,String localName, String qName,Attributes attributes) throws SAXException{
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			P = new Publication();
		}
		else if(qName.equalsIgnoreCase("title")){
			bTitle = true;
		}
		else if(qName.equalsIgnoreCase("author")){
			bAuthors = true;
		}
		else if(qName.equalsIgnoreCase("pages")){
			bPages = true;
		}
		else if(qName.equalsIgnoreCase("year")){
			bYear = true;
		}
		else if(qName.equalsIgnoreCase("volume")){
			bVolume = true;
		}
		else if(qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")){
			bJournal = true;
		}
		else if(qName.equalsIgnoreCase("url")){
			bURL = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			if(RightTitle(P)){
				ResultDB.add(P);
			}
			P=null;
		}
	}
	
	private boolean RightTitle(Publication curP){
		String terms[] = titleTag.split(" ");
		String curTitle = curP.getTitle();
		int i;
		int matches=0;
		for(i=0;i<terms.length;i++){
			Matcher m = Pattern.compile(terms[i].toLowerCase()).matcher(curTitle.toLowerCase());
			if(m.find())
				matches++;
		}
		if(matches==terms.length)
			return true;
		return false;
	}
	
	public void characters(char ch[], int start,int length) throws SAXException {
		if(bTitle){ 
			if(P!=null){
				P.setTitle(new String(ch,start,length));
			}
			bTitle = false;
		}
		else if(bAuthors){
			if(P!=null){
				P.addAuthor(new String(ch,start,length));
			}
			bAuthors = false;
		}
		else if(bPages){
			if(P!=null){
				P.setPages(new String(ch,start,length));
			}
			bPages = false;
		}
		else if(bYear){
			if(P!=null){
				P.setYear(Integer.parseInt(new String(ch,start,length)));
			}
			bYear = false;
		}
		else if(bVolume){
			if(P!=null){
				P.setVolume(new String(ch,start,length));
			}
			bVolume = false;
		}
		else if(bJournal){
			if(P!=null){
				P.setJournal(new String(ch,start,length));
			}
			bJournal = false;
		}
		else if(bURL){
			if(P!=null){
				P.setURL(new String(ch,start,length));
			}
			bURL = false;
		}
	}
}
