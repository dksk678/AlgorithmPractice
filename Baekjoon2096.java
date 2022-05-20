import java.io.*;
import java.util.*;
/*  내려가기 2096 60
 *  
 *  N 0~9 3개 숫자.
 *  숫자 3개중 하나 골라서 시작. 다음줄로 내려감(바로 아래로 가거나, 아래의 수와 붙어있는 수)
 *  (슬라이싱 윈도우 -> 메모리 절약)
 *  //반례. min크기를 너무 작게 잡음
 *  
 *  -> N한번 min, max 결과 Math.max로
 */
class Baekjoon2096 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        N = Integer.parseInt(bf.readLine());
        arr = new int[3];
        int[] mindp = new int[3];
        int[] maxdp = new int[3];
        int f, s, t;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<3; j++) {
        		int a = Integer.parseInt(st.nextToken());
        		arr[j] = a;
        	}
        	f = Math.max(maxdp[0], maxdp[1])+arr[0];
			s = Math.max(maxdp[1], Math.max(maxdp[0], maxdp[2]))+arr[1];
			t = Math.max(maxdp[2], maxdp[1])+arr[2];
			maxdp[0]=f;
			maxdp[1]=s;
			maxdp[2]=t;
			
			f = Math.min(mindp[0], mindp[1])+arr[0];
			s = Math.min(mindp[1], Math.min(mindp[0], mindp[2]))+arr[1];
			t = Math.min(mindp[2], mindp[1])+arr[2];
			mindp[0]=f;
			mindp[1]=s;
			mindp[2]=t;
        }

        int min = 1000001;
        int max = 0;
        
        min = Math.min(mindp[0], Math.min(mindp[1], mindp[2]));
        max = Math.max(maxdp[0], Math.max(maxdp[1], maxdp[2]));

        System.out.println(max+" "+min);
	}
}