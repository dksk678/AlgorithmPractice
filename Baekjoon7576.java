import java.io.*;
import java.util.*;

/*  토마토  7576
 * 	
 * 	주변에  +1씩 해야되기때문에 BFS를 사용.
 *  
 *  첫 토마토있는 곳(1) queue에 넣기.
 *  그 주변에 안익은 토마토(0)가 있으면 -> 토마토가 익은 일 + 1일
 *  
 */

class Baekjoon7576 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int[][] arr;
	
	static int N;
	static int M;
	static int cnt;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static Queue<int[]> q = new LinkedList<int[]>();;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[M][N];
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<N; j++) {
        		int t = Integer.parseInt(st.nextToken());
            	arr[i][j] = t;
            	if(t==1) q.add(new int[] {i, j});
            }
        }
        
        BFS();
        
        int max = 0;
        
        for(int i=0; i<M; i++) {
        	for(int j=0; j<N; j++) {
        		if(arr[i][j]==0) {System.out.println(-1); return;} //방문 안한 곳 있으면 -1
        		max = max<arr[i][j]?arr[i][j]:max;
        	}
        }
        System.out.println(max-1);
    }
	
//	private static void DFS(int i) {
//		if (v[i]) {return; }
//		v[i] = true;
//		for(int l: arr.get(i)) { //l
//			if(v[l]==true) continue;
//			DFS(l);
//		}
//	}
	
	private static void BFS() {
		int nx;
		int ny;
		while(!q.isEmpty()) {
			int[] q2 = q.poll();
			int qx = q2[0];
			int qy = q2[1];
        	for(int k=0; k<4; k++) {
        		nx = qx + dx[k];
        		ny = qy + dy[k];
        		if(nx>=0&&nx<M&&ny>=0&&ny<N&&arr[nx][ny]==0) {
        			arr[nx][ny] = arr[qx][qy]+1;
        			q.add(new int[] {nx, ny});
        		}
	        }
		}
	}
}