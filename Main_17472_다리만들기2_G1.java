import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2_G1 {
	static int[][] map;
	static int N, M;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static ArrayList<ArrayList<Bridge>> list;
	
	static class Bridge implements Comparable<Bridge>{
		int to, dis;

		public Bridge(int to, int dis) {
			super();
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.dis - o.dis;
		}

		@Override
		public String toString() {
			return "Bridge [to=" + to + ", dis=" + dis + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==-1) {
					map[i][j] = cnt;
					findLand(i, j, cnt++); //섬 구역 저장.
				}
			}
		}
		
		list = new ArrayList<>(); //각 섬과의 거리를 저장할 변수
		for (int i = 0; i < cnt-1; i++) {
			list.add(new ArrayList<>());
		} 
		
		getDis(); //list에 거리 저장
		
		System.out.println(prim(cnt-1));
	}
	
	private static int prim(int lc) {
		PriorityQueue<Bridge> pq = new PriorityQueue<>();
		boolean[] v = new boolean[lc];
		pq.offer(new Bridge(0, 0));
		
		int ans = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Bridge cur = pq.poll();
			
			if(v[cur.to]) continue;
			
			v[cur.to] = true;
			ans += cur.dis;
			if(++cnt==lc) break;
			
			for (Bridge bridge : list.get(cur.to)) {
				if(v[bridge.to]) continue;
				
				pq.offer(new Bridge(bridge.to, bridge.dis));
			}
		}

		for (int i = 0; i < lc; i++) {
			if(!v[i]) return -1; //방문 안한 섬있으면 -1
		}
		
		return ans;
	}

	private static void getDis() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]>=1) {
					for (int d = 0; d < 4; d++) {
						findDis(i, j, map[i][j], d); //다른 섬과의 거리 구하기
					}
				}
			}
		}
	}

	private static void findDis(int r, int c, int curl, int d) {
		int nr = r;
		int nc = c;
		int dis = 0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if(chk(nr, nc)) break; //맵을 벗어나거나
			if(map[nr][nc]==curl) break; //현재 땅일 경우 break;
			
			if(map[nr][nc]>=1) {
				if(dis>=2) {
					list.get(curl-1).add(new Bridge(map[nr][nc]-1, dis));
					list.get(map[nr][nc]-1).add(new Bridge(curl-1, dis));
				}
				break;
			}
			
			dis++;
		}
	}

	private static void findLand(int r, int c, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				
				if(chk(nr, nc) || map[nr][nc]!=-1) continue;
				
				map[nr][nc] = cnt;
				q.offer(new int[] {nr, nc});
			}
		}
	}

	private static boolean chk(int nr, int nc) {
		if(nr<0 || nr>=N | nc<0|| nc>=M) return true;
		
		return false;
	}
}
