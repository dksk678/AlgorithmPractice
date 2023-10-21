import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int[] hr = {-2, -1, 1, 2, 2, 1, -1, -2};
	private static int[] hc = {1, 2, 2, 1, -1, -2, -2, -1};
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	private static int[][] map; 
	
	private static int k, w, h;
	
	private static class Monkey {
		int r, c, h, d; //좌표, 말사용횟수, 거리

		public Monkey(int r, int c, int h, int d) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		int ans = simulation();
		
		System.out.println(ans);
	}

	private static int simulation() {
		return moveMonkey();
	}
	
	private static int moveMonkey() {
		Queue<Monkey> q = new ArrayDeque<>();
		boolean[][][] v = new boolean[k+1][h][w]; //말을 사용한 카운트 별로 방문처리 달리 해줘야함.
		
		q.offer(new Monkey(0, 0, 0, 0));
		for (int i = 0; i < k+1; i++) {
			v[i][0][0] = true;			
		}
		
		while(!q.isEmpty()) {
			Monkey monkey = q.poll();
			
			if(monkey.r==h-1 && monkey.c==w-1) {
				return monkey.d;
			}
			
			//원숭이 이동
			for (int i = 0; i < 4; i++) {
				int nr = monkey.r + dr[i];
				int nc = monkey.c + dc[i];
				
				if(!isRange(nr, nc) || map[nr][nc] == 1 || v[monkey.h][nr][nc]) continue;
				
				v[monkey.h][nr][nc] = true;
				q.offer(new Monkey(nr, nc, monkey.h, monkey.d+1));
			}
			
			//말로 이동
			if(monkey.h < k) {
				for (int i = 0; i < 8; i++) {
					int nr = monkey.r + hr[i];
					int nc = monkey.c + hc[i];
				
					if(!isRange(nr, nc) || map[nr][nc] == 1 || v[monkey.h+1][nr][nc]) continue;
					
					v[monkey.h+1][nr][nc] = true;
					q.offer(new Monkey(nr, nc, monkey.h+1, monkey.d+1));
				}
			}
		}
		
		return -1;
	}

	private static void init() throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		k = toInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		w = toInt(st.nextToken());
		h = toInt(st.nextToken());
		
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = toInt(st.nextToken());
			}
		}
	}

	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
	
	private static boolean isRange(int nr, int nc) {
		return 0<=nr && nr<h && 0<=nc && nc<w;
	}

}