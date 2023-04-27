import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 육지 L, 바다 W
 * 상하좌우 이웃한 육지만 이동 가능 -> L만 탐색 
 * 보물은 서로 간에 최단 거리 이동
 * 모든 L에서 최장값을 구하면 최단 거리가 된다.
 * BFS로 탐색을 진행하면 항상 최선의 값을 구하기 때문에
 * 
 * 조금더 빨리 가능한 법 -> 각 땅 마다 BFS를 한번씩 하고 그때의 최장 거리 좌표를 저장.
 * 
 */
public class Main {
	public static int max;
	public static int[] dr = {0, 1, 0, -1};
	public static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'W') continue;
				
				BFS(i, j, N, M, map);
			}
		}
		
		System.out.println(max);
	}

	private static void BFS(int sr, int sc, int n, int m, char[][] map) {
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[n][m];
		
		q.offer(new int[] {sr, sc});
		v[sr][sc] = true;
		
		int len = -1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			len++;
			max = max < len ? len : max;
			
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = cur[0] + dr[j];
					int nc = cur[1] + dc[j];
					
					if(nr < 0 || nr >= n || nc < 0|| nc >= m 
							|| map[nr][nc] == 'W' || v[nr][nc]) continue;
					
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}