import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;

public class Stuff{
    
    //멤버변수
    private String id;         //이하는 stuff 정보 한줄을 필드별로 나눈 것 
    private String sort;       
    private String name;
    private String stuffTag;


    //생성자
    Stuff(String stuffField){

        //데이터가 없는 경우, 기본 멤버변수 초기화
        this.id = "";
        this.sort = "";
        this.name = "";
        this.stuffTag = "";
    
      
        //데이터가 있는 경우, 멤버변수 초기화
        String[] stuffInfos = parsingIntoField(stuffField); 

        for(int i=0; i<stuffInfos.length; i++){
            if(Character.isDigit(stuffInfos[i].charAt(0))){
                this.id = stuffInfos[i];
            }
            else if(stuffInfos[i].startsWith("#")){
                this.stuffTag = stuffInfos[i];
            }
            else{
                //sort값 먼저 부여
                if(this.sort.equals("")){
                    this.sort = stuffInfos[i];
                }
                //이후 name 값 부여
                else{
                    this.name = stuffInfos[i];
                }
            }
        }
    }

    Stuff(){
        this.id = "";
        this.sort = "";
        this.name = "";
        this.stuffTag = "";
    }

  
    //메소드
    //stuff 필드별(id,sort,name)로 나누기
    String[] parsingIntoField(String stuffField){
        String[] stuffInfos = stuffField.split(";");

        List<String> parsed_stuffInfos = Arrays.stream(stuffInfos)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());

        String[] new_stuffInfos = parsed_stuffInfos.toArray(new String[0]);

        return new_stuffInfos;
    }

    //원래 stuff 구조로 되돌리는 메소드
    String parseToLine(){
        String line = "[" + this.getId() + ";" +this.getSort() + ";" + this.getName() + ";" 
    + this.getStuffTag() + ";" +"]";

        return line;
    }

    //랜덤하게 새 stuff id를 생성하고 부여하는 메소드
    void getRandomId(int length){
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < length; i++) {
            id.append(ThreadLocalRandom.current().nextInt(0, 10)); // 0부터 9까지의 랜덤 숫자 생성
        }
        this.setId(id.toString());
    }
    
    // getter, setter
    public String getId() {
        return id;
    }

    public String getSort() {
        return sort;
    }

    public String getName() {
        return name;
    }

    public String getStuffTag() {
        return stuffTag;
    }

    // Setter 메서드
    public void setId(String id) {
        this.id = id;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStuffTag(String stuffTag) {
        this.stuffTag = stuffTag;
    }
   
}