/*
 * panel which displays result in result panel
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class ResultPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	String[] column_names={"S.No.","Authors","Title","Pages","Year","Volume","Journal/Booktitle","URL"};
	JPanel tPanel = new JPanel();
	JLabel NoOfResults = new JLabel();
	JTable table;GridBagConstraints gbc = new GridBagConstraints();
	JButton Next = new JButton("Next");
	Object[][] data = new Object[20][8];
	ArrayList<Publication> PubResult;
	ArrayList<Person> PerResult;
	int whichResult = 0,SNo=0;
	ResultPanel(){
		super();
		this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,600));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		SetUpTable();
	}
	private void SetUpTable(){
		NoOfResults.setText("No of Results");
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.NONE;
		this.add(NoOfResults,gbc);
		
		setUpTableData();
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.BOTH;
		this.add(tPanel,gbc);
		
		
		Next.setSize(200,100);
		Next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ResultPanel.this.showNext();
			}
		});
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=2;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(Next,gbc);
	}
	
	private void setUpTableData(){
		table = new JTable(data,column_names);
		table.setPreferredSize(new Dimension(800,400));
		table.setMinimumSize(new Dimension(800,400));
		JScrollPane sp = new JScrollPane(table);
		tPanel.add(sp);
	}
	public void Reset(){
		NoOfResults.setText("No Of Results.");
		tPanel.removeAll();
		data=new Object[20][8];
		setUpTableData();
		tPanel.revalidate();
		tPanel.repaint();
	}
	
	public void addResultPublication(ArrayList<Publication> Result){
		if(Result.size()==0){
			JOptionPane.showMessageDialog(null, "No Result.");
		}
		else{
			PubResult = Result;
			SNo=1;
			data=new Object[20][8];
			whichResult = 1;
			NoOfResults.setText(""+Result.size()+" Results");
			showNext();
		}
	}
	
	public void addResultPerson(ArrayList<Person> Result){
		if(Result.size()==0){
			JOptionPane.showMessageDialog(null, "No Result.");
		}
		else{
			PerResult = Result;
			SNo=1;
			data=new Object[20][8];
			whichResult = 2;
			NoOfResults.setText(""+Result.size()+" Results");
			showNext();
		}
	}
	
	private void showNext(){
		if(whichResult==1){
			int i=0;
			if(SNo>PubResult.size()){
				SNo=1;
			}
			while(i<20 && SNo<=PubResult.size()){
				data[i]=PubResult.get(SNo-1).getStringArray(SNo);
				i++;SNo++;
			}
			tPanel.removeAll();
			setUpTableData();
			tPanel.repaint();
			tPanel.revalidate();
		}
		else if(whichResult==2){
			int i=0;
			if(SNo>PerResult.size()){
				SNo=1;
			}
			while(i<20 && SNo<=PerResult.size()){
				data[i]=PerResult.get(SNo-1).getStringArray(SNo);
				i++;SNo++;
			}
			tPanel.removeAll();
			setUpTableData();
			tPanel.repaint();
			tPanel.revalidate();
		}
	}
	public ArrayList<Publication> SortByYear(ArrayList<Publication> Result,int from,int till){
		ArrayList<Publication> newResult = new ArrayList<Publication>();
		Iterator<Publication> iter = Result.iterator();
		while(iter.hasNext()){
			Publication temp = iter.next();
			if(temp.getYear()==null){
				newResult.add(temp);
			}
			else if(temp.getYear()>=from && temp.getYear()<=till)
				newResult.add(temp);
		}
		return newResult;
	}
}
