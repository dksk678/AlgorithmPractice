import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 톱니바퀴 G5 14891 100
 * 
 * 12시 방향부터 시작
 * 
 * 회전하기 전에 양 옆 톱니바퀴와 연결부분이 다르면 회전 check!
 * 
 * 방향이 1이면 시계방향 -> 배열 오른쪽으로
 * -1 이면  배열 왼쪽으로
 */

public class Main_14891_톱니바퀴 {
	static ArrayList<ArrayList<Integer>> gears;
	static int[] chkrotate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = "";
		gears = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			gears.add(new ArrayList<>());
		}

		for (int i = 0; i < 4; i++) {
			str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gears.get(i).add(str.charAt(j) - '0');
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			chkrotate = new int[4];
			rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}

		int ans = 0;
		for (int i = 0; i < 4; i++) {
			ans += gears.get(i).get(0) * Math.pow(2, i); // 1 2 4 8
		}

		System.out.println(ans);
	}

	private static void rotate(int ngear, int d) {
		chkrotate[ngear] = d;
		chkPole(ngear, d);

		for (int i = 0; i < 4; i++) {
			if (chkrotate[i] == 1) { // 시계방향이면
				gears.get(i).add(0, gears.get(i).get(7)); // 오른쪽으로 회전
				gears.get(i).remove(8);
			} else if (chkrotate[i] == -1) {
				gears.get(i).add(gears.get(i).get(0)); // 왼쪽으로 회전
				gears.get(i).remove(0);
			}
		}
	}

	private static void chkPole(int ngear, int d) {
		// 오른쪽 기어와 체크
		if (ngear != 3 && gears.get(ngear).get(2) != gears.get(ngear + 1).get(6) && chkrotate[ngear + 1] == 0) { 
			chkrotate[ngear + 1] = d * -1; // true일 때만 돌기
			chkPole(ngear + 1, d * -1);
		}
		// 왼쪽기어와 체크
		if (ngear != 0 && gears.get(ngear - 1).get(2) != gears.get(ngear).get(6) && chkrotate[ngear - 1] == 0) { 
			chkrotate[ngear - 1] = d * -1; // true일 때만 돌기
			chkPole(ngear - 1, d * -1);
		}
	}

}
