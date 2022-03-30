import java.io.*;
import java.util.*;

/*  2xn 타일링 11729  16:10~
 *  
 *  1일땐 1
 *  2일땐 2
 *  3일땐 3
 *  4일땐 5 -> 0 1 2 3 5 8 13 ->피보나치
 *  
 *  dp사용 바텀-업
 */
class Main {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
    
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());

        int[] f = new int[N+2];
        f[1] = 1;
        f[2] = 2;
        //dfs
        for(int i=3; i<N+1; i++) {
        	f[i] = f[i-1]+f[i-2]; 
        	f[i] %= 10007; //다 더하면 오버플로우 발생. -> 더한값을 바로바로 나머지 구하기
        }

        System.out.println(f[N]); //4319
    }
}