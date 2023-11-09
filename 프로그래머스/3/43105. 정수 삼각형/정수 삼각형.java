import java.util.*;

/*
*   EX) 7 3 8 7 5 순으로 가는 것이 최대값임 

*   DP 배열은 중복값을 제외한 최대 N개의 크기만 있으면 됨.
    DP 점화식 -> 맨끝 좌우를 제외한 중앙은 가장 큰 값만 저장
    EX)
    7 10 18
      15 16(11)
         15
*/

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        
        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];
        
        for(int h=0; h<len-1; h++) {
            for(int i=0; i<=h; i++) {
                dp[i][h+1] = Math.max(dp[i][h+1], dp[i][h] + triangle[h+1][i]); //이전에 저장된 값이 더 큰 경우
                dp[i+1][h+1] = dp[i][h] + triangle[h+1][i+1]; //끝 부분은 비교군이 없음
            }
        }
        
        for(int i=0; i<len; i++) {
            //System.out.println(Arrays.toString(dp[i]));
            answer = Math.max(answer, dp[i][len-1]);
        }
        
        return answer;
    }
}