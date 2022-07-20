import java.io.BufferedReader;
import java.io.InputStreamReader;
/*   별 찍기 10 G5 
 *   분할 정복 사용 하기
 */

public class Baekjoon2447_2 {
	static int N;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		req(0, 0, N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]==1?"*":" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void req(int r, int c, int n) {
		if(n==3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i==1 && j==1) continue;
					arr[i+r][j+c] = 1;
				}
			}
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i==1 && j==1) continue;
					req(r+n/3*i, c+n/3*j, n/3);
				}
			}
		}
	}
}
