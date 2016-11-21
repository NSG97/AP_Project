import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args){
		 try{
			 
			 File inputFile = new File("dblp.xml");
			 SAXParserFactory factory = SAXParserFactory.newInstance();
			 SAXParser saxParser = factory.newSAXParser();
			 Database DB = new DBLP_Parser();
			 
			 long t=System.currentTimeMillis();
			 System.out.println("Start: "+t);
			 
			 saxParser.parse(inputFile,DB);
			 
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
