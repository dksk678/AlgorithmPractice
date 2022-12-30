/*
숫자 나오기 직전까지 HEAD
숫자부분만 NUMBER
나머지 전부 TAIL

0은 무시 012와 12는 같은 값
HEAD와 NUMBER가 같으면 유지
*/
import java.util.*;

class Solution {
    public class filename {
        String head, number, tail;
        
        public filename(String head, String number, String tail){
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        public String toString() {
            return "head: " + this.head + ", number: " + this.number+", tail: "+ this.tail; 
        }
    }
    
    public ArrayList<String> solution(String[] files) {
        ArrayList<String> answer = new ArrayList<>();
        
        ArrayList<filename> flist = new ArrayList<>(); //file head,num,tail구분한 리스트 저장
        String head = "";
        String number = "";//숫자도 출력 때문에 string으로
        String tail = "";
        int nums = 0; //숫자 시작 위치
        
        for(String file: files){
            //head값 찾기
            head = "";
            for(int i=0, s=file.length(); i<s; i++){
                if(file.charAt(i) >= '0' && file.charAt(i) <= '9') {
                    nums = i;
                    break;
                }
                head += file.charAt(i);
            }
            
            //숫자와 tail값 찾기
            number = "";
            tail = "";
            for(int i=nums, s=file.length(); i<s; i++){
                if(file.charAt(i) < '0' || file.charAt(i) > '9') {
                    tail = file.substring(i, s); //숫자 영역을 벗어나면 나머진 다 tail
                    break;
                }
                number += file.charAt(i);
            }
            
            flist.add(new filename(head, number, tail)); //list에 담기
        }
        
        flist.sort((o1, o2) -> { 
            int hdiff = o1.head.toUpperCase().compareTo(o2.head.toUpperCase()); //대소문자 구분하지 않으므로
            
            if(hdiff==0){ //head가 같으면 숫자 비교
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            }
            
            return hdiff;
        });
        
        for(filename file: flist){
            answer.add(file.head+file.number+file.tail);
        }
        
        return answer;
    }
}