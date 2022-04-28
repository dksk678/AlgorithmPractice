import java.io.*;
import java.util.*;
/*  스티커 9465 120
 * 
 *  스티커 2n개 구매, 2행 n열로 배치
 *  스티커 때면 왼쪽,오른쪽,위,아래 사용 불가 -> 대각선 값 구하면 됨 
 *  스티커를 땔대  최대 점수?  dp
 *  현재 점수 + 대각선 값의 점수
 */
class Baekjoon9465 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int T;
	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //set
        T = Integer.parseInt(bf.readLine());
        while(T-->0) {
	        N = Integer.parseInt(bf.readLine());
	        arr = new int[2][N+1];
	        for(int i=0; i<2; i++) {
	        	st = new StringTokenizer(bf.readLine());
	        	for(int j=0; j<N; j++) {
	        		arr[i][j] = Integer.parseInt(st.nextToken());
	        	}
	        }
	        dp = new int[2][N+1];
	        dp[0][1] = arr[0][0];
	        dp[1][1] = arr[1][0];
	        for(int i=2; i<=N; i++) {
    			dp[0][i] = Math.max(dp[1][i-2], dp[1][i-1]) + arr[0][i-1];
    			dp[1][i] = Math.max(dp[0][i-2], dp[0][i-1]) + arr[1][i-1];
	        }
	        sb.append(Math.max(dp[0][N], dp[1][N])+"\n");
        }
        System.out.println(sb);
	}
}