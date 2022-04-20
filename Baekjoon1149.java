import java.io.*;
import java.util.*;

/*  RGB 거리 1149
 *  빨강, 초록, 파랑 중 하나의 색으로 칠애햐 함 -> 비용의 최솟값 구하기 dp
 *  
 *  1. 1번 집의 색은 2번집의 색과 같지 않아야함.
 *  2. N번 집의 색은 N-1번 집의 색과 같지 않아야함.
 *  3. i(2<=i<=N-1)번 집의 색은 i-1번, i+1번집의 색과 같지 않아야함.
 *  
 *  집의수 N
 *  각 집을 R, G, B순으로 칠하는 비용
 */

class Baekjoon1149 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int T;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        
        int[][] rgb = new int[T][3];
        int[][] dp = new int[1001][3];
        
        //set
        for(int i=0; i<T; i++) {
	        st = new StringTokenizer(bf.readLine());
	        int R = Integer.parseInt(st.nextToken());
	        int G = Integer.parseInt(st.nextToken());
	        int B = Integer.parseInt(st.nextToken());
	        
	        rgb[i][0] = R;
	        rgb[i][1] = G;
	        rgb[i][2] = B;
        }
        
        int min = 0;
        
        dp[0][0] = rgb[0][0];
        dp[0][1] = rgb[0][1];
        dp[0][2] = rgb[0][2];
        
        //마지막 rgb 까지 계산
        for(int i=1; i<T; i++) {
			min = dp[i-1][1]<dp[i-1][2]?dp[i-1][1]:dp[i-1][2]; //현재 r값 구하기
			dp[i][0] = min+rgb[i][0];
				    	 
			min = dp[i-1][0]<dp[i-1][2]?dp[i-1][0]:dp[i-1][2]; //g값 구하기
			dp[i][1] = min+rgb[i][1];
			 
			min = dp[i-1][0]<dp[i-1][1]?dp[i-1][0]:dp[i-1][1]; //b값 구하기
			dp[i][2] = min+rgb[i][2];
        }
        
        //가장 작은값 출력
        int res = 999999999;   
        for(int i=0; i<3; i++) {
        	if(res>dp[T-1][i]) res = dp[T-1][i];
        }
        System.out.println(res);
    }
}