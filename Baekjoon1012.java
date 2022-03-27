import java.io.*;
import java.util.*;

/*  유기농 배추 1012  16:30 ~ 16:44
 *  
 *  방문가능한곳 체크. 새로운 방문할 곳이 생기면 +1
 */
class Baekjoon1012 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int M;
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[][] A; //처음 주어진 배열
	static boolean[][] visited; //방문한배열 체크
	static int[] res; //결과 값 저장된 배열
	static ArrayList<Integer> res2; //결과 값 저장된 배열
	static int cnt;
//	static int cnt2;
	static ArrayList<Integer> res1; //결과 값 저장된 배열
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(bf.readLine());
        sb = new StringBuilder();
        while(T-->0) {
        	st = new StringTokenizer(bf.readLine());
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        A = new int[N][M];
	        visited = new boolean[N][M];
	        cnt = 0;
//	        cnt2 = 0;
	        
	        int k = Integer.parseInt(st.nextToken());
	        while(k-->0) {
	        	st = new StringTokenizer(bf.readLine());
	        	A[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=1;
	        }
	        //dfs
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<M; j++) {
	        		if(A[i][j]>=1 && !visited[i][j]) {
	        			cnt++;
	        			dfs(i, j, visited);
	        		}
	        	}
	        }
	        sb.append(cnt).append("\n");

	        
	      //---------bfs-------------
//	        visited = new boolean[N][M];
//	        
//	        for(int i=0; i<N; i++) {
//	        	for(int j=0; j<M; j++) {
//	        		if(A[i][j]>=1 && !visited[i][j]) {
//	        			cnt2++;
//	        			bfs(i, j, visited);
//	        		}
//	        	}
//	        }
//	        sb.append(cnt2).append("\n");
        }
        

        System.out.println(sb);
//        
//        System.out.println("*******bfs*******");
//        System.out.println(res1.size());
//        Collections.sort(res1);
//        for(int i=0; i<res1.size(); i++) {
//        	System.out.println(res1.get(i));
//        }
    }
	
	private static void dfs(int x, int y, boolean[][] v) {
		if(v[x][y]) return;
		v[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0&&nx<N&&ny>=0&&ny<M&&!v[nx][ny]) {
				if(A[nx][ny]==1) {
					dfs(nx, ny, v);	
				} 
			}
		}
	}
	
//	private static void bfs(int x, int y, boolean[][] v) {
//		Queue<int[]> queue = new LinkedList<>();
//		v[x][y] = true;
//		queue.offer(new int[] {x,y});
////		cnt2++;
//		while(!queue.isEmpty()) {
//			int[] t = queue.poll();
//			for(int i=0; i<4; i++) {
//				int nx = t[0]+dx[i];
//				int ny = t[1]+dy[i];
//				if(nx>=0&&nx<N&&ny>=0&&ny<M&&!v[nx][ny]) {
//					if(A[nx][ny]==1) {
////						cnt2++;
//						v[nx][ny] = true;
//						queue.offer(new int[] {nx,ny});
//					} 
//				}
//			}
//		}
//	}
}