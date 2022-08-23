

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1_G5_DP {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] pipe = new int[3][N][N]; //0가로 , 1세로, 2대각일 때 값 저장
		pipe[0][0][0] = 1; //
		pipe[0][0][1] = 1;
		
		int[] dr = { 0, 1, 1 }; //우, 하, 대각
		int[] dc = { 1, 0, 1 };
		
		for (int r = 0; r < N; r++) {
			for (int c = 1; c < N; c++) {
				for (int piped = 0; piped < 3; piped++) { //현재 파이프의 방향
					if(pipe[piped][r][c] == 0 ) continue; //현재 위치에 파이프가 없으면 continue;
					
					for (int d = 0; d < 3; d++) { //방향 탐색.
						int nr = r+dr[d];
						int nc = c+dc[d];
						
						if(nr>=N||nc>=N||map[nr][nc]==1) continue;
						if((piped==1 && d==0) || (piped==0 && d==1)) continue;
						if(d==2 && (map[nr-1][nc]==1 || map[nr][nc-1]==1)) continue;
						
						pipe[d][nr][nc] += pipe[piped][r][c];
					}
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < 3; i++) {
			res += pipe[i][N-1][N-1];
		}
		
		System.out.println(res);
	}

}
