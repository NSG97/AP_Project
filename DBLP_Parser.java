import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DBLP_Parser extends Database{
	Publication P;
	int i=0;
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
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") 
				|| qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis")
				|| qName.equalsIgnoreCase("mastersthesis")){
			System.out.println(++i+": "+attributes.getValue("key"));
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
		else if(qName.equalsIgnoreCase("journal")){
			bJournal = true;
		}
		else if(qName.equalsIgnoreCase("url")){
			bURL = true;
		}
	}
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings")
				|| qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis")
				|| qName.equalsIgnoreCase("masterthesis")){
			System.out.println(i+" END");
			this.DB.add(P);
			P=null;
		}
	}
	
	public void characters(char ch[], int start,
			int length) throws SAXException {
		if(bTitle){
			//System.out.println(i+" Title: "+new String(ch,start,length)); 
			P.Title = new String(ch,start,length);
			bTitle = false;
		}
		else if(bAuthors){
			//System.out.println(i+" Author: "+new String(ch,start,length));
			P.Authors.add(new String(ch,start,length));
			bAuthors = false;
		}
		else if(bPages){
			//System.out.println(i+" Pages: "+new String(ch,start,length));
			P.pages = new String(ch,start,length);
			bPages = false;
		}
		else if(bYear){
			//System.out.println(i+" Year: "+new String(ch,start,length));
			P.year=Integer.parseInt(new String(ch,start,length));
			bYear = false;
		}
		else if(bVolume){
			//System.out.println(i+" Volume: "+new String(ch,start,length));
			P.volume=new String(ch,start,length);
			bVolume = false;
		}
		else if(bJournal){
			//System.out.println(i+" Journal: "+new String(ch,start,length));
			P.journal=new String(ch,start,length);
			bJournal = false;
		}
		else if(bURL){
			//System.out.println(i+" URL: "+new String(ch,start,length));
			P.url=new String(ch,start,length);
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
			 Scanner in = new Scanner(System.in);
			 int i=1;
			 while(i>0){
				 i=in.nextInt();
				 System.out.println(userhandler.DB.get(i));
			 }
			 in.close();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
