package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기4 {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int N, M, K, min;
	static boolean[] visited;
	static int[][] rotateCal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 크기
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rotateCal = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotateCal[i][0] = Integer.parseInt(st.nextToken());
			rotateCal[i][1] = Integer.parseInt(st.nextToken());
			rotateCal[i][2] = Integer.parseInt(st.nextToken());
		}

		min = 100000000;
		visited = new boolean[K];

		backtrack(0, map);

		System.out.println(min);
	}

	private static void backtrack(int cnt, int[][] map) {
		if (cnt == K) {
			min = Math.min(getMin(map), min);
			/*System.out.println("######------" + cnt);
			for (int j = 0; j < N; j++) {
				System.out.println(Arrays.toString(map[j]));
			}
			System.out.println("");*/
			return;
		}
		// 배열 백트래킹 하기 위해 임시배열 만들어서 계산
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, M);
		}

		for (int i = 0; i < K; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			for (int j = 0; j < rotateCal[i][2]; j++) {
				tmp = rotate(tmp, i, j);
			}
			backtrack(cnt + 1, tmp);

			visited[i] = false;
			for (int j = 0; j < N; j++) {
				System.arraycopy(map[j], 0, tmp[j], 0, M);
			}
		}
	}

	// 최소값 구하기
	private static int getMin(int[][] map) {
		int min = 10000000;
		int sum = 0;
		for (int[] i : map) {
			sum = 0;
			for (int j : i) {
				sum += j;
			}
			min = min > sum ? sum : min;
		}
		return min;
	}

	// 회전하기
	private static int[][] rotate(int[][] tmpmap, int idx, int cnt) {//배열, 회전연산 해야될 번호, 몇번째 외각인지
		int startR = rotateCal[idx][0] - rotateCal[idx][2] + cnt - 1; //0부터 시작이므로 1빼줌.
		int startC = rotateCal[idx][1] - rotateCal[idx][2] + cnt - 1;
		int endR = rotateCal[idx][0] + rotateCal[idx][2] - cnt;
		int endC = rotateCal[idx][1] + rotateCal[idx][2] - cnt;

		int cur = tmpmap[startR][startC];
		int r = startR;
		int c = startC;
		int next = 0;
		int d = 0;

		while (d != 4) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < startR || nr >= endR || nc < startC || nc >= endC) { //갈 수 없으면 방향 바꾸기
				d++;
				continue;
			}

			next = tmpmap[nr][nc]; //다음 위치값 미리 저장
			tmpmap[nr][nc] = cur; //현재 값을 다음위치로
			r = nr;
			c = nc;
			cur = next; //현재값을 다음값으로
		}
		return tmpmap;
	}
}
