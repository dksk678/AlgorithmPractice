import java.io.*;
import java.util.*;

/*  정수 삼각형  1932
 * 
 *  DP 
 *  전의 최대값(dp[i-1][j-1?j]) 구한 후 더 큰값 dp[i]에 저장.
 */

class Baekjoon1932 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int T;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        
        int[][] arr = new int[T][T];
        int[][] dp = new int[T+1][T+1];
        
        //set
        for(int i=0; i<T; i++) {
	        st = new StringTokenizer(bf.readLine());
	        for(int j=0; j<=i; j++) {
	        arr[i][j] = Integer.parseInt(st.nextToken());
	        }
        }
        int max = 0;
       
        dp[0][0] = arr[0][0];

        for(int i=1; i<T; i++) {
        	for(int j=0; j<=i; j++) {
        		if (j>=1) max = dp[i-1][j-1]<dp[i-1][j]?dp[i-1][j]:dp[i-1][j-1];
        		else max = dp[i-1][j];
        		dp[i][j] = max+arr[i][j];
        	}
        }
        
        //가장 큰 값 출력
        int res = 0;   
        for(int i=0; i<T; i++) {
        	if(res<dp[T-1][i]) res = dp[T-1][i];
        }
        System.out.println(res);
    }
}