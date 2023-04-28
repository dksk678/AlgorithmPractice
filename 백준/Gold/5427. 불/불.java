import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 불 동서남북으로 퍼져나감. 벽에는 X
 * 상근이는 동서남북 이동 1초 걸림. 불이 붙으려는 칸 이동 불가 => 불을 먼저 붙힌 후 이동
 * 벽에 도달한 시간 +1
 */
public class Main {
	private static int N;
	private static int M;
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N][M];
			int[] start = new int[2];
			
			Queue<int[]> fireQ = new ArrayDeque<>();
			
			int answer = 0;
			
			for (int i = 0; i < N; i++) {
				String ms = br.readLine();
				for (int j = 0; j < M; j++) {
					char m = ms.charAt(j);
					
					if(m == '@') {
						start[0] = i;
						start[1] = j;
						
						if(isFinish(i, j)) answer = 1; 
					} else if (m == '*') {
						fireQ.offer(new int[] {i, j});
						map[i][j] = m;
					} else {
						map[i][j] = m;
					}
				}
			}
			
			if(answer == 0) {
				answer = escapeMap(start[0], start[1], map, fireQ);				
			}
			
			sb.append(answer==0?"IMPOSSIBLE":answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int escapeMap(int sr, int sc, char[][] map, Queue<int[]> fireQ) {
		Queue<int[]> userQ = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		
		userQ.offer(new int[] {sr, sc});
		v[sr][sc] = true;
		
		int cnt = 1;
		
		while(!userQ.isEmpty()) {
			int fsize = fireQ.size();
			
			for (int i = 0; i < fsize; i++) { //불 먼저 번짐
				int[] cur = fireQ.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = cur[0] + dr[j];
					int nc = cur[1] + dc[j];
					
					if(isNotRange(nr, nc) || map[nr][nc] == '#' || map[nr][nc] == '*') continue; 
					
					map[nr][nc] = '*';
					fireQ.offer(new int[] {nr, nc});
				}
			};
			
			int size = userQ.size();
			
			for (int i = 0; i < size; i++) {
				int[] cur = userQ.poll();
				
				for (int j = 0; j < 4; j++) {
					int nr = cur[0] + dr[j];
					int nc = cur[1] + dc[j];
					
					if(isNotRange(nr, nc) || v[nr][nc] || map[nr][nc] != '.') continue;
					
					if(isFinish(nr, nc)) {
						return cnt+1;
					}
			
					v[nr][nc] = true;
					userQ.offer(new int[] { nr, nc });
				}
			}
			
			cnt++;
		}
		
		return 0;
	}

	private static boolean isFinish(int nr, int nc) {
		return nr == 0 || nr == N-1 || nc == 0 || nc == M-1;
	}

	private static boolean isNotRange(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}
}