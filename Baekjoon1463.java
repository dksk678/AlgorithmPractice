import java.io.*;
import java.util.*;

/*  1로 만들기  1463 17:20
 *  dp
 *  
 *  top-down 형태 => 재귀
 *  
 *  f(n-1)의 카운트
 *  f(n/3)의 카운트
 *  f(n/2)의 카운트 를 비교
 *  작은 값을 배열에 저장.
 *  
 *  최종 값 return
 */

class Baekjoon1463 {
//	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        Arrays.fill(arr, -1);

        System.out.println(req(N));
    }	
	private static int req(int n) {
		if (n==1) return 0;
		if (arr[n]!=-1) return arr[n];
		int tmp = 0;
		int res = req(n-1)+1;
		if(n%3==0) {
			tmp = (req(n/3)+1);
			if(res>tmp) {
				res=tmp;
			}
		}
		if(n%2==0)  {
			tmp = (req(n/2)+1);
			if(res>tmp) {
				res=tmp;
			}
		}
//		System.out.println(res +" " +arr[n-1] + " " +n);
		arr[n]=res;
		return res;
	}
}