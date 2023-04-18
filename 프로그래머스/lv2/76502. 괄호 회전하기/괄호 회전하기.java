import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int len = s.length();
        
        if(len % 2 == 1) {
            
        }
        for(int i=0; i<len; i++) {
            // System.out.println(s);
            if(isOk(s, len)) {
                ++answer;
            }
            
            s = rotate(s);
        }
        
        return answer;
    }
    
    public boolean isOk(String s, int len) {
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        set.add('}');
        set.add(')');
        set.add(']');
        
        for(int i=0; i<len; i++) {
            if(stack.isEmpty()) {
                stack.add(s.charAt(i));
            } else {
                char c = stack.peek();
                // System.out.println(c+" "+s.charAt(i));
                char ci = s.charAt(i);
                
                if(c == '(') {
                    if(ci == ')') {
                        stack.pop();
                    } else if (!set.contains(ci)){
                        stack.add(ci);
                    } else {
                        return false;
                    }
                } else if (c == '[') {
                    if(s.charAt(i) == ']') {
                        stack.pop();
                    } else if (!set.contains(ci)) {
                        stack.add(ci);
                    } else {
                        return false;
                    }
                } else if (c == '{') {
                    if(s.charAt(i) == '}') {
                        stack.pop();
                    } else if (!set.contains(ci)) {
                        stack.add(ci);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public String rotate(String s) {
        String start = s.substring(0,1);
        
        return s.substring(1)+start;
    }
}