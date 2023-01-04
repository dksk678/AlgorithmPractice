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
        
        for(String k: map.keySet()){ //
            if(s.contains(k)){
                s = s.replace(k, map.get(k).toString());
            }
        }
        
        System.out.println(s);
        
        answer = Integer.parseInt(s);
        return answer;
    }
}