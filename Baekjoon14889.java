import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14889 {
	static int[][] map;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 99999999;
		ncr(0, 0, new int[N]);

		System.out.println(ans);
	}

	private static void ncr(int start, int cnt, int[] select) {
		if (cnt == N / 2) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (i != j && select[i] == select[j]) {
						if (select[i] == 1) {
							sumA += map[i][j];
							sumA += map[j][i];
						} else {
							sumB += map[i][j];
							sumB += map[j][i];
						}
					}
				}
			}
			int d = Math.abs(sumA - sumB);
			ans = ans > d ? d : ans;

			return;
		}

		for (int i = start; i < N; i++) {
			select[i] = 1;
			ncr(i + 1, cnt + 1, select);
			select[i] = 0;
		}
	}
}
