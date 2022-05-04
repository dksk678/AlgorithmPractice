import java.io.*;
import java.util.*;

/*  플로이드  11404  30
 *  
 *  플로이드-와샬
 *  기본문제
 */
class Baekjoon11404 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static int[][] arr;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        //set
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
        	Arrays.fill(arr[i], 210000000);
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	if(arr[a-1][b-1]>c) arr[a-1][b-1] = c;
        }
        
        fw();
        //print
        for(int[] i:arr) {
        	for(int j:i) {
        		if(j==210000000) sb.append(0+" ");
        		else sb.append(j+" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }	
	private static void fw() {
		for(int k=0; k<N; k++) { //거쳐가는 노드
			for(int i=0; i<N; i++) { //출발 노드
				if (k==i) continue;
				for(int j=0; j<N; j++) { //도착 노드
					if (i==j||k==j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}
		}
	}
}