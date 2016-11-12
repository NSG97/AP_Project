import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DBLP_Parser extends DefaultHandler{
	double i=0;
	boolean bTitle = false;
	boolean bAuthors = false;
	boolean bPages = false;
	boolean bYear = false;
	boolean bVolume = false;
	boolean bJournal = false;
	boolean bURL = false;
	
	public void startElement(String uri,String localName,
			String qName,Attributes attributes)
			throws SAXException{
		if(qName.equalsIgnoreCase("article")){
			System.out.println("No:  "+(++i));
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
		else if(qName.equalsIgnoreCase("journal")){
			bJournal = true;
		}
		else if(qName.equalsIgnoreCase("url")){
			bURL = true;
		}
	}
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName.equalsIgnoreCase("article")){
			System.out.println("End Element : "+qName);
		}
	}
	
	public void characters(char ch[], int start,
			int length) throws SAXException {
		if(bTitle){
			System.out.println("Title: "
					+new String(ch,start,length));
			bTitle = false;
		}
		else if(bAuthors){
			System.out.println("Author: "
					+new String(ch,start,length));
			bAuthors = false;
		}
		else if(bPages){
			System.out.println("Pages: "
					+new String(ch,start,length));
			bPages = false;
		}
		else if(bYear){
			System.out.println("Year: "
					+new String(ch,start,length));
			bYear = false;
		}
		else if(bVolume){
			System.out.println("Volume: "
					+new String(ch,start,length));
			bVolume = false;
		}
		else if(bJournal){
			System.out.println("Journal: "
					+new String(ch,start,length));
			bJournal = false;
		}
		else if(bURL){
			System.out.println("URL: "
					+new String(ch,start,length));
			bURL = false;
		}
	}
	
	 public static void main(String[] args){
		 try{
			 File inputFile = new File("dblp.xml");
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 DBLP_Parser userhandler = new DBLP_Parser();
			 saxParser.parse(inputFile,userhandler);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
