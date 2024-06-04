import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PictureFrame extends JFrame{
	// 멤버변수
	private PictureList pictureList;
	private PictureListPanel pictureListPanel;
	
	// 생성자
	public PictureFrame(PictureList picturelist) {
		
		this.pictureList = picturelist;
		
		setTitle("Simple Picture Search");
		setSize(700,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		
		// show all pictures button panel
		JButton showAllButton = new JButton("Show All Pictures");
		add(showAllButton, BorderLayout.NORTH);
		
		// picture data panel
		PictureListPanel picturelistpanel = new PictureListPanel(this.pictureList);
		this.pictureListPanel = picturelistpanel;
	    add(picturelistpanel, BorderLayout.WEST);
	    
		JScrollPane scrollPane = new JScrollPane(picturelistpanel);
	    add(scrollPane);
		
		// buttons panel
		JPanel btnsPanel = new JPanel(); 
		btnsPanel.setLayout(new GridLayout(0,1));
		
		JButton addButton = new JButton("Add");
		// PictureList 인스턴스 레퍼런스를 넘겨준다
		addButton.addActionListener(new AddListener(this.pictureList, this.pictureListPanel));
		
		JButton deleteButton = new JButton("Delete");
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadListener(this.pictureList, this.pictureListPanel));
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener(this.pictureList));
		
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchListener());
		
		btnsPanel.add(addButton);
		btnsPanel.add(deleteButton);
		btnsPanel.add(loadButton);
		btnsPanel.add(saveButton);
		btnsPanel.add(searchButton);
		add(btnsPanel, BorderLayout.EAST);

		setVisible(true);

	}
}

class AddListener implements ActionListener{
	// 멤버변수
	PictureList pictureList;
    PictureListPanel pictureListPanel;
	
	// 생성자
	public AddListener(PictureList pictureList, PictureListPanel pictureListPanel) {
		this.pictureList = pictureList;
		this.pictureListPanel = pictureListPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		new AddFrame(this.pictureList, this.pictureListPanel);
	}
}

class SearchListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		new SearchFrame();
	}
}

class SaveListener implements ActionListener{
	
	// 멤버변수
	private PictureList pictureList;
	
	// 생성자
	public SaveListener(PictureList picturelist) {
		this.pictureList = picturelist;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.pictureList.writeToFile("src/picture-output.data");
	}
}

class LoadListener implements ActionListener{
	
	// 멤버변수
	private PictureList pictureList;
	PictureListPanel pictureListPanel;
	
	// 생성자
	public LoadListener(PictureList picturelist, PictureListPanel picturelistpanel) {
		this.pictureList = picturelist;
		this.pictureListPanel = picturelistpanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		// PictureList 객체 레퍼런스를 가지고 있으니 메소드 사용가능
		// 다시 파일읽기를 하고 picturelist와 picturelistpanel을 업데이트
		this.pictureList.setFilename("src/picture-normal.data"); 
		this.pictureList.updatePictureList();
		this.pictureListPanel.updatePanel();	
	}
}
