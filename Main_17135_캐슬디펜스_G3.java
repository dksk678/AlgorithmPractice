import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* NxM격자판
 * 
 * N+1행의 모든 칸은 성.
 * 0. 3명의 궁수 배치
 * 1. 거리가 D이하인 적 중 가장 가까운. -> 가장 왼쪽 . -> 여러 궁수에게 공격당할 수 있음.
 * 2. 적 아래로 이동. 성이 있는 칸으로 이동하는 경우 게임에서 제외. 모든 적이 제외되면 게임끝
 * 
 * -> 궁수의 공격으로 제거할 수 있는 적의 최대 수 구하기
 * 
 * 거리가 되는 경우를 다 찾아서 저장 .
 *  
 */
public class Main_17135_캐슬디펜스_G3 {
	static int[][] map;
	static int d, ans, N, M;
	static int[][] archer; //궁수 위치, 적위치, 적과 거리
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		archer = new int[3][4];
		
		combi(0, 0);
		
		System.out.println(ans);
	}

	private static void combi(int cur, int cnt) {
		if(cnt == 3) {
			int[][] tmp = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tmp[i], 0, M);
			}
            
			attack(tmp);
			
			return;
		}
		
		for (int i = cur; i < M; i++) {
			archer[cnt][0] = i;
			combi(i+1, cnt+1);
		}
	}
	
	private static void attack(int[][] map2) {
		int n = 0;
		int kill = 0;
		while(true) {
			for (int i = 0; i < 3; i++) {
				archer[i][1] = -1;
				archer[i][2] = -1;
				archer[i][3] = 0;
			}
			for (int i = 0; i < M ; i++) {
				for (int j = N-1; j >= 0; j--) {
					if(map2[j][i]==1) {
						for (int k = n; k < 3; k++) {
							chkDis(j, i, k);
						}
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if(archer[i][1]==-1||archer[i][2]==-1) continue;
				if(map2[archer[i][1]][archer[i][2]]==1) {
					map2[archer[i][1]][archer[i][2]] = 0;
					kill++;
				}
			}
			
			if(findEm(map2)) {
				ans = ans<kill?kill:ans;
				return;
			} else {
				map2 = goDown(map2);
			}
		}
	}

	private static int[][] goDown(int[][] map2) {
		for (int i = N-1; i >= 0 ; i--) {
			for (int j = 0; j < M; j++) {
				if(map2[i][j]==1) {
					map2[i][j] = 0;
					if(i+1<N) {
						map2[i+1][j] = 1; 
					}
				}
			}
		}
		return map2;
	}

	private static boolean findEm(int[][] map2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map2[i][j]>=1) return false;
			}
		}
		return true;
	}

	private static boolean chkDis(int i, int j, int n) {
		int d2  = Math.abs(N-i)+Math.abs(j-archer[n][0]);
		int curd = archer[n][3];
		
		if((curd==0 && d2<=d) || d2<curd) {
			archer[n][1] = i;
			archer[n][2] = j;
			archer[n][3] = d2;
			return true;
		}
		return false;
	}
	
}