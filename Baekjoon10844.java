import java.io.*;
import java.util.*;

/*  쉬운 계단 수 10844  16:00
 *  
 *  100자리까지의 수가 들어옴
 *  
 *  앞 뒤 차이가 1인 수가 계단 수임.
 *  1~9 는 모드 계단 수
 *  
 *  앞이 1이면 N-2 일 때  1의 개수+ N-1일때 2의 개수
	앞이 2면 N-1일때 1의 개수+ N+1일때 3의 개수.

	앞이 9면 N-1일때의 8의 개수.
	
	바텀-업
	
 */
class Baekjoon10844 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int K;
	
	static int mod = 1000000000;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        int[][] num = new int[N+1][10+1]; //지금값과 현재값.
        
        for(int i=1; i<=9; i++) {
        	num[1][i] = 1;
        }
        
        for(int i=2; i<=N; i++) { // 1, 1    12,, 10,12
        	for(int j=0; j<=9; j++) {
//    			System.out.println("tmp = " + tmp[0]);
        		if(j==0) num[i][j] = (num[i-1][1])%mod; //0 = 1,2,  
        		
        		else if(j<=8) num[i][j] = (num[i-1][j-1]+num[i-1][j+1])%mod;
        		
        		else num[i][j] = num[i-1][8]%mod;
        	}
        }

        int res = 0;
        for(int i=0; i<10; i++) {
        	res=(res+num[N][i])%mod;
        }
        
        System.out.println(res%mod);
    }
	
}