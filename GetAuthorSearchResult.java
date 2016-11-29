import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class GetAuthorSearchResult {
private ResultDatabase RDB;
	
	GetAuthorSearchResult(String tag,Database DB){
		try{
			ArrayList<String> RelevantNames = DB.getRelevantNames(tag);
			
			File inputFile = new File("dblp.xml");
			 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			RDB = new AuthorSearchParser(RelevantNames);
			 
			long t=System.currentTimeMillis();
			System.out.println("Start: "+t);
			 
			saxParser.parse(inputFile,RDB);
			
			long t2=System.currentTimeMillis();
			System.out.println("End: "+t2);
			System.out.println("Time Taken: "+(t2-t)+"ms");
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	public ArrayList<Publication> getResult(){
		return RDB.getResult();
	}
}
