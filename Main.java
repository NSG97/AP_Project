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
			 InputStream iStream = new FileInputStream(inputFile);
			 Reader reader = new InputStreamReader(iStream,"ISO-8859-1");
			 
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 Database DB = new DBLP_Parser();
			 
			 InputSource is = new InputSource(reader);
			 is.setEncoding("ISO-8859-1");
			 
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
