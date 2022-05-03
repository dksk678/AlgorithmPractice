import java.io.*;
import java.util.*;

/*  곱셈  1629  18:20
 *  
 *  A를 B번 곱한 수를 C로 나눈 값
 *  
 *  1. n이 짝수: A^B = A^(B/2)*A^(B/2)
 *  2. n이 홀수: A^B = A^(B-1/2)*A^(B-1/2)*A
 *  반복.
 *  
 *  구해진 A^B-> %C = ans
 */
class Baekjoon1629 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static int[][] arr;
	static int[][] arr2;
	static int A;
	static int B;
	static int C;
	static int ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(req(B));
    }	
	private static long req(int b) { // 6, 6
		if (b==0) return 1;

		long x = req(b/2); //x = A%(B-1)
		System.out.println(x*x + " " + x*x*A);
		if(b%2==0) return x*x%C;
		
		return x*x%C*A%C;
	}
}