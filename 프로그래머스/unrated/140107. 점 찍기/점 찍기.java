import java.util.*;
// 원점과의 거리가 d를 넘는 위치에서는 점을 찍지 않음
// 원점과의 거리는 x + y
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        int maxY = d - (d%k);
        int y = maxY;
        
        // System.out.println(maxY);
        int cnt = maxY / k + 1;
        // if(cnt == 0) cnt++;
        
        // System.out.println(cnt);
        answer += cnt--;
        answer += cnt;
        for(int i = k; i <= y; i += k) {
            long ii = (long)i*i;
            long yy = (long)y*y;
            long dis = ii+yy;
            long dd = (long)d*d;
            // System.out.println("-- " + dd+" "+dis+" "+i+" "+y);
            if(dd < dis) {
                long diff = (dis-dd);
                cnt--;
                y -= k;
            }
            
            if(cnt==0) break;
            
            answer += cnt;
            answer += --cnt;
            // System.out.println(cnt+" "+i);
        }
        
        
        return answer;
    }
}