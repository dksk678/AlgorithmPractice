import java.io.*;
import java.util.*;
/*  나이트의 이동 7562 30
 * 
 *  단순 BFS에서 4방향->8방향으로 바꿈
 * 
 */
class Baekjoon7562 { 
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        int T = Integer.parseInt(br.readLine());
        for(int i=1; i<=T; i++) {
        	int L = Integer.parseInt(br.readLine());
        	
        	st = new StringTokenizer(br.readLine());
        	int startX = Integer.parseInt(st.nextToken());
        	int startY = Integer.parseInt(st.nextToken());
        	st = new StringTokenizer(br.readLine());
        	int endX = Integer.parseInt(st.nextToken());
        	int endY = Integer.parseInt(st.nextToken());
        	
        	bw.write(BFS(L, startX, startY, endX, endY)+"\n");

        }

        bw.flush();
        bw.close();
        br.close();
	}
	
	static int BFS(int L, int sx, int sy, int ex, int ey) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][]  v = new boolean[L][L];
		
		q.offer(new int[] {sx, sy, 0});
		
		while(!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.peek()[1];
			int cnt = q.poll()[2];
			
			if(curX==ex&&curY==ey) {
				return cnt;
			}
			
			for(int i=0; i<8; i++) {
				int nx = curX+dx[i];
				int ny = curY+dy[i];
				
				if(nx<0||nx>=L||ny<0||ny>=L||v[nx][ny]) continue;
				
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny, cnt+1});
			}
		}
		
		return 0;
	}
}