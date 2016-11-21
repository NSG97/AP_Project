import java.awt.*;

import javax.swing.*;

public class ResultPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	String[] column_names={"S.No.","Authors","Title","Pages","Year","Volume","Journal/Booktitle","URL"};
	JPanel tPanel = new JPanel();
	JTable table;GridBagConstraints gbc = new GridBagConstraints();
	JButton Next = new JButton("Next");
	Object[][] data = new Object[20][8];
	ResultPanel(){
		super();
		this.setPreferredSize(new Dimension(600,600));
		this.setMinimumSize(new Dimension(600,600));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		SetUpTable();
	}
	private void SetUpTable(){
		table = new JTable(data,column_names);
		table.setPreferredSize(new Dimension(600,400));
		table.setMinimumSize(new Dimension(600,400));
		JScrollPane sp = new JScrollPane(table);
		tPanel.add(sp);
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=0;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.BOTH;
		this.add(tPanel,gbc);
		
		
		Next.setSize(200,100);
		Next.setAlignmentX(CENTER_ALIGNMENT);
		Next.setAlignmentY(TOP_ALIGNMENT);
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridx=0;gbc.gridy=1;
		gbc.gridheight=1;gbc.gridwidth=1;
		gbc.weightx=1.0;gbc.weighty=1.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		this.add(Next,gbc);
	}
}
