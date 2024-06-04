import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args){

        PictureList myPictureList = new PictureList("src/picture-normal.data"); 
        myPictureList.sortByDate();
        
        System.out.println("사진 객체 한번에 생성:");
        
        ArrayList<Picture> picturelist = new ArrayList<Picture>();
        picturelist = myPictureList.getPictureList();
        
        for(int i=0; i<picturelist.size(); i++){
            System.out.println(i);
            picturelist.get(i).print();
        }

        System.out.println("StuffList 출력:");
        StuffList.print();
    
        // GUI 구현
        new PictureFrame(myPictureList);
        
    }

}

