import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.*;
import java.util.Iterator;

// picturelist 객체를 하나 새로 만들고(원본 picturelist는 검색 후 되돌려야하니까 보존) 그 객체를 이용해서 search 구현
public class SearchFrame extends JFrame {
	// 레퍼펀스를 멤버변수로
	private PictureList pictureList;
	private PictureListPanel pictureListPanel;
	
	private SearchFrame.SearchActionListener searchActionListener;
	
	private ArrayList<JTextField> fieldList;
		
	// 생성자
	public SearchFrame(PictureList pictureList, PictureListPanel pictureListPanel) {
		this.pictureList = pictureList;
		this.pictureListPanel = pictureListPanel;

		fieldList = new ArrayList<JTextField>();
		
		setTitle("Search Picture");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	    
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
	  
		// upperPanel - time search
        JPanel upperPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0,2));
        
        // time search
        inputPanel.add(new JLabel("From"));
        JTextField fromField = new JTextField(20);
        fromField.setName("from");
        fieldList.add(fromField);
        inputPanel.add(fromField);
        
        inputPanel.add(new JLabel("To"));
        JTextField toField = new JTextField(20); 
        toField.setName("to");
        fieldList.add(toField);
        inputPanel.add(toField);
        
        upperPanel.add(inputPanel, BorderLayout.WEST);
        upperPanel.setBorder(new TitledBorder("Time Search"));
         
        JPanel timePanel = new JPanel();
        timePanel.add(new JLabel("(yyyy-MM-dd_HH:mm:ss)"));
        upperPanel.add(timePanel, BorderLayout.EAST);
        
        mainPanel.add(upperPanel);
        
        
        // bottom panel - keyword search
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainPanel.add(bottomPanel);
        bottomPanel.setBorder(new TitledBorder("Keyword Search"));
        
        // keyword search
        // left part
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
	    JTextField pictureTagsField = new JTextField(15);
	    pictureTagsField.setName("pictureTags");
        fieldList.add(pictureTagsField);
        keywordInputsPanel.add(pictureTagsField, gbc);
        
        gbc.gridx = 0; 
	    gbc.gridy = 1; 
	    gbc.weightx = 0.1; 
        keywordInputsPanel.add(new JLabel("Comments"), gbc);
        
        gbc.gridx = 1; 
	    gbc.gridy = 1; 
	    gbc.weightx = 0.9; 
	    JTextField commentsField = new JTextField(15);
	    commentsField.setName("comments");
        fieldList.add(commentsField);
        keywordInputsPanel.add(commentsField, gbc);
        
        keywordPanel.add(keywordInputsPanel, BorderLayout.WEST);
	
        // right part - stuffPanel
        JPanel stuffPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
	  
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0,1));
		
		JLabel typeLabel = new JLabel("Type");
	    JTextField typeField = new JTextField(10);
	    typeField.setName("type");
        fieldList.add(typeField);
	    
	    JLabel nameLabel = new JLabel("Name");
	    JTextField nameField = new JTextField(10);
	    nameField.setName("name");
        fieldList.add(nameField);

        JLabel tagLabel = new JLabel("Tags");
	    JTextField tagField = new JTextField(10);
	    tagField.setName("tags");
        fieldList.add(tagField);
	    
	    labelPanel.add(typeLabel);
	    labelPanel.add(nameLabel);
	    labelPanel.add(tagLabel);


		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(0,1));
		fieldPanel.add(typeField);
		fieldPanel.add(nameField);
		fieldPanel.add(tagField);
		    
		gbc3.gridx = 0; 
	    gbc3.gridy = 0; 
	    gbc3.weightx = 0.1; 
	    gbc3.fill = GridBagConstraints.BOTH; 
	    stuffPanel.add(labelPanel, gbc3);
	   
	    gbc3.gridx = 1; 
        gbc3.weightx = 0.9; 
        stuffPanel.add(fieldPanel, gbc3);
        
        keywordPanel.add(stuffPanel, BorderLayout.CENTER);
        bottomPanel.add(keywordPanel,BorderLayout.CENTER);
        
        // general search
        JPanel generalSearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        generalSearchPanel.add(new JLabel("General Search"));
        JTextField generalSearchField = new JTextField(20);
        generalSearchField.setName("generalSearch");
        fieldList.add(generalSearchField);
        generalSearchPanel.add(generalSearchField);
        
        bottomPanel.add(generalSearchPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        
        
        // buttons 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.searchActionListener = new SearchActionListener();
        
        JButton andSearchButton = new JButton("AND SEARCH");
        andSearchButton.setName("andSearch");
        andSearchButton.addActionListener(this.searchActionListener);
        
        JButton orSearchButton = new JButton("OR SEARCH");
        orSearchButton.setName("orSearch");
        orSearchButton.addActionListener(this.searchActionListener);
        
        JButton closeButton = new JButton("CLOSE");
        closeButton.addActionListener(new CloseButtonListener());

        buttonPanel.add(andSearchButton);
        buttonPanel.add(orSearchButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}
	
	// 메소드
	public SearchFrame.SearchActionListener getCommonActionListener() {
		return this.searchActionListener;
	}
	
	public void setCommonActionListener(SearchFrame.SearchActionListener searchActionListener) {
		this.searchActionListener = searchActionListener;
	}
	
	public boolean checkTimeField(LocalDateTime targetDateTime, JTextField fromField, JTextField toField) {
		String from = fromField.getText().trim();
		String to = toField.getText().trim();
		
		// 시간을 하나만 입력했을 경우를 대비해 기본값을 설정
		LocalDateTime fromDateTime = LocalDateTime.MIN;
		LocalDateTime toDateTime =  LocalDateTime.MAX;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

		
		if(from != null && !from.isEmpty()) {
			fromDateTime = LocalDateTime.parse(from, formatter);
		}
		if(to != null && !to.isEmpty()) {
			toDateTime = LocalDateTime.parse(to, formatter);
		}
		return !targetDateTime.isBefore(fromDateTime) && !targetDateTime.isAfter(toDateTime);
	}
	
	public boolean checkTimeField_forOr(LocalDateTime targetDateTime, JTextField fromField, JTextField toField) {
		String from = fromField.getText().trim();
		String to = toField.getText().trim();
		
		if(!from.isEmpty() || !to.isEmpty()) {
			// 시간을 하나만 입력했을 경우를 대비해 기본값을 설정
			LocalDateTime fromDateTime = LocalDateTime.MIN;
			LocalDateTime toDateTime =  LocalDateTime.MAX;
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

			fromDateTime = LocalDateTime.parse(from, formatter);
			toDateTime = LocalDateTime.parse(to, formatter);
			
			return !targetDateTime.isBefore(fromDateTime) && !targetDateTime.isAfter(toDateTime);
		}
		return false;
	}
	
	
	public boolean checkPictureTagsField(String pictureTag, JTextField pictureTagsField) {
		String tag = pictureTagsField.getText().trim();
		//System.out.println(tag);
		
		// 값이 없어도 동일한 것으로 취급하고 true 리턴
		if (tag.isEmpty() || pictureTag.equals("#" + tag)) {
	        return true;
	    }
		return false;
	}
	
	public boolean checkPictureTagsField_forOr(String pictureTag, JTextField pictureTagsField) {
		String tag = pictureTagsField.getText().trim();
		//System.out.println(tag);
		
		// 값이 없어도 동일한 것으로 취급하고 true 리턴
		if (!tag.isEmpty() && pictureTag.equals("#" + tag)) {
	        return true;
	    }
		return false;
	}
	
	
	public boolean checkCommentField(String pictureComment, JTextField commentsField) {
		String comment = commentsField.getText().trim();
		//System.out.println(comment);
		
		 if (comment.isEmpty() || pictureComment.equals(comment)) {
		        return true;
		    } 
		return false;
	}
	
	public boolean checkCommentField_forOr(String pictureComment, JTextField commentsField) {
		String comment = commentsField.getText().trim();
		//System.out.println(comment);
		
		 if (!comment.isEmpty() && pictureComment.equals(comment)) {
		        return true;
		    } 
		return false;
	}
	
	
	public boolean checkStuffField(ArrayList<Stuff> pictureStuffs, JTextField typeField, JTextField nameField, JTextField tagField){
		String type = typeField.getText().trim();
		String name = nameField.getText().trim();
		String tags = tagField.getText().trim();
		
		//System.out.println(type);
		//System.out.println(name);
		//System.out.println(tags);
		// 여러 개의 stuff 정보들 중 하나의 stuff 정보라도 맞는게 있다면 true 리턴
		for(Stuff stuff : pictureStuffs) {
			 if ((type.isEmpty() || stuff.getSort().equals(type))
				&& (name.isEmpty() || stuff.getName().equals(name)) 
				&& (tags.isEmpty() || stuff.getStuffTag().equals("#" + tags))) {
	            return true;  
	         }
	    }
		return false;
	}
	

	public boolean checkStuffField_forOr(ArrayList<Stuff> pictureStuffs, JTextField typeField, JTextField nameField, JTextField tagField){
		String type = typeField.getText().trim();
		String name = nameField.getText().trim();
		String tags = tagField.getText().trim();
		
		// 여러 개의 stuff 정보들 중 하나의 stuff 정보라도 맞는게 있다면 true 리턴
		for(Stuff stuff : pictureStuffs) {
			 if ((!type.isEmpty() && stuff.getSort().equals(type))
				|| (!name.isEmpty() && stuff.getName().equals(name)) 
				|| (!tags.isEmpty() && stuff.getStuffTag().equals("#" + tags))) {
	            return true;  
	         }
	    }
		return false;
	}
	
	public boolean checkGeneralSearchField(Picture picture, JTextField generalSearchField) {
		
		if(checkPictureTagsField(picture.getPictureTag().getTagInfo(), generalSearchField)) {
			return true;
		}
		if(checkCommentField(picture.getComment(), generalSearchField)){
			return true;
		}
		if(checkStuffField(picture.getPictureStuffs(), generalSearchField, generalSearchField, generalSearchField)) {
			return true;
		}
		return false;
	}
	
	public boolean checkGeneralSearchField_forOr(Picture picture, JTextField generalSearchField) {
		
		if(checkPictureTagsField_forOr(picture.getPictureTag().getTagInfo(), generalSearchField)) {
			return true;
		}
		if(checkCommentField_forOr(picture.getComment(), generalSearchField)){
			return true;
		}
		if(checkStuffField_forOr(picture.getPictureStuffs(), generalSearchField, generalSearchField, generalSearchField)) {
			return true;
		}
		return false;
	}
		
	
	
	public PictureList getPictureList() {
		return this.pictureList;
	}
	
	public PictureListPanel getPictureListPanel() {
		return this.pictureListPanel;
	}
	public ArrayList<JTextField> getFieldList(){
		return this.fieldList;
	}

class SearchActionListener implements ActionListener{

	private PictureList originalPictureListObj;
	private ArrayList<Picture> filteredPictureList = new ArrayList<Picture>();
	
	public SearchActionListener() {
		// 기존 PictureList 객체, 이 객체의 레퍼런스를 필터링 된 picturelist로 바꾸어서 검색 기능을 처리한다
		originalPictureListObj = getPictureList();
				
		// AND 검색용 PictureList.pictureList 객체
		initalizeFilteredPictureList();
	}
	
	public void initalizeFilteredPictureList() {
		this.filteredPictureList.clear();
		this.filteredPictureList.addAll(originalPictureListObj.getOriginalPictureList());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		ArrayList<JTextField> fieldList = getFieldList();
		
		JTextField fromField = null;
		JTextField toField = null;
		JTextField pictureTagsField = null;
		JTextField commentsField = null;
		JTextField typeField = null;
		JTextField nameField = null;
		JTextField tagField = null;
		JTextField generalSearchField = null;
		
		for (JTextField field : fieldList) {
			if(field.getName() != null) {
				String target = field.getName();
				
				switch (target) {
				case "from":
					fromField = field;
					break;
				case "to":
					toField = field;
					break;
				case "pictureTags":
					pictureTagsField = field;
					break;
				case "comments":
					commentsField = field;
					break;
				case "type":
					typeField = field;
					break;
				case "name":
					nameField = field;
					break;
				case "tags":
					tagField = field;
					break;
				case "generalSearch":
					generalSearchField = field;
					break;
				default :
					System.out.println("Unknown field");
				}
			}
		}
		
		// and 혹은 or로 분기 한다
		JButton source = (JButton)e.getSource();
		String sourceName = source.getName();
		
		if (sourceName.equals("andSearch")) {
            // AND SEARCH 버튼 클릭 시 동작
            System.out.println("AND SEARCH button clicked");
            
    		// 순회하면서 수정하려고 하니까 에러 발생. iterator을 이용해 안전하게 수정한다
    		Iterator<Picture> iterator =  filteredPictureList.iterator();  
    		
    		while (iterator.hasNext()) {
    		    Picture picture = iterator.next();
    		    if (!checkTimeField(picture.getTimestamp(), fromField, toField) ||
    		        !checkPictureTagsField(picture.getPictureTag().getTagInfo(), pictureTagsField) ||
    		        !checkCommentField(picture.getComment(), commentsField) ||
    		        !checkStuffField(picture.getPictureStuffs(), typeField, nameField, tagField) ||
    		        !checkGeneralSearchField(picture, generalSearchField)) {
    		        iterator.remove();  // 안전하게 요소 제거
    		    }
    		}
            
        } else if (sourceName.equals("orSearch")) {
            // OR SEARCH 버튼 클릭 시 동작
            System.out.println("OR SEARCH button clicked");
    		
    		// 순회하면서 수정하려고 하니까 에러 발생. iterator을 이용해 안전하게 수정한다
    		Iterator<Picture> iterator =  filteredPictureList.iterator();  
    		
    		while (iterator.hasNext()) {
    		    Picture picture = iterator.next();
    		    
    		    //잡앗다이자식
    		    boolean timeFieldCheck = checkTimeField_forOr(picture.getTimestamp(), fromField, toField);
    		    
    		    boolean pictureTagsFieldCheck = checkPictureTagsField_forOr(picture.getPictureTag().getTagInfo(), pictureTagsField);
    		    
    		    boolean commentsFieldCheck = checkCommentField_forOr(picture.getComment(), commentsField);
    		   
    		    boolean stuffFieldCheck = checkStuffField_forOr(picture.getPictureStuffs(), typeField, nameField, tagField);

    		    boolean generalSearchFieldCheck = !generalSearchField.getText().isEmpty() && checkGeneralSearchField_forOr(picture, generalSearchField);

    		        // 모든 조건이 false일 때만 요소를 제거
    		        if (!timeFieldCheck && !stuffFieldCheck && !pictureTagsFieldCheck && !commentsFieldCheck && !generalSearchFieldCheck) {
    		            iterator.remove();  // 조건을 만족하지 않는 요소 제거
    		        }  
    		    }
        }
		
		// 기존 PictureList.picturelist가 걸러진 pictureList를 가르키도록 레퍼런스를 바꾼다
		originalPictureListObj.setPictureList(filteredPictureList);
		
		// pictureList를 업데이트 한 후 그것을 바탕으로 다시 파일 쓰기
		originalPictureListObj.writeToFile("src/picture-normal.data");
		originalPictureListObj.setFilename("src/picture-normal.data");
		originalPictureListObj.updatePictureList();
		
		// 화면 업데이트
		getPictureListPanel().updatePanel();
        }
}

private class CloseButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}

}


