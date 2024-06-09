import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.FileWriter;

public class AddFrame extends JFrame {
	
	// 레퍼런스를 멤버변수로
	private PictureList pictureList;
	private PictureListPanel pictureListPanel;
	
	// 나중을 정보 활용을 위해 stuff 패널은 arraylist로 관리한다
	private ArrayList<JPanel> stuffPanels = new ArrayList<JPanel>();
    
	// 생성자
	public AddFrame(PictureList pictureList, PictureListPanel pictureListPanel) {
		this.pictureList = pictureList;
		this.pictureListPanel = pictureListPanel;
		
		setTitle("Add a Picture");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// northPanel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// time
		JLabel time = new JLabel("Time");
		JTextField timefield = new JTextField(20);

		// tag
		JLabel pictureTags = new JLabel("(Pictures) Tags");
		JTextField tagsfield = new JTextField(20);
		
		 northPanel.add(time); 
		 northPanel.add(timefield);
		 northPanel.add(pictureTags); 
		 northPanel.add(tagsfield);
		 
		 add(northPanel, BorderLayout.NORTH);
		 
		 
		 // centerPanel
		 JPanel centerPanel = new JPanel();
		 centerPanel.setLayout(new BorderLayout());
		 
		 // image
		 JButton imageBtn = new JButton("Select Image File");
		 
		 imageBtnListener imageBtnListener = new imageBtnListener();
		 imageBtn.addActionListener(imageBtnListener);
		 centerPanel.add(imageBtn,  BorderLayout.WEST);
		 
		 
		 // stuff
	     // stuff 정보란
	     JPanel stuffPanelContainer = new JPanel();
	     stuffPanelContainer.setLayout(new GridLayout(0,1));
	     
	     addNewStuffPanel(stuffPanelContainer);
	     
		 centerPanel.add(stuffPanelContainer, BorderLayout.CENTER);
		 add(centerPanel, BorderLayout.CENTER);
		 
		 
		 // southPanel
		 JPanel southPanel = new JPanel();
		 southPanel.setLayout(new BorderLayout());
		 

		 // commentPanel
	     JPanel commentsPanel = new JPanel();
	     commentsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		 JLabel comments = new JLabel("Comments");
		 JTextField commentfield = new JTextField(40);
		 
		 commentsPanel.add(comments);
	     commentsPanel.add(commentfield);
	     
	     southPanel.add(commentsPanel, BorderLayout.NORTH);

	     // buttonPanel
	     JPanel buttonPanel = new JPanel();
	     buttonPanel.setLayout(new BorderLayout());

		 JButton moreStuffBtn = new JButton("More Stuff");
		 moreStuffBtn.addActionListener(new moreStuffBtnListener(stuffPanelContainer));
		 
		 // input end button
		 JButton okBtn = new JButton("OK-INPUT END");
		 okBtn.addActionListener(new okBtnListener(timefield, imageBtnListener, commentfield, tagsfield, 
				  this.pictureList, this.pictureListPanel));
		 
		 buttonPanel.add(moreStuffBtn, BorderLayout.WEST);
	     buttonPanel.add(okBtn, BorderLayout.EAST);
	     
	     southPanel.add(buttonPanel, BorderLayout.SOUTH);
	     add(southPanel, BorderLayout.SOUTH);

		 
		 setVisible(true);
	}
	
	// 메소드
    public ArrayList<JPanel> getStuffPanels(){
    	return this.stuffPanels;
    }
	
	public void addNewStuffPanel(JPanel stuffPanelContainer) {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridBagLayout());
		newPanel.setBorder(new LineBorder(Color.BLACK));
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(5, 5, 5, 5);
	    gbc.fill = GridBagConstraints.BOTH;
	     
        JLabel typeLabel = new JLabel("Type");
		JTextField type = new JTextField();
		JLabel nameLabel = new JLabel("Name");
		JTextField name = new JTextField();
		JLabel tagLabel = new JLabel("Tags");
		JTextField tags = new JTextField();
		tags.setName("tags"); // 앞에 # 를 붙여야함으로 이름을 통해 구별한다
			 
	    gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		newPanel.add(typeLabel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.9;
		newPanel.add(type, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		newPanel.add(nameLabel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.9;
		newPanel.add(name, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.2;
		newPanel.add(tagLabel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.8;
		newPanel.add(tags, gbc);
		
		stuffPanels.add(newPanel);
		stuffPanelContainer.add(newPanel);
	}

// 내부클래스 이벤트 리스너
private class imageBtnListener implements ActionListener{
	private File selectedFile;
	
	public void actionPerformed(ActionEvent e) {
		
		JFrame frame = new JFrame();
	
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(frame);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			this.selectedFile = fileChooser.getSelectedFile();
			// 이미지가 선택되면 이미지를 띄운다
			ImageIcon icon = new ImageIcon(selectedFile.getPath());
			JButton sourceButton = (JButton)e.getSource();
			
			sourceButton.setIcon(icon);
			sourceButton.setText("");
		}
		
	}
	
	public String makeImageInfo() {
		
		// image 파일 정보 한 줄 생성
		String imageInfoLine = "";
		
		// image id 생성
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		String imageId = now.format(formatter);
	
		imageInfoLine += "IMG" + imageId + "; ";
		
		// image filename 생성
		String path = this.selectedFile.getPath();
		imageInfoLine += path + ";";
		
		return imageInfoLine;
	}
}

private class okBtnListener implements ActionListener{
	private JTextField timefield;
	private imageBtnListener imageBtnListener;
	private JTextField commentfield;
	private JTextField tagsfield;
	private PictureList pictureList;
	private PictureListPanel pictureListPanel;
	
	public okBtnListener(JTextField timefield, imageBtnListener imageBtnListener, 
			JTextField commentfield, JTextField tagsfield, 
			PictureList pictureList, PictureListPanel pictureListPanel) {
		this.timefield = timefield;
		this.imageBtnListener = imageBtnListener;
		this.commentfield = commentfield;
		this.tagsfield = tagsfield;
		this.pictureList = pictureList;
		this.pictureListPanel = pictureListPanel;
	}

	public void actionPerformed(ActionEvent e) {
		
		// Picture 생성자에 넘겨줄 사진 정보 한 줄을 만든다
		String line = "";
		
		// id는 없이 넘긴다
		line += "<> ";
		
		// timestamp
		String timestamp = this.timefield.getText();
		line += "< " + timestamp + " > ";
		
		// image
		line += "<" + this.imageBtnListener.makeImageInfo() + ">";
		
		// stuff 
		line += "< ";
		
		for(JPanel stuffpanel :getStuffPanels()) {
			line += "[";
			Component[] components = stuffpanel.getComponents();
			// stuff 정보란 component를 찾는다
			for(Component target :components) {
				if(target != null && target instanceof JTextField) {
					line += ";";
					JTextField textField = (JTextField) target;
					String str = "";
					if(textField.getName() != null && textField.getName().equals("tags") && !textField.getText().trim().isEmpty()) {
						str += "#";
					} 
					str += textField.getText();
					line += str;
				}
				else {
					line += "";
				}
			}
			line += "]";
		}
		line += " >";
			
			
		// picturetag
		String pictureTag = this.tagsfield.getText();
		
		line += " <";
		if(!pictureTag.isEmpty()) {
			line += "#" + pictureTag;
		}
		
		line += ">";
		
		// comment
		String comment = this.commentfield.getText();
		line += "<" + comment + "> ";
		
		// 생성한 사진 정보 데이터 한 줄을 파일에 써 업데이트(추가)한다
		try {
			FileWriter fw = new FileWriter("src/picture-normal.data", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println();
			pw.println(line);
			
			// flush 해서 변경사항 반영
			pw.flush();
		}
		catch(Exception error) {
			System.out.println(error);
		}
		
		// 메인화면 갱신
		this.pictureList.setFilename("src/picture-normal.data"); 
		this.pictureList.updatePictureList();
		this.pictureList.setOriginalPictureList(pictureList.getPictureList()); // add 시 originalPictureList도 업데이트 해준다
		this.pictureListPanel.updatePanel();
		
		// frame 닫기
		dispose();
	}
	

}

private class moreStuffBtnListener implements ActionListener{
	
	private JPanel stuffPanelContainer;
	
	public moreStuffBtnListener(JPanel stuffPanelContainer) {
		this.stuffPanelContainer =stuffPanelContainer;
	}
	public void actionPerformed(ActionEvent e) {
				
		// stuff 란 추가
		addNewStuffPanel(this.stuffPanelContainer);
		
		// 재구성
		revalidate();
		repaint();
	}
}

}


