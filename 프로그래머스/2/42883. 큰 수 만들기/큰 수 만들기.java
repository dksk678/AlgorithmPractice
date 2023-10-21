import java.util.*;
/**
* n 번째 자리에 n+k 까지의 수와 비교
* 앞 숫자중에 더 작은 수 있으면 삭제, k--
* 위 과정 반복
* 
*/

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
            
        int numLen = number.length();
        int ansLen = numLen - k;
        int lastIdx = 0;
        
        for(int i=0; i<numLen; i++) {
            int cur = number.charAt(i)-'0';
            
            //현재 삭제가능한 수 만큼만 반복
            while(!stack.empty() && stack.peek() < cur && k > 0) { //앞 숫자중에 더 작은 수 있으면 삭제
                stack.pop();
                k--;
            }
            
            stack.push(cur);
        }
        
        //stack에 남아있는 수 더하기
        for(int i=0; i<ansLen; i++) {
            sb.append(stack.get(i));
        }
        
        return sb.toString();
    }
}