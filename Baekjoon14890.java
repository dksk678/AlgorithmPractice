import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 경사로 G3 21:40
 * 2N개의 길이 있음
 * 길을 지나가려면 모든 칸의 높이가 같거나 경사로를 놓아서 지나갈 수 있는 길을 만듬
 * 경사로는 항상 높이가 1, 길이가 N임
 * 
 * -경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
 * -낮은 칸과 높은칸의 차이는 1
 * -경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 */

public class Baekjoon14890 {
	static int[][] map;
	static int N;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			//가로 찾기
			if (findCol(i))
				ans++;
			//세로 찾기
			if (findRow(i))
				ans++;
		}

		System.out.println(ans);
	}

	private static boolean findRow(int i) {
		int curp = 0;
		int curh = map[curp][i];
		boolean[] v = new boolean[N];
		for (int j = 1; j < N; j++) {
			int nexth = map[j][i];
			System.out.println(curp + " " + j);
			if (Math.abs(curh - nexth) >= 2) {
				System.out.println(curp + " " + j + " 2이상차이");
				return false; // 2이상 차이나면 X
			}
			if (v[curp] && curh < nexth)
				return false; // 현재위치에 경사로가 있는 데 다음 위치가 높으면

			if (curh < nexth && (j - curp) < L) { // 현재 위치의 높이와 앞에 위치 높이가 다른데 L만큼의 거리가 확보 안됐으면
				return false;
			}
			if (curh == nexth) { // 현재와 다음 높이가 같으면 넘어감
				if (v[curp])
					curp = j; // 현재위치에 경사로가 놓아져 있으면 다음 위치 저장
				continue;
			}

			// 앞에가 더 크면 이전 까지에 경사로 넣기
			if (curh < nexth) {
				for (int k = curp; k < curp + L; k++) {
					if (k >= N) {
						System.out.println("앞이 짧아서 경사로 못 놓음");
						return false; // 경사로를 놓을 수 없으면 false
					}
					if (map[k][i] != curh) {
						System.out.println("경사로 놓을 높이가 다름.");
						return false; // 경사로를 놓을 경로의 높이가 다르면 false
					}
					v[k] = true;
				}
				curp = j;
				curh = map[curp][i];
			} else {// 앞에가 더 작으면 앞에 경사로 넣기
				for (int k = j; k < j + L; k++) {
					if (k >= N) {
						System.out.println("앞이 짧아서 경사로 못 놓음");
						return false; // 경사로를 놓을 수 없으면 false
					}
					if (map[k][i] != nexth) {
						System.out.println("경사로 놓을 높이가 다름.");
						return false; // 경사로를 놓을 경로의 높이가 다르면 false
					}
					v[k] = true;
				}
				j += L - 1;
				curp = j;
				if (curp >= N)
					return true;
				curh = map[curp][i];
			}
		}

		return true;
	}

	private static boolean findCol(int i) {
		int curp = 0;
		int curh = map[i][curp];
		boolean[] v = new boolean[N];
		for (int j = 1; j < N; j++) {
			int nexth = map[i][j];
//			System.out.println(curp + " " + j);
//			System.out.println(Arrays.toString(v));
			if (Math.abs(curh - nexth) >= 2) {
				System.out.println(curp + " " + j + " 2이상차이");
				return false; // 2이상 차이나면 X
			}
			if (v[curp] && curh < nexth)
				return false; // 현재 경사로를 놓은 상태에서 다음에도 높이가 더 높으면

			if (curh < nexth && (j - curp) < L) {
				System.out.println("뒤가 짧아서 경사로 못 놓음");
				return false;
			}
			if (curh == nexth) { // 현재와 다음 수가 같으면 넘어감
				if (v[curp])
					curp = j; // 현재위치에 경사로가 놓아져 있으면 다음 위치 저장
				continue;
			}

			if (curh < nexth) {
				for (int k = curp; k < curp + L; k++) {
					if (k >= N) {
						System.out.println("앞이 짧아서 경사로 못 놓음");
						return false; // 경사로를 놓을 수 없으면 false
					}
					if (map[i][k] != curh) {
						System.out.println("경사로 놓을 높이가 다름.");
						return false; // 경사로를 놓을 경로의 높이가 다르면 false
					}
					v[k] = true;
				}

				curp = j;
				if (curp >= N)
					return true;
				curh = map[i][curp];
			} else {
				for (int k = j; k < j + L; k++) {
					if (k >= N) {
						System.out.println("앞이 짧아서 경사로 못 놓음");
						return false; // 경사로를 놓을 수 없으면 false
					}
					if (map[i][k] != nexth) {
						System.out.println("경사로 놓을 높이가 다름.");
						return false; // 경사로를 놓을 경로의 높이가 다르면 false
					}
					v[k] = true;
				}
				j += L - 1;
				curp = j;
				curh = map[i][curp];
			}
		}

		System.out.println("가로 찾기 중 " + i);

		return true;
	}

}
