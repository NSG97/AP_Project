import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Query2 extends JFrame {
	private static int Def_width=1200;
	private static int Def_height=1200;	
	private JLabel Banner=new JLabel("<html><b>DBLP QUERY ENGINE</b></html>");
	private JPanel Header_Panel;
	private JPanel Querypanel;
	private JPanel Resultpanel;
	private JButton Search=new JButton("Search");
	private JButton Reset=new JButton("Reset");
	public Query2(){		
		setLayout(null);
		setSize(Def_height,Def_width);
		Header_Panel=new JPanel();
		Querypanel=new JPanel();
		Resultpanel=new JPanel();
		
		
		Header_Panel.setVisible(true);
		Querypanel.setVisible(true);
		Resultpanel.setVisible(true);
		
		
		Banner.setFont(new Font("Serif", Font.PLAIN, 50));
		Header_Panel.add(Banner);
		Banner.setBorder(BorderFactory.createLineBorder(Color.black));
		Header_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		Header_Panel.setSize(1160,100);
		Header_Panel.setBounds(10,10,1160,100);
		add(Header_Panel);	
		
		Querypanel.setBorder(BorderFactory.createLineBorder(Color.black));
		Querypanel.setSize(400,600);
		
		Querypanel.setBounds(10, 150, 400, 600);
		
		
		String[] queries_type=new String[]{"Query2","Query1","Query3"};
		JComboBox<String>queries=new JComboBox<String>(queries_type);
		Querypanel.add(queries);

		queries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox<String> combo=(JComboBox<String>) event.getSource();
				String item=(String)combo.getSelectedItem();
				if(item.equals("Query1")){	
					new Query1();
				}
				else if(item.equals("Query2")){
					new Query2();
				}
				else if(item.equals("Query3")){
					
				}
			}
		});
		
		JLabel publication=new JLabel("No. of Publications");
		publication.setBounds(40,200,150,30);
		JTextField publication_box=new JTextField();
		publication_box.setBounds(200, 200, 80, 30);
		add(publication);
		add(publication_box);
		Search.setBounds(40, 400, 80, 40);
		Reset.setBounds(140, 400, 80, 40);
		add(Search);
		add(Reset);
		add(Querypanel);		
		Resultpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		Resultpanel.setSize(750,600);
		Resultpanel.setBounds(420 ,150, 750, 600);
		add(Resultpanel);		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	public static void main(String[] args) {
		Query2 obj=new Query2();

	}

}
