import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DBLP_Parser extends Database{
	private Publication P;
	private Person newAuthor;
	private boolean bTitle = false, bAuthors = false,bPages = false,bYear = false;
	private boolean bVolume = false,bJournal = false,bURL = false;
	
	public void startElement(String uri,String localName, String qName,Attributes attributes) throws SAXException{
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
}
