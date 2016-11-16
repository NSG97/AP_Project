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
			 Scanner in = new Scanner(System.in);
			 while(true){
				 System.out.println("Enter author: ");
				 String name = in.nextLine();
				 if(name.equalsIgnoreCase("quit"))
					 break;
				 ArrayList<Publication> Result = DB.SearchAuthor(name);
				 Collections.sort(Result, new PublicationComparator());
				 Iterator<Publication> iter = Result.iterator();
				 while(iter.hasNext())
					 System.out.println(iter.next());
			 }
			 in.close();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }

}
