import java.io.*;
import java.util.*;
// 피보나치 dp 2748 
// 최종값 long 사이즈
class Baekjoon2748 { 
	static int[][] sudoku;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=N; i++) {
        	dp[i] = dp[i-2]+dp[i-1];
        }
        

        bw.write(dp[N]+"");
        bw.flush();
        bw.close();
        br.close();
	}
}