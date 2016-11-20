import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel MainPanel,ResultPanel,QueryOptionPanel,QueryPanel;
	private JLabel Banner;
	MainFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,700);
		setUpMainPanel();
		this.add(MainPanel);
		this.setVisible(true);
	}
	private void setUpMainPanel(){
		MainPanel = new JPanel();
		MainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Banner = new JLabel("<html><b>DBLP Query Engine</b><html>");
		Banner.setHorizontalAlignment(JLabel.CENTER);
		Banner.setFont(new Font("Serif",Font.PLAIN,50));
		Banner.setPreferredSize(new Dimension(1000,50));
		Banner.setMinimumSize(new Dimension(1000,50));
		Banner.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=2;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		MainPanel.add(Banner,gbc);
		
		setUpQueryOptionPanel();
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=0.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		MainPanel.add(QueryOptionPanel,gbc);
		
		setUpQueryPanel();
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=2;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=0.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		MainPanel.add(QueryPanel,gbc);
		
		
		setUpResultPanel();
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=1;gbc.gridy=1;
		gbc.gridheight=2;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		MainPanel.add(ResultPanel,gbc);
	}
	
	private void setUpQueryOptionPanel(){
		QueryOptionPanel = new JPanel();
		QueryOptionPanel.setPreferredSize(new Dimension(300,50));
		QueryOptionPanel.setMinimumSize(new Dimension(300,50));
		QueryOptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		String[] queries_type=new String[]{"Queries","Query1","Query2","Query3"};
		JComboBox<String>queries=new JComboBox<String>(queries_type);
		QueryOptionPanel.add(queries);

		queries.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox<String> combo=(JComboBox<String>) event.getSource();
				String item=(String)combo.getSelectedItem();
				if(item.equals("Queries")){
					MainFrame.this.setToQueryDefault();
				}
				else if(item.equals("Query1")){
					MainFrame.this.setToQuery1();
				}
				else if(item.equals("Query2")){
					MainFrame.this.setToQuery2();
				}
				else if(item.equals("Query3")){
					
				}
			}
		});
	}
	
	private void setUpQueryPanel(){
		QueryPanel = new JPanel();
		QueryPanel.setPreferredSize(new Dimension(300,400));
		QueryPanel.setMinimumSize(new Dimension(300,400));
		QueryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void setToQueryDefault(){
		QueryPanel.removeAll();
		QueryPanel.revalidate();
		QueryPanel.repaint();
	}
	
	private void setToQuery1(){
		QueryPanel.removeAll();
		QueryPanel.add(new Query1Panel());
		QueryPanel.revalidate();
		QueryPanel.repaint();
	}
	
	private void setToQuery2(){
		QueryPanel.removeAll();
		QueryPanel.add(new Query2Panel());
		QueryPanel.revalidate();
		QueryPanel.repaint();
	}
	
	
	private void setUpResultPanel(){
		ResultPanel = new JPanel();
		ResultPanel.setPreferredSize(new Dimension(600,600));
		ResultPanel.setMinimumSize(new Dimension(600,600));
		ResultPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public static void main(String[] args){
		new MainFrame();
	}
}
