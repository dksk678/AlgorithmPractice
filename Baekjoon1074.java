import java.io.*;
import java.util.*;

/*  Z 1074
 * 
 *  2^N의 배열이 주어짐.
 *  
 *  r, c값을 줄여 나가면서 2by2 배열까지 만듬.
 *  
 *  줄여 나갈때마다 4의 배수 만큼 증가. 제곱형태이기 때문.
 *  
 *  2by2까지 되면 r,c의 위치에 해당하는 값 구해서 더하기. (0 1 2 3)
 *  
 */

class Baekjoon1074 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	
	static int r;
	static int c;
	static int N;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        System.out.println(req(r, c, N));
    }
	
	public static int req(int x, int y, int n) {
		if (n == 0) return 0;

		return 2*(x%2)+y%2+4*req(x/2, y/2, n-1);
	}
}