import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		map[0][0] = 1;
		int end = N*M;
		int cnt = 1;
		int ans = 0;
		int r = 0, c = 0, d = 0;
		
		while(true) {
			if(cnt == end) break;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0||nr>=N||nc<0||nc>=M||map[nr][nc] != 0) {
				d = (d+1) % 4;
				nr = r+dr[d];
				nc = c+dc[d];
				ans++;
			}
			r = nr;
			c = nc;
			cnt++;
			map[r][c] = cnt;
		}
		
		System.out.println(ans);
	}
}