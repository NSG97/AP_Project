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
				|| qName.equalsIgnoreCase("mastersthesis")){
			System.out.println(i+" END");
			this.DB.add(P);
			P=null;
		}
	}
	
	public void characters(char ch[], int start,
			int length) throws SAXException {
		if(bTitle){
			System.out.println(i+" Title: "+new String(ch,start,length)); 
			if(P!=null){
				P.Title = new String(ch,start,length);
				bTitle = false;
			}
		}
		else if(bAuthors){
			//System.out.println(i+" Author: "+new String(ch,start,length));
			if(P!=null){
				P.Authors.add(new String(ch,start,length));
				bAuthors = false;
			}
		}
		else if(bPages){
			//System.out.println(i+" Pages: "+new String(ch,start,length));
			if(P!=null){
				P.pages = new String(ch,start,length);
				bPages = false;
			}
		}
		else if(bYear){
			String temp = new String(ch,start,length);
			System.out.println(i+" Year: "+temp);
			if(P!=null){
				try{
					P.year=Integer.parseInt(temp);
				}
				catch(Exception e){
					e.printStackTrace();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				bYear = false;
			}
		}
		else if(bVolume){
			//System.out.println(i+" Volume: "+new String(ch,start,length));
			if(P!=null){
				P.volume=new String(ch,start,length);
				bVolume = false;
			}
		}
		else if(bJournal){
			//System.out.println(i+" Journal: "+new String(ch,start,length));
			if(P!=null){
				P.journal=new String(ch,start,length);
				bJournal = false;
			}
		}
		else if(bURL){
			//System.out.println(i+" URL: "+new String(ch,start,length));
			if(P!=null){
				P.url=new String(ch,start,length);
				bURL = false;
			}
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
			 saxParser.parse(is,userhandler);
			 Scanner in = new Scanner(System.in);
			 int i=1;
			 while(i>0){
				 System.out.print("Enter: ");
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
