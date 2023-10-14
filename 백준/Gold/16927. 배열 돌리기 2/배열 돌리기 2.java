import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int n,m,r;
	private static int[][] map;
	
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		init();
		
		rotate();
		
		printAns();
	}

	private static void printAns() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void rotate() {
		int max = n < m ? m : n;
		int min = n < m ? n : m;
		//4*4 5*4 18
		// max*2 + (min-2)*2
		// max-=2, min-=2, min < 0 이면 멈춤
		for (int i = 0, s=min/2; i < s; i++) {
			int size = max*2 + (min-2)*2;
			rotateMap(i, r%size); //시작좌표, rt 횟수
			
			max-=2; min-=2;
		}
	}

	private static void rotateMap(int rc, int rtCnt) {
		for (int i = 0; i < rtCnt; i++) {
			int temp = map[rc][rc];
			int r = rc;
			int c = rc;
			int d = 0;
			
			while(d < 4) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(isRange(nr, nc, rc)) {
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				} else {
					d++;
				}
			}
			map[rc+1][rc] = temp; //마지막
		}
		
	}

	private static boolean isRange(int nr, int nc, int rc) {
		return rc<=nr && nr<n-rc && rc<=nc && nc<m-rc;
	}

	private static void init() throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = toInt(st.nextToken());
		m = toInt(st.nextToken());
		r = toInt(st.nextToken());
		
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = toInt(st.nextToken());
			}
		}
	}

	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
}