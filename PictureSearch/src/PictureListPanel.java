import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PictureListPanel extends JPanel {
	// 멤버변수
	private PictureList pictureList;
	
	// 생성자
	public PictureListPanel(PictureList picturelist) {
		
		this.pictureList = picturelist;
        setLayout(new GridLayout(0,1));
		updatePanel();
	}
	
	// 메소드
	void updatePanel() {
		// 컴포넌트를 제거하고
		removeAll();
		for(Picture picture : this.pictureList.getPictureList()) {
			add(new PicturePanel(picture));
		}
		
		// 변경사항 적용
		revalidate(); // 컴포넌트 재배치
        repaint();    // 컴포넌트 다시 그리기
	}
}

