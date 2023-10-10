import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 1:00 ~
 * n*n 격자 판, 도망자 m명, 나무 h개
 * 좌우, 상하 둘중에 하나만 가능
 * (좌우는 오른쪽으로 시작 1, 상하는 아래를 보고 시작 2)
 * 
 * 1. 도망자가 먼저 움직임 (술래와의 거리가 3이하인 경우만, 맨허튼거리 방법)
 *  - 술래가 있으면 안됨
 *  - 나무가 있어도 이동 가능
 *  - 격자를 벗어나면 반대로 이동
 * 2. 술래 움직임
 *  - 달팽이 모양
 *  - 방향이 바뀌는 구간에 도착하면 바로 방향 바꿔줌.(이동 X)
 *    - 중앙이나,1,1에 도달해도 반대로 방향 바꿔줌
 * 3. 이동 후 시야에 있는 도망자 잡기 (항상 3칸만)
 *  - 나무 위치에 있는 도망자는 Ok
 *  - 현재 턴 t * 잡은 수 만큼 점수를 얻음
 *  
 * n*n-1 turn마다 방향 바뀜
 * n*n-1/2 가 짝수인경우 안쪽 달팽이, 홀수 바깥쪽
 */
public class Main_술래잡기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dr = {0, 1, 0, -1}; //안쪽=0, 바깥쪽=1
	private static int[] dc = {1, 0, -1, 0}; //
	private static ArrayList<Integer>[][] map; //같은 방향에 같이 있는 경우는 무조건 없음!
	private static ArrayList<Integer>[][] nextMap; //같은 방향에 같이 있는 경우는 무조건 없음!
	private static IT it;
	private static boolean[][] tree;
	private static int n, m, h, k, turn; //k = turn
	private static int[] path1;
	
	private static class IT {
		int r, c, d;

		public IT(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "IT [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		int totalPoint = 0;
		
		for (int i = 0; i < k; i++) {
			
			totalPoint += simulation(i+1);
			turn++;
			if(turn == 2*(n*n)-2) {
				turn = 0;
			}
		}
		
		System.out.println(totalPoint);
	}
	
	private static int simulation(int t) {
		// 도망자 이동
		moveRunaway();
		
		// 술래 이동
		moveIT();
		
		// 도망자 잡기
		return catchRunaway(t);
	}


	private static int catchRunaway(int t) {
		int seeR = it.r;
		int seeC = it.c;
		int seeD = it.d;
		int point = 0;
		
		for (int i = 0; i < 3; i++) {
			
			if(!isRange(seeR, seeC) || isTree(seeR, seeC)) {
				seeR += dr[seeD];
				seeC += dc[seeD];
				continue;
			}
			
			ArrayList<Integer> curRun = map[seeR][seeC];
			
			point += t*curRun.size();
			
			map[seeR][seeC].clear();
			
			seeR += dr[seeD];
			seeC += dc[seeD];
		}
		
		return point;
	}

	private static boolean isTree(int seeR, int seeC) {
		return tree[seeR][seeC];
	}

	private static void moveIT() {
		//이동 후 방향 전환
		it.r += dr[it.d];
		it.c += dc[it.d];
		
		it.d = path1[turn];
	}

	private static boolean isReversePosition() {
		return (it.r==0 && it.c==0) || (it.r==n/2 && it.c==n/2);
	}

	private static void moveRunaway() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				ArrayList<Integer> curRun = map[i][j];
				
				if(!is3Range(i, j)) { //범위에 없으면 안움직임. 하지만 다른데서 들어오는 경우가 있기 때문에 따로 저장
					for (int k = 0, s=map[i][j].size(); k < s; k++) {
						nextMap[i][j].add(map[i][j].get(k));
					}
					continue;
				}
				
				for (int k = 0, s=curRun.size(); k < s; k++) {
					int d = curRun.get(k);
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(!isRange(nr, nc)) {
						d = (d + 2) % 4;
						nr = i + dr[d];
						nc = j + dc[d];
					}
					// 다음 위치가 술래면 가만히 있기
					if(isItPos(nr, nc)) {
						nextMap[i][j].add(map[i][j].get(k));
						continue;
					}
					//이동 하기
					nextMap[nr][nc].add(d);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new ArrayList<>(nextMap[i][j]);
				nextMap[i][j].clear();
			}
		}
	}
	
	private static boolean isItPos(int nr, int nc) {
		return it.r == nr && it.c == nc;
	}

	private static boolean is3Range(int i, int j) {
		return (Math.abs(it.r-i) + Math.abs(it.c-j)) <= 3;
	}

	private static boolean isRange(int nr, int nc) {
		return 0<=nr && nr<n && 0<=nc && nc<n;
	}

	private static void init() throws IOException{
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()); //turn 수
		turn = 1;
		
		map = new ArrayList[n][n];
		nextMap = new ArrayList[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new ArrayList<>();
				nextMap[i][j] = new ArrayList<>();
			}
		}
		
		tree = new boolean[n][n]; //좌우 or 상하
		
		//set 술래 위를 보며 시작
		it = new IT(n/2, n/2, 3);
		
		//set 도망자 
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			map[x][y].add(d);
		}
		
		//set 나무
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			tree[x][y] = true;
		}
		
		setITPath();
	}
	

	private static void setITPath() {
		int pN = 2*((n*n)-1) > 200 ? 200 : 2*((n*n)-1);
		
		path1 = new int[pN]; //3시작
		path1[0] = 3;
		path1[1] = 0;
		path1[pN-1] = 1;
		path1[pN-2] = 2;
		//k가 1 789
//		path2 = new int[pN-1];//1시작
//		path2[pN-2] = 1;
//		path2[pN-3] = 2;
		
		//1,1,2,2,3,3,4,4,5
		//1,2,34,56,789,101112,13141516,17181920,21222324
		//01 23 45
		//상,우,하,좌, 상,  우
		//5,4,4,3,3,2,2,1,1
		
		int max = n*n-1 > 100 ? 100 : n*n-1;
		
		int cnt = 0;
		int curc = 1;
		for (int i = 2; i < max; i+=curc) {
			if(cnt++%2==0) {
				curc++;
			}
			
			path1[i] = (path1[i-1]+1) % 4;
			path1[pN-i-1] = (path1[pN-i]+1) % 4;
			
			for (int j = i+1; j < i+curc; j++) {
				if(j >= max) break;
				
				path1[j] = path1[j-1];
				path1[pN-j-1] = path1[pN-j];
			}
		}
//		System.out.println(Arrays.toString(path1));
	}

	private static void printMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(Arrays.toString(map[i][j].toArray())+", ");
			}
			System.out.println("");
		}
		System.out.println();
	}
}
