import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baekjoon16954 {
	static int N;
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		System.out.println(BFS(map));
	}

	private static int BFS(char[][] map) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {7,0,0});
		boolean[][] v = new boolean[8][8];
		int[] dr = {0, -1, -1, -1, 0, 1, 1, 1, 0};
		int[] dc = {1, 1, 0, -1, -1, -1, 0, 1, 0};
		int time = 0;
		while(!q.isEmpty()) {
			int r = q.peek()[0];
			int c = q.peek()[1];
			int d = q.poll()[2];
			
			if(map[r][c]=='#') continue; //벽이 내려온 후 캐릭터 위치가 되면 안됨.

			if(r==0&&c==7) return 1;
			
			
			for (int i = 0; i < 9; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				
				if(i==8) { //제자리면 바로 넘어감
					q.offer(new int[] {nr, nc, d+1});
					continue;
				}
				if(nr<0||nr>=8||nc<0||nc>=8||v[nr][nc]||map[nr][nc]=='#') continue;
				if(nr-1>=0&&map[nr-1][nc]=='#') continue; //이동한 곳 바로 위가 #이면 무조건 안됨.

				v[nr][nc] = true;
				q.offer(new int[] {nr, nc, d+1});
			}
			
			if(d>time) {
				time = d+1;
				for (int i = 7; i >= 0; i--) {
					for (int j = 0; j < 8; j++) {
						if(map[i][j]=='#') {
							if(i==7) map[i][j] = '.';
							else {
								map[i+1][j] = '#';
								map[i][j] = '.';
							}
						}
					}
				}
			}
			
		}
		
		return 0;
	}
//--------------print---------------
	private static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
