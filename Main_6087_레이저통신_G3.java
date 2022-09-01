import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/* 레이저 통신 6087 G3 180~
 * 
 * 다음 향할 곳이 거울 설치를 하는 곳이면 +1 아니면 0인 상태로 비교
 * 우선순위 큐를 이용해 거울 설치가 가장 적은 경로 먼저
 * 
 */
public class Main_6087_레이저통신_G3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		ArrayList<int[]> CtoC = new ArrayList<>();
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				char c =  str.charAt(j);
				map[i][j] = c;
				if(c=='C') {
					CtoC.add(new int[] {i, j});
				}
			}
		}
		System.out.println(dijkstra(H, W, CtoC, map));
	}
	
	private static int dijkstra(int h, int w, ArrayList<int[]> ctoC, char[][] map) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int startr = ctoC.get(0)[0];
		int startc = ctoC.get(0)[1];
		int endr = ctoC.get(1)[0];
		int endc = ctoC.get(1)[1];
		PriorityQueue<laser> q = new PriorityQueue<>();
		q.offer(new laser (startr, startc, -1, getDis(startr, startc, endr, endc), 0));
		
		int[][] visited = new int[h][w];
		for (int i = 0; i < h; i++) {
			Arrays.fill(visited[i], 999999);
		}
		visited[startr][startc] = 0;

		while (!q.isEmpty()) {
			laser cur = q.poll();
			
			if(cur.r==endr && cur.c==endc) {
				return cur.dcnt-1;
			}
			
			for (int i=0; i<4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				int nextdcnt = cur.d==i?cur.dcnt:cur.dcnt+1;
				
				if (nr<0||nr>=h||nc<0||nc>=w||map[nr][nc]=='*'||visited[nr][nc] < nextdcnt) continue; //현재 수가 저장된 값보다 크면 continue
				
				visited[nr][nc] = nextdcnt;
				q.offer(new laser (nr, nc , i, getDis(nr, nc, endr, endc), nextdcnt));
			}
		}

		return -1;
	}
	
	private static int getDis(int startr, int startc, int endr, int endc) {
		return Math.abs(startr-endr) + Math.abs(startc-endc);
	}

	static class laser implements Comparable<laser>{
		int r,c,d,dis,dcnt; //위치, 방향, 거리, 방향 전환 카운트

		public laser(int r, int c, int d, int dis, int dcnt) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.dis = dis;
			this.dcnt = dcnt;
		}

		@Override
		public int compareTo(laser o) {
			int ddcnt = this.dcnt-o.dcnt;
			if(ddcnt==0) {
				return this.dis-o.dis;
			}
			return ddcnt;
		}
	}
	
}
