import java.io.*;
import java.util.*;
/*  LCS 9251 50
 *  DP
 *  
 *  첫번째 줄[i] 검색 -> 두번째 줄[j] 같은 값있는 지 체크
 *  같은값 이면 DP[i]에 저장. 아니면 첫문장에서 이전값[i-1]?현재문장에서 이전값[j-1] 비교 
 */
class Baekjoon9251 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int T;
//	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //set
        String[] a = bf.readLine().split("");
        String[] b = bf.readLine().split("");

        dp = new int[a.length+1][b.length+1];

        for(int i=1; i<=a.length; i++) {
        	for(int j=1; j<=b.length; j++) {
        		if(a[i-1].equals(b[j-1])) {
        			dp[i][j] = dp[i-1][j-1]+1;
        		} else {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //열이 첫문자 행이 두번째 문자
        		}
        	}
        }
        System.out.println(dp[a.length][b.length]);
	}
}