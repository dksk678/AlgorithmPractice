import java.io.*;
import java.util.*;
/*  가장 긴 바이토닉 부분 수열 11054 150
 *  
 *  DP
 *  첫수부터 증가하거나 감소할때마다 카운트
 *  1.0~i까지 증가수열 최대 길이
 *  2.N-1~i까지 감소수열 최대 길이
 * 
 */
class Baekjoon11054 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static int[] arr;
	static int ans=0;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //set
        N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        arr = new int[N+1];
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int inc[] = new int[N+1];
        int dec[] = new int[N+1];
        for(int i=0; i<N; i++) {
        	//증가
        	for(int j=0; j<i; j++) {
        		//현재 수(1)보다 전에 더 작은 수(0)가 있으면 증가 (0, 1) 
        		if(arr[j]<arr[i]) inc[i] = Math.max(inc[i], inc[j]);
        	}
        	inc[i]++;
        	
        	//감소
        	for(int j=N-1; j>=N-i; j--) {
        		//현재수(1)보다 더 작은 수(0)가 있으면 감소 (1, 0)
        		if(arr[j]<arr[N-1-i]) dec[N-1-i] =  Math.max(dec[N-1-i], dec[j]);
        	}
        	dec[N-1-i]++;
        }
        for(int i: inc) {
        	System.out.print(i+" ");
        }
        System.out.println("");
        for(int i: dec) {
        	System.out.print(i+" ");
        }
        for(int i=0; i<inc.length; i++) {
        	ans = ans<inc[i]+dec[i]?inc[i]+dec[i]-1:ans;
        } 
        System.out.println(ans);
	}	
}