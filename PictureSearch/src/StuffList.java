import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class StuffList {
    
    //멤버변수
    public static ArrayList<Stuff> stuffList = new ArrayList<Stuff>();

    //메소드
    //같은 사물이 이미 존재하는지 유무 확인하고 stufflist에 stuff 객체 추가하는 메소드
    //같은 사물이면 해당 ID를, 다른 사물이 처음 등장하면 새로운 ID를 할당한다
    public static void add(Stuff newStuff){

        boolean exist = false;

        for(int i=0; i<StuffList.stuffList.size(); i++){

        	Stuff stuff = StuffList.stuffList.get(i);
        	
        	//sort와 name이 같으면 동일한 stuff로 처리
            //주의! 자바에서는 string 변수를 비교할때 == 연산자를 쓰면 주소값을 비교하게 된다. equals()함수를 써야함.
            if(stuff.getSort().equals(newStuff.getSort()) && stuff.getName().equals(newStuff.getName())) {  
                newStuff.setId(stuff.getId()); //해당 ID를 지급한다 
                exist = true;
                break;
            }
        }

        //새로운 사물이 등장했다면
        if(!exist){
            //만약 id가 존재하지 않을떄 새 id 부여
            if(newStuff.getId().equals("")){
                newStuff.getRandomId(8);   //랜덤하게 8자리 id를 생성하는 메소드
            }
            
            StuffList.stuffList.add(newStuff);
        }
    }

    //모든 사물정보 출력 메소드
    public static void print(){
        for(int i=0; i<StuffList.stuffList.size(); i++){
        	Stuff stuff = StuffList.stuffList.get(i);
            String line = "["+ stuff.getId() + ";" + stuff.getSort() + ";"
            		+ stuff.getName() + ";" + stuff.getStuffTag() + "]";
            System.out.println(line);
        }
    }
}