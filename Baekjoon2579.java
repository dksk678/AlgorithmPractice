import java.io.*;
import java.util.*;

/*	계단 오르기 (DP) *DP 큰 문제를 작은 문제로 쪼개서 풀어 답을 저장, = Memoization
 *               저장된 답으로 다음 풀이   -> 시간이 재귀보다 빠르다 
 *               * 점화식 찾는것이 절반이상. 
 *  Memoization을 사용하기 위한 배열을 만듬 -> sum
 *  1번째 첫 번째 계단 저장   => N=1, 2 일때 예외 처리
 *  2번째 1+2번째 계단 저장
 *  3번째 -> 1+3, 2+3 2가지를 비교 가장 큰 값을 저장
 *  4번째부터는 -> -3번째 값+현재값=(1+3), -2번째 값=(1+2) 비교해서 현재값 더해서 저장. 
 */ 
class Baeckjoon2579{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        int[] sum = new int[N]; //Memoization
        
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(bf.readLine()); 
        }
        sum[0] = arr[0]; 
        //10
        if(N>1) {
        	sum[1] = arr[1]+arr[0];
        	if(N>2) {
        	sum[2] = Math.max(arr[0], arr[1])+arr[2];
	    		if(N>3) {
			        for(int i=3; i<N; i++) {
			        	sum[i] = Math.max(sum[i-3]+arr[i-1], sum[i-2])+arr[i]; //21, 11 비교(1+3, 1+2랑 비교)
			        }                 
	    		}
        	}
        }
        System.out.println(sum[N-1]);
    }
}