import java.io.*;
import java.util.*;

/*  경로 찾기 11403
 *  
 *  플로이드-와샬. -> 거쳐가는 노드 까지 계산 갈수 있는 곳 +1
 * 
 */

class Baekjoon11403 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
	static int cnt;
	static int[][] arr;
	static int[][] d;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        
        arr = new int[N][N];
        d = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				arr[i][j] = n;
				d[i][j] = n;
			}
        }
       
        fw();
       
        for(int[] i: d) {
        	for(int j: i) {
        		sb.append(j).append(" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }

	private static void fw() {
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(d[i][k]+d[k][j]==2) {  //01  02
						d[i][j] = 1;
					}
				}
			}
		}
		
	}
}