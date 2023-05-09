import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
이동하면서 최소 최대 좌표 구하고 곱하면 됨
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			String comm = br.readLine();
			int len = comm.length();
			int r = 0;
			int c = 0;
			int d = 0;
			int minr = r;
			int minc = c;
			int maxr = r;
			int maxc = c;
			for (int j = 0; j < len; j++) {
				switch(comm.charAt(j)) {
					case('F'):
						//전진
						r = r+dr[d];
						c = c+dc[d];
						break;
					case('B'):
						r = r-dr[d];
						c = c-dc[d];
						break;
					case('L'):
						d = (d+3) % 4;
						break;
					case('R'):
						d = (d+1) % 4;
						break;
				}
				
				minr = minr > r ? r: minr;
				minc = minc > c ? c: minc;
				maxr = maxr < r ? r: maxr;
				maxc = maxc < c ? c: maxc;
			}
			
			sb.append((maxr-minr)*(maxc-minc)).append("\n");
		}
	
		System.out.println(sb.toString());
	}
}