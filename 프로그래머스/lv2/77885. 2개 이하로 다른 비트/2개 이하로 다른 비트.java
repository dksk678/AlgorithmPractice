import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        int size = numbers.length;
        long[] answer = new long[size];
        ArrayList<Long> list = new ArrayList<>();
        
        for(long l: numbers) {
            long plus = 1;
            int cnt = 1;
            for(long m=4; m<l+2; m*=2) {
                if(l%m == m-1) {
                    // System.out.println(m);
                    plus = m;
                }
            }
            if(plus==1) plus*=2;
            // System.out.println(l+" "+plus);
            list.add(l+plus/2);
        }
        
        for(int i=0; i<size; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}