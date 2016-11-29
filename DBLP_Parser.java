/*
 * Main SAX Parser class
 */

import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DBLP_Parser extends Database{
	private Person newAuthor;
	private boolean bAuthors = false;
	private ArrayList<String> pubAuthors;
	
	public void startElement(String uri,String localName, String qName,Attributes attributes) throws SAXException{
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			pubAuthors = new ArrayList<String>();
		}
		else if(qName.equalsIgnoreCase("www")){
			newAuthor = new Person();
		}
		else if(qName.equalsIgnoreCase("author")){
			bAuthors = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("inproceedings") || qName.equalsIgnoreCase("incollection")
				|| qName.equalsIgnoreCase("book") || qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("mastersthesis")){
			if(pubAuthors.size()!=0){
				Iterator<String> authors = pubAuthors.iterator();
				while(authors.hasNext()){
					String temp = authors.next();
					if(autToNo.containsKey(temp)){
						autToNo.put(temp, autToNo.get(temp)+1);
					}
					else{
						autToNo.put(temp, 1);
					}
				}
			}
		}
		else if(qName.equalsIgnoreCase("www")){
			if(newAuthor.size()!=0){
				this.Persons.add(newAuthor);
			}
			newAuthor=null;
		}
	}
	
	public void characters(char ch[], int start,int length) throws SAXException {
		if(bAuthors){
			if(newAuthor!=null){
				newAuthor.add(new String(ch,start,length));
			}
			else if(pubAuthors!=null){
				pubAuthors.add(new String(ch,start,length));
			}
			bAuthors = false;
		}
	}
}
