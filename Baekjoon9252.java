import java.io.*;
import java.util.*;
/*  LCS 2  9252 60
 * 	
 * 	최장 공통 부분 수열 
 *  2차원 dp
 */
class Baekjoon9252 { 
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        StringTokenizer st;
        sb = new StringBuilder();
        
        String A = br.readLine();
        String B = br.readLine();
        int al = A.length();
        int bl = B.length();
    	int[][] dp = new int[al+1][bl+1];
    	
    	for(int i=1; i<=al; i++) {
    		for(int j=1; j<=bl; j++) {
        		if(A.charAt(i-1)==B.charAt(j-1)) {
        			dp[i][j] = dp[i-1][j-1]+1;
        			
        		}else {
        			dp[i][j] = dp[i-1][j]<dp[i][j-1]?dp[i][j-1]:dp[i-1][j];
        		}
        	}
    	}

    	int len = dp[al][bl];
    	System.out.println(len);
    	//역 추적
    	char[] ans = new char[len];
    	while(len>=1) {
    		if(A.charAt(al-1)==B.charAt(bl-1)) {
    			ans[--len] = B.charAt(bl-1);//같을 때의 값 저장
    			al--;
    			bl--;
    		} else {
    			if(dp[al-1][bl]<dp[al][bl-1]) bl--; //다르면 길이가 더 길었던 곳으로
    			else al--;
    		}
    	}
    	System.out.println(String.valueOf(ans));
	}
	
	
//############### print ##############	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
	
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.println(i+" ");
		}
		System.out.println("-----------");
	}
}