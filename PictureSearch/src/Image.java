public class Image {
    
    //멤버변수
    private String id;
    private String imageFileName;
    private String imageName;
    private String imageTags;  

    //생성자
    Image(String imageField){

    	//데이터가 없을 경우 대비
    	 this.id="";
         this.imageFileName="";
         this.imageTags="";
         
        String[] imageFields = this.parsing(imageField);
        
        this.id = imageFields[0];

        this.imageFileName = imageFields[1];

        for(int i=2; i<imageFields.length; i++){
            if(imageFields[i].startsWith("#")){
                this.imageTags = imageFields[i];
            }
            else{
                this.imageName = imageFields[i];
            }
        }
    }

    Image(){
        this.id="";
        this.imageFileName="";
        this.imageTags="";
    }

    //메소드
    //image 정보를 필드별로 나누는 메소드
    String[] parsing(String imageField){
        String[] parsed_imageField = imageField.split(";");

        for(int i=0; i<parsed_imageField.length; i++){
            parsed_imageField[i] = parsed_imageField[i].trim();
        }

        return parsed_imageField;
    }

    //image 정보를 다시 한 line으로 되돌리는 메소드(parsing의 반대)
    String parseToline(){

        String line = "";
        line += this.getId();
        line += ";";
        line += this.getImageFileName();
        line += ";";
        line += this.getImageTags();
        line += ";";

        return line;
    }
    
    // getter, setter
    public String getId() {
        return id;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageTags() {
        return imageTags;
    }

    // Setter 메서드
    public void setId(String id) {
        this.id = id;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageTags(String imageTags) {
        this.imageTags = imageTags;
    }
}


