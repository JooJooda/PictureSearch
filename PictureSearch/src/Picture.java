import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.time.format.DateTimeParseException;

public class Picture{

    //멤버변수
    private String id;
    private LocalDateTime timestamp;   
    private Image pictureImage;               //Picture 클래스 내부 Image 클래스
    private ArrayList<Stuff> pictureStuffs;     //Picture 클래스 내부 Stuff 클래스의 배열
    private Tag pictureTag;                   //Picture 클래스 내부 Tag 클래스
    private String comment;   

    //생성자
    //파일로부터 사진 정보를 받아 Picture 객체를 만드는 생성자
    Picture(String[] infoFields){
        
    	
    	//데이터가 없는 경우, 기본 멤버변수 초기화
    	this.id = "";
    	this.timestamp = null;
        this.pictureStuffs = new ArrayList <Stuff>();
        this.pictureTag = new Tag();
        this.comment = "";
        
        
        //데이터가 있는 경우, 멤버변수 초기화
        boolean exist = false;
        for(int i=0; i<infoFields.length; i++){
            if(infoFields[i].startsWith("m_")){  //id로 분류
                this.id = infoFields[i];
            }
            else if(Character.isDigit(infoFields[i].charAt(0))){ //timestamp로 분류

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
                    this.timestamp = LocalDateTime.parse(infoFields[i], formatter);	
                } catch (DateTimeParseException e) {
                    this.timestamp = null;  //시간이 형식에 맞지 않으면 null를 임시로 부여하고 나중에 .now()로 timestamp 초기화
                    System.out.print("Wrong timestamp format." + infoFields[i] + "\n");
                }
                
            }
            else if(infoFields[i].startsWith("IMG")){  //image로 분류
                this.pictureImage = new Image(infoFields[i]);
            }
            else if(infoFields[i].startsWith("[")){  //stuff로 분류
                String[] Stuff_ele = parsingStuff(infoFields[i]);
                
                for(int j=0; j<Stuff_ele.length; j++){
                	Stuff newStuff = new Stuff(Stuff_ele[j]);
                    this.pictureStuffs.add(newStuff);

                    //StuffList에 Stuff 객체 추가
                    StuffList.stuffList.add(newStuff);
                }
            }
            else if(infoFields[i].startsWith("#")){  //tag로 분류
                this.pictureTag = new Tag(infoFields[i]);
            }
            else{    //comment로 분류
                this.comment = infoFields[i];
            }
        }

        //id 혹은 timestamp가 없는경우
        if(this.timestamp == null){
            System.out.println("No timestamp in the input.");
            this.timestamp = LocalDateTime.now();
        }
        if(this.id.equals("")){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
            String str_timestamp = now.format(formatter);
            this.id = "m_" + str_timestamp;
        }

    }
//
//    //image 파일 이름을 주면, 현재시각으로 timestamp 및 ID를 만드는 생성자
//    Picture(String imageFileName){
//    
//        //timestamp
//        LocalDateTime now = LocalDateTime.now();
//        this.timestamp = now;
//
//        //id
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("m_yyyy-MM-dd_HH:mm:ss:SSS");
//        this.id = this.timestamp.format(formatter); 
//        
//        //image
//        this.pictureImage = new Image();
//        this.pictureImage.setImageFileName(imageFileName);
//
//        //stuff
//        this.pictureStuffs = new ArrayList <Stuff>();
//
//        //tag
//        this.pictureTag = new Tag();
//
//        //comment
//        this.comment = "";
//    }
    

    //stuff 개별로 나누기
    String[] parsingStuff(String stuffField){

        String[] stuffFields = stuffField.split("\\[|\\]");

        List<String> filtered_stuffFields = Arrays.stream(stuffFields)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());

        String[] new_fields = filtered_stuffFields.toArray(new String[0]);

        return new_fields;
    }

    //Picture 객체 정보 출력
    public void print(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
        String timestamp = this.getTimestamp().format(formatter);
        Image pictureImage = this.getPictureImage();
        
    	String line = " < " + this.getId() + " > " + " < " + timestamp + " > "
    			+ " < " + pictureImage.getId() + ";" + pictureImage.getImageFileName() +
    			";" + pictureImage.getImageTags() + " > " + " < ";
        
    	ArrayList<Stuff> list = this.getPictureStuffs();
    	
        for(int i=0; i<list.size(); i++){
        	Stuff stuff = list.get(i);
        	line += "[" + stuff.getId() + ";" + stuff.getSort() + ";"
                    + stuff.getName() +";"+ stuff.getStuffTag() + "]";
        }
        
        line += " > " + " < " + this.getPictureTag().getTagInfo() + " > " + " < " + this.getComment() + " > ";
        
        System.out.println(line);
    }
    
    // getter, setter 추가
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Image getPictureImage() {
        return pictureImage;
    }
    
    public void setPictureImage(Image pictureImage) {
        this.pictureImage = pictureImage;
    }
    
    public ArrayList<Stuff> getPictureStuffs() {
        return this.pictureStuffs;
    }
    
    public void setPictureStuffs(ArrayList<Stuff> pictureStuffs) {
        this.pictureStuffs = pictureStuffs;
    }
    
    public Tag getPictureTag() {
        return pictureTag;
    }

    public void setPictureTag(Tag pictureTag) {
        this.pictureTag = pictureTag;
    }
    
    public String getComment() {
    	return this.comment;
    }
    
    public void setComment(String comment) {
    	this.comment = comment;
    }
}
