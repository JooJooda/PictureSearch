import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class PictureList {

    //멤버변수
	private ArrayList<Picture> pictureList = new ArrayList<Picture>();
	private String filename;

    //생성자
    //인자로 파일이름을 주면 해당 파일에 기술된 사진 정보들로 리스트를 만드는 생성자
    PictureList(String filename){
      this.filename =filename;
      updatePictureList();
     
    }
    

    //메소드
    // ArrayList pictureList를 업데이트하는 메소드
    public void updatePictureList() {
    	this.pictureList.clear();
    	
    	FileManager file = new FileManager(this.getFilename());
        file.readFile();
        ArrayList<String> list = file.getInfoLines();

        int i = 0;
        while((i<list.size())&&(list.get(i) != null)){  
            String[] infoFields = file.parseField(list.get(i));
            Picture pic = new Picture(infoFields);
            boolean exist = isExist(pic);
          //중복id라면 picturelist에 넣지 않는다.
            if(!exist){   
            	pictureList.add(pic);
            }  
            else{
                System.out.print("id : "+ pic.getId() + "already exists" + "\n");
            }
            i++;
        }
    }

    //모든 사진,사물 정보들을 timestamp 시간 순으로 정렬하는 메소드
    public void sortByDate(){
        Collections.sort(pictureList, new Comparator<Picture>() {// Comparator를 사용하여 객체 정렬 기준을 전달
            @Override
            public int compare(Picture p1, Picture p2){   //compare 함수를 재정의
                if (p1 == null || p2 == null) {    //객체를 못 찾거나 null인 경우 처리
                    return 0; 
                }
                return p1.getTimestamp().compareTo(p2.getTimestamp());   //timestamp를 기준으로 정렬
            }            
        });
    }

    //중복 id를 체크하는 메소드
    boolean isExist(Picture pic){
         
        for(int j=0; j<pictureList.size(); j++){
            if(pictureList.get(j).getId().equals(pic.getId())){  
                return true;
            }
        }    
        return false;     
    }


    //PictureList 객체를 파일에 저장하는 메소드
    void writeToFile(String filename){
       try {
    	   File file = new File(filename);
    	   
    	   PrintWriter writer = new PrintWriter(file);
    	   String infoLine;

    	   //Picturelist의 Picture 객체를 파싱해서 한 줄로 다시 만드는 코드
    	   for(Picture picture : pictureList){
            
    		   if(picture != null){
    			   infoLine = "";

    			   //id 데이터 다시 한 줄로 파싱
    			   infoLine += " < " + picture.getId() + " > ";

    			   //timestamp 데이터 다시 한 줄로 파싱
    			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
    			   String str_timestamp = picture.getTimestamp().format(formatter);
    			   infoLine += " < " + str_timestamp + " > ";

    			   //image 데이터 다시 한 줄로 파싱
    			   infoLine += " < " + picture.getPictureImage().parseToline() + " > ";
 
    			   //stuff 데이터 다시 한 줄로 파싱
    			   infoLine += " < ";
            
    			   ArrayList<Stuff> stufflist = picture.getPictureStuffs();
            
    			   for(Stuff stuff : stufflist){
    				   infoLine += stuff.parseToLine();
    			   }
    			   infoLine += " > ";
             
    			   //tag 데이터 다시 한 줄로 파싱
    			   infoLine += " < " + picture.getPictureTag().getTagInfo() + " > ";
             
    			   //comment 데이터 다시 한 줄로 파싱
    			   infoLine += " < " + picture.getComment() + " > ";
             
    			   writer.println(infoLine);
    		   }
       
    	   }
    	   writer.close();
       	} 
       catch(Exception e){
           System.out.println(e);
       }
    }
    
    // getter, setter
    public ArrayList<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(ArrayList<Picture> pictureList) {
        this.pictureList = pictureList;
    }
    
    public String getFilename() {
        return filename;
    }

    // Setter for filename
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
    
    
