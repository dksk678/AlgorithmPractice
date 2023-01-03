import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        
//         String[] stringNumber = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        
//         for(int i=0; i<10; i++){
//             if(s.contains(stringNumber[i])){
//                 s = s.replaceAll(stringNumber[i], i+"");
//             }
//         }
        
        
        for(String k: map.keySet()){ 
            if(s.contains(k)){ //문자열에 해당 키값이 포함되어있으면
                s = s.replaceAll(k, map.get(k).toString()); //숫자로 replace
            }
        }
        
        answer = Integer.parseInt(s);
        return answer;
    }
}
