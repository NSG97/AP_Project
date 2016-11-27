import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.*;

public class Query2Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc;
	JButton SearchButton,ResetButton;
	JLabel sr_noOfPub;
	JTextField noOfPub;
	Database DB;
	ResultPanel RP;
	Query2Panel(ResultPanel SharedRP,Database SharedDB){
		super();
		RP = SharedRP;
		DB = SharedDB;
		this.setPreferredSize(new Dimension(300,390));
		this.setMinimumSize(new Dimension(300,390));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		addFillPublication();
		addButtons();
	}
	private void addFillPublication(){
		sr_noOfPub = new JLabel("Enter No of Publications: ");
		gbc.gridx=0;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(sr_noOfPub,gbc);
		
		noOfPub = new JTextField("");
		gbc.gridx=1;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=2;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		this.add(noOfPub,gbc);
	}
	
	private void addButtons(){
		SearchButton = new JButton("Search");
		SearchButton.setPreferredSize(new Dimension(100,20));
		SearchButton.setMinimumSize(new Dimension(100,20));
		ResetButton = new JButton("Reset");
		ResetButton.setPreferredSize(new Dimension(100,20));
		ResetButton.setMinimumSize(new Dimension(100,20));
		ResetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Q2Reset();
			}
		});
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(SearchButton,gbc);
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=1;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=0.2;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(ResetButton,gbc);
	}
	public void Q2Reset(){
		RP.Reset();
		noOfPub.setText("");
	}
}
