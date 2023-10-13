import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/* 240
 * 모든 포탑이 존재 (N*M) 하지만 뿌셔진 포탑이 있음 공격력:0
 * 0이하가 되면 더이상 공격 불가.
 * 
 * 4가지 액션 수행. 부서지지 않은 포탑이 1개가 되면 중지!
 * 
 * 1. 공격자 선정
 *   - 가장 약한 포탑이 공격자
 *   - n+m 만큼 공격력 증가
 *   - 1. 공격력이 같을 경우 가장 최근
 *   - 2. 행과열의 합이 가장 큰 경우
 *   - 3. 열 값이 가장 큰 경우
 * 2. 공격자 공격
 *   - 자신을 제외한 가장 강한 포탑 공격
 *   - 1. 공격한지 가장 오래된 포탑
 *   - 2. 행과 열합 가장 작은 경우
 *   - 3. 열 값이 가장 작은 경우
 *   
 * 3. 레이저 공격
 *   - 1. 상하좌우 이동
 *   - 2. 부서진 포탑 이동 불가
 *   - 3. 가장자리인 경우 반대편으로 0,n -> 0,0 으로
 *   - 4. 최단 경로로 공격 (우/하/좌/상) 선순위로 경로 선택
 *   - 		5. 경로가 없으면 포탄 공격
 *   - 6. 해당 경로에 있는 포탑들도 공격력/2 만큼 피해를 받음
 * 4. 포탑 공격
 *   - 1. 공격대상은 공격력만큼 피해
 *   - 2. 주의 8개 방향 포탑 피해, 막혀있으면 반대편에 피해줌
 *   - 3. 공격력의 절반만큼 피해
 *   - 4. 공격자는 영향 X
 *   
 * 5. 정비
 *   - 공격과 무관한 포탑 +1
 * 
 * 6. 전체 과정이 끝나면 가장 강한 포탑의 공격력 출력
 * 
 * -- 메인 포탑 배열이 필요함.
 * -- 가장 공격력이 작은 포탑, 큰 포탑 구하는 메소드 필요함.
 * -- 공격자,공격받은포탑을 저장한 2차원 배열 추가
 * -- 공격 count 저장할 배열 추가
 * 
 * !!!!!!!!! 만약 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지됩니다.
 */
public class Main_포탑부수기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int n,m,k;
	
	private static int[][][] map; //공격 count와 터렛정보
	private static int[] minTur; //공격자
	private static int[] maxTur; //선택자
	private static Pos[][] isAttacked; //레이저 경로 저장하기 위한 배열
	private static boolean[][] plusTurret; //재정비 체크를 위한 배열
	
	private static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + "]";
		}
	}
	
	private static void init() throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = toInt(st.nextToken());
		m = toInt(st.nextToken());
		k = toInt(st.nextToken());
		
		map = new int[2][n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[0][i][j] = toInt(st.nextToken());
			}
		}
		
		minTur = new int[2];
		maxTur = new int[2];
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int i = 0; i < k; i++) {
			simulation(i+1);
		}
		
		//최대값 터렛 찾기
		findMaxTurret();
		
		System.out.println(map[0][maxTur[0]][maxTur[1]]);
	}


	private static void simulation(int turn) {
		findMinTurret();
		findMaxTurret();
		
		if(minTur[0]==maxTur[0] && minTur[1]==maxTur[1]) return;
		//포탑 공격력 증가 및 공격 횟수 증가
		
		map[0][minTur[0]][minTur[1]] += (n+m);
		map[1][minTur[0]][minTur[1]] = turn;
		
//		System.out.println(Arrays.toString(minTur));
//		System.out.println(Arrays.toString(maxTur));
		
		//재정비 때 불가능
		plusTurret = new boolean[n][m];
		plusTurret[maxTur[0]][maxTur[1]] = true;
		plusTurret[minTur[0]][minTur[1]] = true;

		if(canLaser()) {
			laserAttack();
		} else {
			turretAttack();
		}
		
		repairTurret();
	}

	//공격자한테 피해 없어야 함!
	private static void turretAttack() {
		int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
		int r = maxTur[0];
		int c = maxTur[1];
		int damage = map[0][minTur[0]][minTur[1]];
		map[0][r][c] -= damage;
		
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isRange(nr, nc)) {
				//n => 0, -1 => n-1
				nr = (n+nr) % n;
				nc = (m+nc) % m;
			}
			
			if(map[0][nr][nc] <= 0 || plusTurret[nr][nc]) continue;
			
			plusTurret[nr][nc] = true;
			map[0][nr][nc] -= damage/2;
		}
	}

	private static void repairTurret() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[0][i][j] <= 0 || plusTurret[i][j]) continue;
				
				map[0][i][j] += 1;
			}
		}
	}
	
	private static void laserAttack() {
		int r = maxTur[0];
		int c = maxTur[1];
		int damage = map[0][minTur[0]][minTur[1]];
		
		//max 포탑 공격
		map[0][r][c] -= damage;
		Pos cur = isAttacked[r][c];
		r = cur.r;
		c = cur.c;
		//레이저 경로에 따라 공격
		while(true) {
			cur = isAttacked[r][c];
			
			plusTurret[r][c] = true;
			if(cur.r==r && cur.c==c) break;
			map[0][r][c] -= damage/2;
			
			r = cur.r;
			c = cur.c;
		}
	}

	private static boolean canLaser() {
		Queue<Pos> q = new ArrayDeque<>();
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		isAttacked = new Pos[n][m];
		
		q.offer(new Pos(minTur[0],minTur[1]));
		isAttacked[minTur[0]][minTur[1]] = new Pos(minTur[0],minTur[1]);
		
		int maxR = maxTur[0];
		int maxC = maxTur[1];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!isRange(nr, nc)) { //반대편 이동
					//n => 0, -1 => n-1
					nr = (n+nr) % n;
					nc = (m+nc) % m;
				}
				
				if(isAttacked[nr][nc] != null || map[0][nr][nc] <= 0) continue;
				
				isAttacked[nr][nc] = new Pos(cur.r, cur.c);
				
				if(nr==maxR && nc==maxC) return true;
				
				q.offer(new Pos(nr, nc));
			}
		}
		
		return false;
	}

	private static void findMinTurret() {
		int min = 123456789;
		int recAtk, sumRC, r, c;
		sumRC = 0; r = 0; c = 0;
		recAtk = 0;
		
		ArrayList<Pos> minTurs = new ArrayList<>();
		
		for (int i = 0; i < n; i++) { //nm부터 시작하면 열값을 비교하는 경우를 생략할 수 있음.
			for (int j = 0; j < m; j++) {
				if(map[0][i][j] <= 0) continue;
				int cur = map[0][i][j];
				
				if(min > cur) {
					minTurs.clear();
					minTurs.add(new Pos(i, j));
					min = cur;
				} else if(min == cur) {
					minTurs.add(new Pos(i, j));
				}
			}
		}
		
		//min값이 같은 경우
		for (int k = 0, s=minTurs.size(); k < s; k++) {
			Pos cur = minTurs.get(k);
			
			int atkCnt = map[1][cur.r][cur.c];
			//단순 공격력이 약한 경우, //같으면서 행열합이 더 큰 경우 //먼저 나온 수가 무조건 열이 크므로 이후 같은 값은 무시
			if((recAtk < atkCnt) || (recAtk == atkCnt && sumRC < cur.r+cur.c)) {
				recAtk = atkCnt;
				r = cur.r;
				c = cur.c;
				sumRC = r+c;
			}
		}
		
		minTur[0] = r;
		minTur[1] = c;
	}
	
	private static void findMaxTurret() {
		int max = 0;
		int recAtk, sumRC, r, c;
		recAtk = 123456789; sumRC = 0; r = 0; c = 0;
		
		ArrayList<Pos> maxTurs = new ArrayList<>();
		
		for (int i = 0; i < n; i++) { //nm부터 시작하면 열값을 비교하는 경우를 생략할 수 있음.
			for (int j = 0; j < m; j++) {
				if(map[0][i][j] <= 0) continue;
				int cur = map[0][i][j];
				
				if(max < cur) {
					maxTurs.clear();
					maxTurs.add(new Pos(i, j));
					max = cur;
				} else if(max == cur) {
					maxTurs.add(new Pos(i, j));
				}
			}
		}
		//max값이 같은 경우
		for (int k = 0, s=maxTurs.size(); k < s; k++) {
			Pos cur = maxTurs.get(k);
			int atkCnt = map[1][cur.r][cur.c];
			//단순 공격력이 약한 경우, //같으면서 행열합이 더 큰 경우
			//먼저 나온게 무조건 열이 클 수 밖에 없음 -> 덮어써야함.
			if((recAtk > atkCnt) || (recAtk == atkCnt && sumRC >= cur.r+cur.c)) {
				recAtk = atkCnt;
				r = cur.r;
				c = cur.c;
				sumRC = r+c;
			}
		}
		
		maxTur[0] = r;
		maxTur[1] = c;
	}
	
	private static boolean isRange(int nr, int nc) {
		return 0<=nr && nr<n && 0<=nc && nc<m;
	}

	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
}