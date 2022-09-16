import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_백조의호수_P5 {
	static Queue<loc> water, L, nextL;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map  = new char[R][C];
		
		water = new ArrayDeque<>();
		L = new ArrayDeque<>();
		nextL = new ArrayDeque<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				if (ch=='L') {
					if(L.isEmpty()) {
						L.offer(new loc(i, j));
					}
					water.offer(new loc(i, j));
				} else if (ch=='.') {
					water.offer(new loc(i, j));
				}
			}
		}
		
		System.out.println(BFS(R, C, map));
	}

	private static int BFS(int R, int C, char[][] map) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		boolean[][] v = new boolean[R][C];
		v[L.peek().r][L.peek().c] = true;
		int time = 0;
		
		while(true) {
			
			//meet swan
			while(!L.isEmpty()) {
				loc lcur = L.poll();
				for (int j = 0; j < 4; j++) {
					int nr = lcur.r + dr[j];
					int nc = lcur.c + dc[j];
					
					if(nr<0||nr>=R||nc<0||nc>=C||v[nr][nc]) continue;
					if(map[nr][nc]=='L') return time;
					
					v[nr][nc] = true;
					
					if(map[nr][nc]=='X') {
						nextL.offer(new loc(nr, nc));
					} else {
						L.offer(new loc(nr, nc));
					}
				}
			}
			
			//melt ice
			int wsize = water.size();
			for (int i = 0; i < wsize; i++) {
				loc wcur = water.poll();
				for (int j = 0; j < 4; j++) {
					int nr = wcur.r + dr[j];
					int nc = wcur.c + dc[j];
					
					if(nr<0||nr>=R||nc<0||nc>=C) continue;
					
					if(map[nr][nc]=='X') {
						map[nr][nc] = '.';
						water.offer(new loc(nr, nc));
					}
				}
			}
			//백조 다음 이동
			for (loc n:nextL) {
				L.offer(n);
			}
			nextL.clear();
			
			time++;
		}
	}
	
	static private class loc {
		int r, c;

		public loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
}
