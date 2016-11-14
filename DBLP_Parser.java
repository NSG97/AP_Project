import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

public class DBLP_Parser extends Database{
	Publication P;
	Person newAuthor;
	boolean bTitle = false;
	boolean bAuthors = false;
	boolean bPages = false;
	boolean bYear = false;
	boolean bVolume = false;
	boolean bJournal = false;
	boolean bURL = false;
	
	public void startElement(String uri,String localName, String qName,Attributes attributes)
			throws SAXException{
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			P = new Publication();
		}
		else if(qName.equalsIgnoreCase("www")){
			newAuthor = new Person();
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
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			this.DB.add(P);
			P=null;
		}
		else if(qName.equalsIgnoreCase("www")){
			if(newAuthor.Names.size()!=0){
				this.Persons.add(newAuthor);
			}
			newAuthor=null;
		}
	}
	
	public void characters(char ch[], int start,int length) throws SAXException {
		if(bTitle){ 
			if(P!=null){
				P.Title = new String(ch,start,length);
			}
			bTitle = false;
		}
		else if(bAuthors){
			if(P!=null){
				P.Authors.add(new String(ch,start,length));
			}
			else if(newAuthor!=null){
				newAuthor.Names.add(new String(ch,start,length));
			}
			bAuthors = false;
		}
		else if(bPages){
			if(P!=null){
				P.pages = new String(ch,start,length);
			}
			bPages = false;
		}
		else if(bYear){
			if(P!=null){
				P.year=Integer.parseInt(new String(ch,start,length));
			}
			bYear = false;
		}
		else if(bVolume){
			if(P!=null){
				P.volume=new String(ch,start,length);
			}
			bVolume = false;
		}
		else if(bJournal){
			if(P!=null){
				P.journal=new String(ch,start,length);
			}
			bJournal = false;
		}
		else if(bURL){
			if(P!=null){
				P.url=new String(ch,start,length);
			}
			bURL = false;
		}
	}
	
	 public static void main(String[] args){
		 try{
			 
			 File inputFile = new File("dblp.xml");
			 InputStream inputStream = new FileInputStream(inputFile);
			 Reader reader = new InputStreamReader(inputStream,Charset.forName("ISO-8859-1"));
			 InputSource is = new InputSource(reader);
			 is.setEncoding("ISO-8859-1");
			 
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 DBLP_Parser userhandler = new DBLP_Parser();
			 
			 long t=System.currentTimeMillis();
			 System.out.println("Start: "+t);
			 saxParser.parse(is,userhandler);
			 long t2=System.currentTimeMillis();
			 System.out.println("End: "+t2);
			 System.out.println("Time Taken: "+(t2-t)+"ms");
			 
			 System.out.println("No of Publication: "+userhandler.DB.size());
			 System.out.println("No of Authors(Persons): "+userhandler.Persons.size());
			 Scanner in = new Scanner(System.in);
			 int i=0;
			 while(i>=0){
				 System.out.print("\nEnter Publication no: ");
				 i=in.nextInt();
				 System.out.println(userhandler.DB.get(i));
				 System.out.print("\nEnter Person no: ");
				 i=in.nextInt();
				 System.out.println(userhandler.Persons.get(i));
			 }
			 in.close();
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
