/*
 * Class which initiates project
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class Main {

	public static void main(String[] args){
		 try{
			 
			 File inputFile = new File("dblp.xml");
			 InputStream inputStream = new FileInputStream(inputFile);
			 Reader reader = new InputStreamReader(inputStream,"UTF-8");
			 
			 InputSource is = new InputSource(reader);
			 is.setEncoding("UTF-8");
			 
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 Database DB = new DBLP_Parser();
			 
			 long t=System.currentTimeMillis();
			 System.out.println("Start: "+t);
			 
			 saxParser.parse(is,DB);
			 
			 long t2=System.currentTimeMillis();
			 System.out.println("End: "+t2);
			 System.out.println("Time Taken: "+(t2-t)+"ms");
			 
			 new MainFrame(DB);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }

}
