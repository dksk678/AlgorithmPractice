import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2931_가스관 {
	static int R, C;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static char[][] map;
	static char[][] dpipe = {{'-', '3', '4', '+'}, {'|','2','3','+'}, {'-','1','2','+'}, {'|','1','4','+'}};
	static boolean chk = false;
	static int[] usepipe = {-1, -1};
	static char ans2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		int sr = 0;
		int sc = 0;
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='M') {
					sr = i;
					sc = j;
				} else if(map[i][j]=='+') {
					cnt+=2;
				} else if(map[i][j]!='.') {
					cnt++;
				}
			}
		}
		
		int[][] v = new int[R][C];
		for (int i = 0; i < 4; i++) {
			int nr = sr + dr[i];
			int nc = sc + dc[i];
			
			if(nr<0||nr>=R||nc<0||nc>=C||map[nr][nc]=='.') continue;
			
			for (int j = 0; j < 4; j++) {
				if(map[nr][nc]==dpipe[i][j]) {
					v = new int[R][C];
					v[sr][sc] = 1;
					v[nr][nc] = 1;
					DFS(nr, nc, i, cnt, false, v);
				}
			}
		}
		
		System.out.println((usepipe[0]+1)+" "+(usepipe[1]+1)+" "+map[usepipe[0]][usepipe[1]]);
	}


	private static void DFS(int r, int c, int d, int cnt, boolean pipe, int[][] v) {
		if(cnt==0) {
			chk = true;
			return;
		}
		char cur = map[r][c];
		
		int nd = change(d, cur);
		int nr = r+dr[nd];
		int nc = c+dc[nd];
		
		if(nr<0||nr>=R||nc<0||nc>=C) return;

		if(map[nr][nc]=='.') {
			if(!pipe) {
				for (int i = 0; i < 4; i++) {
					usepipe[0] = nr;
					usepipe[1] = nc;
					map[nr][nc] = dpipe[nd][i];
					v[nr][nc] += 1;
					
					DFS(nr, nc, nd, cnt-1, true, v);
					
					if(chk) return;
					
					v[nr][nc] -= 1;
					map[nr][nc] = '.';
				}
			} else {
				return;
			}
		} else {
			if(v[nr][nc]>=2) return;
			if(v[nr][nc]==1 && map[nr][nc] != '+') return;
			
			v[nr][nc] += 1;
			DFS(nr, nc, nd, cnt-1, pipe, v);
			v[nr][nc] -= 1;
		}
	}
	
	static int change (int d, char pipe) {
		switch (pipe) {
		case '|':
			if(d==1 || d==3) return d;
		case '-':
			if(d==0 || d==2) return d;
		case '1':
			if(d==2) return 1;
			if(d==3) return 0;
		case '2':
			if(d==2) return 3;
			if(d==1) return 0;
		case '3':
			if(d==0) return 3;
			if(d==1) return 2;
		case '4':
			if(d==0) return 1;
			if(d==3) return 2;
		default:
			return d;
		}
	}

}