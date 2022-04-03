import java.io.*;
import java.util.*;

/*  연속합 1912
 *  
 *  0~i까지 최대값을 dp에 저장.
 *  dp값을 비교해 가면서 max값만 저장. 
 * 
 *  
	바텀-업
 */
class Baekjoon1912 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int K;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        int[] num = new int[N]; //입력 값
        int[] sum = new int[N]; //dp
        
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        	sum[i] = num[i];
        }
        
        int max = num[0];

        for(int i=1; i<N; i++) {
        	sum[i] = (sum[i]<sum[i-1]+num[i])?sum[i-1]+num[i]:sum[i]; //5<4+5
        	max = (max<sum[i])?sum[i]:max;
        }
        
        System.out.println(max);
    }
	
}