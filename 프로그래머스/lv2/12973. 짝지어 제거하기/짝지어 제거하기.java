import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        
        Stack<Character> stack = new Stack<>();
        stack.add(s.charAt(0));
        int size = s.length();
        
        for(int i=1; i<size; i++) {
            if(stack.isEmpty()) {
                stack.add(s.charAt(i));
                continue;   
            }
            if(stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.add(s.charAt(i));
            }
        }
        
        // System.out.println(stack.toString());


        return stack.size() > 0 ? 0 : 1;
    }
}