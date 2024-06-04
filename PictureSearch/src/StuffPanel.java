import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StuffPanel extends JPanel{
	public StuffPanel(Stuff stuff) {
		
		setLayout(new GridBagLayout());
		setBorder(new LineBorder(Color.LIGHT_GRAY));
        GridBagConstraints gbc = new GridBagConstraints();

		JLabel typeLabel, typeLabel2, nameLabel, nameLabel2, tagsLabel, tagsLabel2;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		typeLabel2 = new JLabel(stuff.getSort());
		typeLabel2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		nameLabel2 = new JLabel(stuff.getName());
		nameLabel2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		tagsLabel2 = new JLabel(stuff.getStuffTag());
		tagsLabel2.setBorder(new LineBorder(Color.LIGHT_GRAY));
	        
		panel.add(typeLabel2);
		panel.add(nameLabel2);
		panel.add(tagsLabel2);
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,1));
		
		typeLabel = new JLabel("Type");
		nameLabel = new JLabel("Name");
		tagsLabel = new JLabel("Tags");
		 
		panel2.add(typeLabel);
		panel2.add(nameLabel);
		panel2.add(tagsLabel);	
		
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridy = 0; 
		 
		gbc.gridx = 0; 
	    gbc.weightx = 0.1; 
	     add(panel2, gbc);
	    
	    gbc.gridx = 1; 
        gbc.weightx = 0.9; 
        add(panel, gbc);
		

	}
}
