import java.util.*;
import java.io.*;

/*
    정수를 비교해서 이어붙였을 때 가장 높은 경우
    -> 문자열로 비교해서 정렬
    -> comparator를 사용해서 합쳐진 문자열을 비교해서 정렬하기
    
    첫 숫자가 0인 경우는 0만 출력되게
*/

class Solution {
    public class StringDescendingComparator implements Comparator<String>{
        
        @Override
        public int compare(String n1, String n2){
            String sum1 = n1 + n2;
            String sum2 = n2 + n1;
            
            return sum2.compareTo(sum1);
        }
    }
    
    public String solution(int[] numbers) {
        String answer = "";
        int arrLen = numbers.length;
        
        String[] strNumbers = new String[arrLen]; //비교를 위해 문자열배열 생성
        
        for(int i=0; i<arrLen; i++) {
            strNumbers[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(strNumbers, new StringDescendingComparator());
        
        if(strNumbers[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String strNumber : strNumbers) {
            sb.append(strNumber);
        }
        
        return sb.toString();
    }
}