import java.io.*;
import java.util.*;

/*  토마토 7569
 *  7576 토마토 응용
 */

class Baekjoon7569 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
	static int H;
	static int[][][] arr;
	static int[] res;
	static Queue<int[]> q;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; //앞뒤좌우
	static int[] dh = {-1,0,1}; //아래 위 
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        arr = new int[H][N][M]; // 2,3,5
        q = new LinkedList<int[]>();
        
        for(int k=0; k<H; k++) {
	        for(int i=0; i<N; i++) {
	        	st = new StringTokenizer(bf.readLine());
				for(int j=0; j<M; j++) {
					int f = Integer.parseInt(st.nextToken());
					arr[k][i][j] = f;
					if(f == 1) q.add(new int[] {k, i, j});	
				}
	        }
        }
        
        BFS();
        int max = 0;
        
        for(int h=0; h<H; h++) {
	        for(int i=0; i<N; i++) {
	        	for(int j=0; j<M; j++) {
	        		if(arr[h][i][j]==0) {System.out.println(-1); return;} //방문 안한 곳 있으면 -1
	        		max = max<arr[h][i][j]?arr[h][i][j]:max;
	        	}
	        }
        }
        
        System.out.println(max-1);
    }

	private static void BFS() {
		int[] cur;
		int cx = 0;
		int cy = 0;
		int ch = 0;
		int nx = 0;
		int ny = 0;
		int nh = 0;
		int cnt =0;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			ch = cur[0];
			cx = cur[1];
			cy = cur[2];
			
			for(int i=0; i<3; i++) {
				nh = ch+dh[i];
				if(0<=nh&&nh<H&&arr[nh][cx][cy]==0) { //위 아래
					arr[nh][cx][cy] = arr[ch][cx][cy]+1;
					q.add(new int[] {nh, cx, cy});
					continue;
				}
				for(int j=0; j<4; j++) { //앞뒤좌우
					nx = cx+dx[j];
					ny = cy+dy[j];
					if(nx>=0&&nx<N&&ny>=0&&ny<M&&arr[ch][nx][ny]==0) {
	        			arr[ch][nx][ny] = arr[ch][cx][cy]+1;
	        			
	        			q.add(new int[] {ch, nx, ny});
	        		}
				}
			}
		}
	}
}