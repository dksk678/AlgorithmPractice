import java.io.*;
import java.util.*;

/*  케빈 베이컨의 6단계 법칙 1389
 *  
 *  플로이드-와샬. -> 경유해서 갈 수 있는 곳 찾아서 경로 값 더하기.
 *  ex) 목표:1->5 경로:1->4->5 = 1+1
 *  ex2)목표:2->5 경로:2->3->4->5 = 1+1+1
 * 
 */

class Baekjoon1389 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
//	static int[][] arr;
	static int[][] d;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N][N];
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if (i==j) {d[i][j]=0; continue;}
        		d[i][j]=210000000;
        	}
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
			d[a-1][b-1] = 1;
			d[b-1][a-1] = 1;
        }
       
        fw();
        
        int sum = 0;
        int min = 210000000;
        int ans=0;
        for(int i=0; i<N; i++) {
        	sum=0;
        	for(int j=0; j<N; j++) {
        		sum+=d[i][j];
        	}
        	if(sum<min) {
        		min = sum;
        		ans = i;
        	}
        }
        System.out.println(ans+1);
    }

	private static void fw() {//플로이드-와샬
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
				}
			}
		}
		
	}
}