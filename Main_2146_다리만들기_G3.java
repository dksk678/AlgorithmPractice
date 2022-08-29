import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리만들기_G3 {
	static int[][] map;
	static int N;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==-1) {
					map[i][j] = cnt;
					findLand(i, j, cnt++); //섬 구역 저장.
				}
			}
		}
		
		ans = 123456789;
		getDis(); //list에 거리 저장
		
		System.out.println(ans);
	}
	
	private static void getDis() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]>=1) {
					findDis(i, j, map[i][j]); //다른 섬과의 거리 구하기
				}
			}
		}
	}

	private static void findDis(int r, int c, int curl) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] v = new boolean[N][N];
		v[r][c] = true;
		q.offer(new int[] {r, c});
		int dis = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int s = 0; s < size; s++) {
				r = q.peek()[0];
				c = q.poll()[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r+dr[i];
					int nc = c+dc[i];
					
					if(chk(nr, nc) || map[nr][nc]==curl || v[nr][nc]) continue;
					
					if(map[nr][nc]>=1) {
						ans = ans>dis?dis:ans;
						return;
					}
					
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
	 			}
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
		if(nr<0 || nr>=N | nc<0|| nc>=N) return true;
		
		return false;
	}
}
