import java.util.*;

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        
        long min = Math.min(w, h);
        long max = Math.max(w, h);
        
        long gcd = getGcd(max, min);
        
        return max*min - (max+min-gcd);
    }
    
    public long getGcd(long a, long b) {
        if(b==0) return a;
            
        return getGcd(b, a%b);
    }
}