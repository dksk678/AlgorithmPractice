import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> nums = new ArrayList<>();

        for(int i=0; i<n; i++) {
            nums.add(i+1);
        }
        
        long facto = getFacto(n);
        int cnt = 0;
        k--;
        
        while(n > 0) {
            facto /= n;
            
            answer[cnt++] = nums.remove((int) (k / facto));
            k %= facto;
            n--;
        }
        
        return answer;
    }
    
    public long getFacto(int n) {
        if(n == 0) return 1;
        return n*getFacto(n-1);
    }
}