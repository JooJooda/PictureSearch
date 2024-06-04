import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SearchFrame extends JFrame {
	public SearchFrame() {

		setTitle("Search Picture");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	    
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
	  
		// time search
        
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0,2));
        inputPanel.add(new JLabel("From"));
        inputPanel.add(new JTextField(20));
        inputPanel.add(new JLabel("To"));
        inputPanel.add(new JTextField(20));
        panel.add(inputPanel, BorderLayout.WEST);
        panel.setBorder(new TitledBorder("Time Search"));
        
        JPanel timePanel = new JPanel();
        timePanel.add(new JLabel("(yyyy-MM-dd_HH:mm:ss)"));
        panel.add(timePanel, BorderLayout.EAST);
        
        mainPanel.add(panel);
        
        
        // bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainPanel.add(bottomPanel);
        bottomPanel.setBorder(new TitledBorder("Keyword Search"));
        
        // keyword search
        JPanel keywordPanel = new JPanel(new BorderLayout());
       
        
        JPanel keywordInputsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0; 
	    gbc.gridy = 0; 
	    gbc.weightx = 0.1; 
        keywordInputsPanel.add(new JLabel("Tags"), gbc);
        
		gbc.gridx = 1; 
	    gbc.gridy = 0; 
	    gbc.weightx = 0.9; 
        keywordInputsPanel.add(new JTextField(15), gbc);
        
        gbc.gridx = 0; 
	    gbc.gridy = 1; 
	    gbc.weightx = 0.1; 
        keywordInputsPanel.add(new JLabel("Comments"), gbc);
        
        gbc.gridx = 1; 
	    gbc.gridy = 1; 
	    gbc.weightx = 0.9; 
        keywordInputsPanel.add(new JTextField(15), gbc);
        
        keywordPanel.add(keywordInputsPanel, BorderLayout.WEST);
	
        
        JPanel stuffPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
	  
        JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,1));
		
		JLabel typeLabel = new JLabel("Type");
	    JTextField typeField = new JTextField(10);
	    
	    JLabel nameLabel = new JLabel("Name");
	    JTextField nameField = new JTextField(10);

        JLabel tagLabel = new JLabel("Tags");
	    JTextField tagField = new JTextField(10);
	    
	    panel2.add(typeLabel);
	    panel2.add(nameLabel);
	    panel2.add(tagLabel);


		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0,1));
		panel3.add(typeField);
		panel3.add(nameField);
		panel3.add(tagField);
		    
		gbc3.gridx = 0; 
	    gbc3.gridy = 0; 
	    gbc3.weightx = 0.1; 
	    gbc3.fill = GridBagConstraints.BOTH; 
	    stuffPanel.add(panel2, gbc3);
	   
	    gbc3.gridx = 1; 
        gbc3.weightx = 0.9; 
        stuffPanel.add(panel3, gbc3);
        
        keywordPanel.add(stuffPanel, BorderLayout.CENTER);
        
        bottomPanel.add(keywordPanel,BorderLayout.CENTER);
        
        // general search
        JPanel generalSearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        generalSearchPanel.add(new JLabel("General Search"));
        generalSearchPanel.add(new JTextField(20));
        
        bottomPanel.add(generalSearchPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // buttons 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton andSearchButton = new JButton("AND SEARCH");
        JButton orSearchButton = new JButton("OR SEARCH");
        JButton closeButton = new JButton("CLOSE");

        buttonPanel.add(andSearchButton);
        buttonPanel.add(orSearchButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
		setVisible(true);

		
	}
}
