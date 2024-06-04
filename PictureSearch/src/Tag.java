
public class Tag {

    //멤버변수
    private String tagInfo;

    //생성자
    Tag(String tagField){
        this.tagInfo = tagField;
    }

    Tag(){
        this.tagInfo =""; 
    }
    
    // getter, setter
    public String getTagInfo() {
        return tagInfo;
    }

    // Setter 메서드
    public void setTagInfo(String tagInfo) {
        this.tagInfo = tagInfo;
    }
    
}
