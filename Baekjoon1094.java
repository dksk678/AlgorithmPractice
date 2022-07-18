import java.io.*;
import java.util.*;
/*  막대기 1094 S5
 * 
 *  64cm 막대기 반으로 쪼개면서 Xcm 막대기로 만들기. 가장 짧은 막대기 반으로
 *  반으로 자르고 하나를 버린다고 가정, 남아있는 막대의 길이 합이 X보다 크거나 같으면 버림.
 *  막대 붙여서 X로 만듬 
 *  몇개의 막대를 써서 만들 수 있나? -> 2의 지수는 무조건 1개로 됨 64,32,16,8,4,2,1
 *  
 *  & 연산 둘다 1이면 1
 */
class Baekjoon1094 { 
	static int N, M;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        StringTokenizer st;
        sb = new StringBuilder();
        
    	int x = Integer.parseInt(br.readLine());
    	int cnt = 0;
    	
    	for(int i=0; i<7; i++) {
    		if((x & (1<<i)) != 0) cnt++; //2의 i승 만큼 값을 x와 비교 -> x와 &연산을 통해 필요한 막대인지 확인.
//    		System.out.println(x&(1<<i));
//    		System.out.println(Integer.toBinaryString(x) + " " +Integer.toBinaryString(1<<i));
    	}
    	
    	System.out.println(cnt);
	}
	
	//######### arr_print #########################
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}