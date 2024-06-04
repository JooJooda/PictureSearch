import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.*;

public class PicturePanel extends JPanel{
	public PicturePanel(Picture picture) {
		
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel topPanel = new JPanel(new BorderLayout());
		
		JPanel timeTagPanel = new JPanel();
		JLabel timeLabel = new JLabel(picture.getTimestamp().toString());
		timeTagPanel.add(timeLabel);
	    
		JPanel tagPanel = new JPanel();
		tagPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		JLabel picturetag = new JLabel(picture.getPictureTag().getTagInfo());
		tagPanel.add(picturetag);
		
		
		topPanel.add(timeTagPanel, BorderLayout.WEST);
		topPanel.add(tagPanel, BorderLayout.EAST);
		
		add(topPanel,BorderLayout.NORTH);
		
		// 이미지 panel
		JPanel imagepanel = new JPanel();
		JLabel imageLabel = new JLabel();
		
		System.out.println(picture.getPictureImage().getImageFileName());
		ImageIcon icon = new ImageIcon(picture.getPictureImage().getImageFileName());
		imageLabel.setIcon(icon);
		imagepanel.add(imageLabel);
		
		add(imagepanel, BorderLayout.WEST);		
		
		//stuff panel
		JPanel stuffpanel = new JPanel();
		stuffpanel.setLayout(new GridLayout(0,1));
		
		ArrayList<Stuff> stufflist = picture.getPictureStuffs();
		for(Stuff stuff : stufflist) {
			stuffpanel.add(new StuffPanel(stuff));
		}
		add(stuffpanel, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(stuffpanel);
	    add(scrollPane);
	    
		//comment panel
		JPanel commentpanel = new JPanel();
		commentpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel commentLabel = new JLabel(picture.getComment());
		commentpanel.add(commentLabel);
		
		add(commentpanel, BorderLayout.SOUTH);		
	}
}
