import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Query1 extends JFrame{
		private static int Def_width=1200;
		private static int Def_height=1200;	
		private JLabel Banner=new JLabel("<html><b>DBLP QUERY ENGINE</b></html>");
		private JPanel Header_Panel;
		private JPanel Querypanel;
		private JPanel Resultpanel;
		private JButton Search=new JButton("Search");
		private JButton Reset=new JButton("Reset");
		public Query1(){		
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
		
			String[] queries_type=new String[]{"Query1","Query2","Query3"};
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
			
			
			String[] search_type=new String[]{"SearchBy","Author","Title"};
			JComboBox<String>search_box=new JComboBox<String>(search_type);
			Querypanel.add(search_box);
			queries.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JComboBox<String> combo=(JComboBox<String>) event.getSource();
					String item=(String)combo.getSelectedItem();
					if(item.equals("Author")){
						//changing_querypanel.setVisible(false);
						new Query1();
					}
					else if(item.equals("Title")){
						
					}					
				}
			});
			search_box.setSize(120,100);
			//search_box.setBounds(20, 180, 120,100 );
			//add(search_box);
			JLabel name_label=new JLabel("Name/Title");
			JLabel year_label=new JLabel("Since Year");
			JLabel custom_range_label=new JLabel("Custom Range");
			JLabel sort_by_year_label=new JLabel("Sort By Year");
			JLabel sort_by_relevance_label=new JLabel("Sort By Relevance");
			name_label.setBounds(40, 200, 80, 30);
			year_label.setBounds(40, 240, 80, 30);
			custom_range_label.setBounds(40, 280, 100, 30);
			JTextField name_title=new JTextField();
			JTextField year=new JTextField();
			JTextField range1=new JTextField();
			JTextField range2=new JTextField();
			JRadioButton sort_by_year_button=new JRadioButton();
			JRadioButton sort_by_relevance_button=new JRadioButton();
			name_title.setBounds(130, 200, 80, 30);
			year.setBounds(130, 240, 80, 30);
			range1.setBounds(130, 280, 50, 30);
			range2.setBounds(180, 280, 50, 30);
			sort_by_year_button.setBounds(40,320, 30, 30);
			sort_by_relevance_button.setBounds(40, 340, 30, 30);
			sort_by_year_label.setBounds(80, 320, 100, 30);
			sort_by_relevance_label.setBounds(80, 340, 130, 30);
			Search.setBounds(40, 400, 80, 40);
			Reset.setBounds(140, 400, 80, 40);
			add(name_label);
			add(name_title);
			add(year_label);
			add(year);
			add(custom_range_label);
			add(range1);
			add(range2);
			add(sort_by_year_button);
			add(sort_by_relevance_button);
			add(sort_by_year_label);
			add(sort_by_relevance_label);
			Search.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				}
			});
			Reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new Query1();
				}
			});
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
	
	public static void main(String args[]){
		Query1 obj=new Query1();
		
	}
}

