package EventDatabaseSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;

public class FrontEnd extends JFrame implements ItemListener, ActionListener{

	/**
	 * The purpose of this class is to create the FrontEnd/GUI and provide listener functions.
	 * You cannot write/perform any database interaction functions/actions in this class.
	 * You can only invoke a suitable function from MiddleTier class on the click event of Submit button. 
	 */
	JCheckBox eventConference;
    JCheckBox eventJournal;
    JCheckBox eventBook;
    JRadioButton allDates;
    JRadioButton dateRange;
    ButtonGroup dateSelection;
    JTextArea queryOutput;
    JLabel fromLabel;
    JLabel toLabel;
    JTextField fromDate;
    JTextField toDate;
    JButton submitQuery;
    Object source;
    boolean eventSelected;
    boolean journalSelected;
    boolean bookSelected;
    int count = 0;
    
	public FrontEnd() {
		initialize();
	    this.eventSelected = false;
	    this.bookSelected = false;
	    this.journalSelected = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel emptyLabel1 = new JLabel("                    \n          ");
        JPanel emptyPanel1 = new JPanel(new GridLayout(0, 1));
        emptyPanel1.add(emptyLabel1);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		
        this.add(emptyPanel1, c);
                
		eventConference = new JCheckBox("EventConference");
		eventConference.setSelected(false);
		eventJournal = new JCheckBox("EventJournal");
		eventJournal.setSelected(false);
		eventBook = new JCheckBox("EventBook");
		eventBook.setSelected(false);        
		eventConference.addItemListener(this);
		eventJournal.addItemListener(this);
		eventBook.addItemListener(this);
		JPanel eventPanel = new JPanel(new GridLayout(0, 1));
		eventPanel.add(eventConference);
		eventPanel.add(eventJournal);
		eventPanel.add(eventBook);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
        this.add(eventPanel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
        this.add(emptyPanel1, c);

        allDates = new JRadioButton("All Events");
        allDates.setSelected(true);
        dateRange = new JRadioButton("Period");
        dateRange.setSelected(false);        
        dateSelection = new ButtonGroup();
        allDates.addItemListener(this);
        dateRange.addItemListener(this);
        dateSelection.add(allDates);
        dateSelection.add(dateRange);        
		JPanel datePanel = new JPanel(new GridLayout(0, 1));
		datePanel.add(allDates);
		datePanel.add(dateRange);

        fromLabel = new JLabel("       From ");
        fromLabel.setEnabled(false);
        toLabel = new JLabel("       To ");
        toLabel.setEnabled(false);
        fromDate = new JTextField();
        fromDate.setEnabled(false);
        toDate = new JTextField();
        toDate.setEnabled(false);
        JPanel dateRangePanel = new JPanel(new GridLayout(0, 4));
		dateRangePanel.add(fromLabel);
		dateRangePanel.add(fromDate);
		dateRangePanel.add(toLabel);
		dateRangePanel.add(toDate);
		datePanel.add(dateRangePanel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 0;
        this.add(datePanel, c);
        
        JLabel emptyLabel2 = new JLabel("                    \n          ");
        JPanel emptyPanel2 = new JPanel(new GridLayout(0, 1));
        emptyPanel2.add(emptyLabel2);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
        this.add(emptyPanel2, c);

        submitQuery = new JButton("Submit");
        submitQuery.addActionListener(this);
		JPanel submitPanel = new JPanel(new GridLayout(0, 1));
		submitPanel.add(submitQuery);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 6;
		c.gridy = 2;
        this.add(submitPanel, c);

        JLabel emptyLabel3 = new JLabel("                    \n          ");
        JPanel emptyPanel3 = new JPanel(new GridLayout(0, 1));
        emptyPanel3.add(emptyLabel3);
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
        this.add(emptyPanel3, c);

        queryOutput = new JTextArea(15,100);
        queryOutput.setText("The\nQuery\nOutput\nwill\nAppear\nhere.");
		JScrollPane outputPanel = new JScrollPane(queryOutput);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
        this.add(outputPanel, c);
        

		this.setBounds(50, 50, 1200, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//this function is the listener for three check boxes and two radio buttons
	public void itemStateChanged(ItemEvent e) {

//	    Object source = e.getItemSelectable();
		this.source = e.getItemSelectable();
	    int i = 0, numSelected = 0 ;

	    if (source == eventConference) {
	    	count += 1;
	    	queryOutput.setText("\tEvent Conference Table");
	    	System.out.println("1");
	    	if(this.eventSelected) {
	    		this.eventSelected = false;
	    	}
	    	else {
	    		this.eventSelected = true;
	    	}

	    } else if (source == eventJournal) {
	    	count += 1;
	    	queryOutput.setText("\tEvent Journal Table");
	    	System.out.println("2");
	    	if(this.journalSelected) {
	    		this.journalSelected = false;
	    	}
	    	else {
	    		this.journalSelected = true;
	    	}

	    } else if (source == eventBook) {
	    	count += 1; 
	    	queryOutput.setText("\tEvent Book Table");
	    	System.out.println("3");
	    	if(this.bookSelected) {
	    		this.bookSelected = false;
	    	}
	    	else {
	    		this.bookSelected = true;
	    	}

	    } else if (source == dateRange) {
	    	if (dateRange.isSelected()) {
	            fromLabel.setEnabled(true);
	            toLabel.setEnabled(true);
	            fromDate.setEnabled(true);
	            toDate.setEnabled(true);	    		
	    	}
	    	else {
	            fromLabel.setEnabled(false);
	            toLabel.setEnabled(false);
	            fromDate.setEnabled(false);
	            toDate.setEnabled(false);	    			    		
	    	}
	    	System.out.println("4");
	    } else if (source == allDates) {
	    	System.out.println("5");
	    }
	    
	}
	
    /** Listens to the submit button click */
    public void actionPerformed(ActionEvent e) {

    	int i=0;
    	Connection c = MiddleTier.getConnection();
    	try {	
    		if(true) {
				String finalOutput = "";
    			if(this.eventSelected) {
    	    	
    				Statement st = c.createStatement();
    				ResultSet rs = (st.executeQuery("select * from eventConference;"));
    				ResultSetMetaData rsmd = rs.getMetaData();
    				int numOfCol = rsmd.getColumnCount();
    				System.out.println(numOfCol);
    				while (rs.next()) {
    					for(i = 1; i<=numOfCol; i++) {
    						finalOutput += rs.getString(i) + "\t";
    					}
    		    	
    					finalOutput += "\n";
    				}
					finalOutput += "\n";
	    	    	System.out.println(finalOutput);
//    	    	System.out.println(" ");
    	    	//queryOutput.setText(queryOutput.getText()+"\n"+fromDate.getText()+"\n"+toDate.getText());
    			}
    			if(this.journalSelected) {
    				Statement st = c.createStatement();
    				ResultSet rs = (st.executeQuery("select * from eventJournal;"));
    				ResultSetMetaData rsmd = rs.getMetaData();
    				int numOfCol = rsmd.getColumnCount();
    				System.out.println(numOfCol);
    				while (rs.next()) {
    					for(i = 1; i<=numOfCol; i++) {
    						finalOutput += rs.getString(i) + "\t";
    					}
    		    	
    					finalOutput += "\n";
    				}
					finalOutput += "\n";
    	    	System.out.println(finalOutput);
    			}
    			if(this.bookSelected) {
    				Statement st = c.createStatement();
    				ResultSet rs = (st.executeQuery("select * from eventBook;"));
    				ResultSetMetaData rsmd = rs.getMetaData();
    				int numOfCol = rsmd.getColumnCount();
    				System.out.println(numOfCol);
    				while (rs.next()) {
    					for(i = 1; i<=numOfCol; i++) {
    						finalOutput += rs.getString(i) + "\t";
    					}
    					
    					finalOutput += "\n";
    				}
					finalOutput += "\n";
//    	    		System.out.println(" ");
    			}
				queryOutput.setText(finalOutput);
    		}
//	    	queryOutput.setText((String) this.source);
		} catch (SQLException e1) {
	    	queryOutput.setText("ERROR");
			e1.printStackTrace();
		}
    }

}
