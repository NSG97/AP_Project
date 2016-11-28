/*
 * class responsible for creating query3 panel
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Query3Panel extends JPanel  {
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc;
	JTextField author_name;
	JTextField year_of_prediction;
	JButton SearchButton;
	JButton ResetButton;
	Database DB;
	ResultPanel RP;
	
	Query3Panel(ResultPanel SharedRP,Database SharedDB){
		DB=SharedDB;RP=SharedRP;
		this.setPreferredSize(new Dimension(300,390));
		this.setMinimumSize(new Dimension(300,390));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.CENTER;
		addButtons();
	}
	private void addButtons(){
		JLabel Enter_author = new JLabel("Enter Author: ");
		gbc.gridx=0;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(Enter_author,gbc);
		
		author_name = new JTextField("");
		gbc.gridx=1;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=2;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(author_name,gbc);	
		
		JLabel Enter_years=new JLabel("Enter year");
		gbc.gridx=0;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(Enter_years, gbc);
		
		year_of_prediction=new JTextField("");
		gbc.gridx=1;gbc.gridy=1;
		gbc.weightx=1;gbc.weighty=2;
		gbc.gridheight=1;gbc.gridwidth=2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(year_of_prediction, gbc);
		
		
		SearchButton = new JButton("Search");
		SearchButton.setPreferredSize(new Dimension(100,20));
		SearchButton.setMinimumSize(new Dimension(100,20));
		SearchButton.addActionListener(new SearchButtonActionListener());
		ResetButton = new JButton("Reset");
		ResetButton.setPreferredSize(new Dimension(100,20));
		ResetButton.setMinimumSize(new Dimension(100,20));
		ResetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Q3Reset();
			}
		});
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=2;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(SearchButton,gbc);
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=1;gbc.gridy=2;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(ResetButton,gbc);
	}
	private void Q3Reset(){
		author_name.setText("---");
		year_of_prediction.setText("---");
		
	}
	private class SearchButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(author_name.getText().equals("") || year_of_prediction.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Enter No. of Publications.");
			}
			else{
				try{
					float[] pred_act = DB.Predict(author_name.getText(),Integer.parseInt(year_of_prediction.getText()));
					float deviation = Math.abs(pred_act[0]-pred_act[1])/(pred_act[1]);
					JOptionPane.showMessageDialog(null, "Predicted: "+pred_act[0]+"\nActual: "+pred_act[1]+"\nDeviation: "+deviation+"%");
				}
				catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid Input.");
				}
			}
		}
	}
}
