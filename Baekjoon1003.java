import java.io.*;
import java.util.*;

/*  피보나치 함수 1003  18:30~18:57
 *  
 *  2일때 0의 개수 = 0일때 0의 개수+ 1일때 0의 개수 -> 2[0] = 0[0]+1[0]
	2일때 1의 개수 = 0일때 1의 개수+ 1일때 1의 개수 -> 2[1] = 0[1]+1[1]
	3일때= 2의 0,1개수와 1의 0,1개수를 구하면됨. 이유는 2의 개수가 0일때개수도 포함된 값이기때문.
 *  N까지 반복.
 */
class Baekjoon1003 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(bf.readLine());
        sb = new StringBuilder();
        while(T-->0) {
	        N = Integer.parseInt(bf.readLine());
	        int[] f = new int[N+2];
	        int[][] arr = new int[N+2][2];
	        arr[0][0] = 1;
	        arr[0][1] = 0;
	        arr[1][0] = 0;
	        arr[1][1] = 1;
	        //dfs
	        for(int i=2; i<N+1; i++) {
//	        	
	        	arr[i][0] = arr[i-1][0]+arr[i-2][0];//1+0, arr[2] = 1, 1
	        	arr[i][1] = arr[i-1][1]+arr[i-2][1];//0+1
	        }
	        sb.append(arr[N][0]).append(" ").append(arr[N][1]).append("\n");
        }
        

        System.out.println(sb);
    }
}