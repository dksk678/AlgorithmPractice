import java.io.*;
import java.util.*;

/*  구간 합 구하기 5  11660  30
 *  
 *  sum[2~y1] = dp[y1]-dp[y2]
 */
class Baekjoon11660 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static int[][] arr; 
	static int[][] dp;
	static int ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr= new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=1; j<=N; j++) {
        		arr[i][j] += arr[i][j-1]+Integer.parseInt(st.nextToken());
            }
        }
        
        int x1,y1,x2,y2;
        for(int t=0; t<M; t++) {
        	ans = 0;
        	st = new StringTokenizer(bf.readLine());
        	x1 = Integer.parseInt(st.nextToken());
        	y1 = Integer.parseInt(st.nextToken());
        	x2 = Integer.parseInt(st.nextToken());
        	y2 = Integer.parseInt(st.nextToken());
	        for(int i=x1; i<=x2; i++) {
	        	ans += arr[i][y2]-arr[i][y1-1];
	        }
	        sb.append(ans).append("\n");
        }
        System.out.println(sb);
	}
}