import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PictureFrame extends JFrame{
	// 멤버변수
	private PictureList pictureList;
	private PictureListPanel pictureListPanel;
	private SearchFrame.SearchActionListener searchActionListener;
	
	// 생성자
	public PictureFrame(PictureList picturelist) {
		
		this.pictureList = picturelist;
		
		setTitle("Simple Picture Search");
		setSize(700,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		
		// show all pictures button panel
		JButton showAllPictureBtn = new JButton("Show All Pictures");
		showAllPictureBtn.addActionListener(new showAllPictureBtnListener()); 
		add(showAllPictureBtn, BorderLayout.NORTH);
		
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
		addButton.addActionListener(new AddListener());
		
		JButton deleteButton = new JButton("Delete");  // delete 시 originalPictureList 도 업데이트 필요!!!!!!!!!!!
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadListener());
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener());
		
		
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
	
	public PictureList getPictureList() {
		return this.pictureList;
	}
	
	public PictureListPanel getPictureListPanel() {
		return this.pictureListPanel;
	}
	
	public SearchFrame.SearchActionListener getCommonActionListener() {
		return this.searchActionListener;
	}
	
	public void setCommonActionListener(SearchFrame.SearchActionListener searchActionListener) {
		this.searchActionListener = searchActionListener;
	}
	
	
// 내부 클래스 이벤트 리스너
private class showAllPictureBtnListener implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		
		PictureList pictureList = getPictureList();
		
		// 깉은 복사를 해두었던 pictureList 객체를 가르키도록 레퍼런스를 바꿔준다
		pictureList.setPictureList(pictureList.getOriginalPictureList()); 
		
		// 그것을 기반으로 파일을 업데이트
		pictureList.writeToFile("src/picture-normal.data");
		pictureList.setFilename("src/picture-normal.data");
		
		// 화면 & picturelist 업데이트
		pictureList.updatePictureList();
		getPictureListPanel().updatePanel();
		
		// filteredPictureList 초기화
		getCommonActionListener().initalizeFilteredPictureList();
	}
}
	
private class AddListener implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		new AddFrame(pictureList, pictureListPanel);
	}
}
 
private class SearchListener implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		SearchFrame searchFrame = new SearchFrame(pictureList, pictureListPanel);
		setCommonActionListener(searchFrame.getCommonActionListener());
	}
}

private class SaveListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		pictureList.writeToFile("src/picture-output.data");
	}
}

private class LoadListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		
		// PictureList 객체 레퍼런스를 가지고 있으니 메소드 사용가능
		// 다시 파일읽기를 하고 picturelist와 picturelistpanel을 업데이트
		pictureList.setFilename("src/picture-normal.data"); 
		pictureList.updatePictureList();
		pictureListPanel.updatePanel();	
	}
}

	
}
