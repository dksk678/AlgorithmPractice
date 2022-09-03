import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_젤다_G4 {
	static int ans;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = 0;
		int N = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = BFS(N);
			sb.append("Problem ").append(++tc).append(": ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	private static int BFS(int N) {
		PriorityQueue<loc> pq = new PriorityQueue<>();
		pq.offer(new loc(0, 0, map[0][0]));

		boolean[][] v = new boolean[N][N];

		while (!pq.isEmpty()) {
			loc cur = pq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || v[nr][nc]) continue;
				
				if (nr == N - 1 && nc == N - 1) return cur.rup + map[nr][nc];

				v[nr][nc] = true;
				pq.offer(new loc(nr, nc, map[nr][nc] + cur.rup));
			}
		}

		return 0;
	}

	static class loc implements Comparable<loc> {
		int r, c, rup;

		public loc(int r, int c, int rupee) {
			super();
			this.r = r;
			this.c = c;
			this.rup = rupee;
		}

		@Override
		public int compareTo(loc o) {
			return this.rup - o.rup;
		}
	}

}