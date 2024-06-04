import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.PrintWriter;

// File과 관련된 작업을 하는 메소드(파일 읽고 쓰기, 필드 별로 나누고 파싱 등)
public class FileManager {

    //멤버변수
    private File file;
    private ArrayList<String> infoLines = new ArrayList<String>();   //사진 데이터를 한 줄씩 저장, 사진은 최대 100개까지

    //생성자
    FileManager(String filename){
        file = new File(filename);
    }

    //메소드
    //파일에서 한 줄씩 읽어서 infoLines 배열에 저장
    void readFile(){  

        // System.out.println("파일에서 한줄씩 읽어오기 테스트");
        try{
            Scanner input = new Scanner(this.file);

            while(input.hasNext()){
                String line = input.nextLine();

                if(line.trim().isEmpty()){
                    continue;
                }
                else if(line.trim().startsWith("//")){
                    continue;
                }
                else{
                    this.setInfoLines(line);
                    // System.out.println(infoLines[i-1]);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    //필드별로 나누고 parsing
    String[] parseField(String field){   
        
        String[] fields = field.split("<|>");
        List<String> filtered_fields = Arrays.stream(fields)
        .map(String::trim)          // 앞 뒤 공백 제거
        .filter(s -> !s.isEmpty())  // 배열의 값이 공백인 요소 제거
        .collect(Collectors.toList());

        String[] new_fields = filtered_fields.toArray(new String[0]);

        return new_fields;
    }
    
    // getter, setter
    public File getFile() {
        return file;
    }

    public ArrayList<String> getInfoLines() {
        return infoLines;
    }
    
    public void setFile(File file) {
        this.file = file;
    }

    public void setInfoLines(String infoLines) {
       this.infoLines.add(infoLines);
    }

}

