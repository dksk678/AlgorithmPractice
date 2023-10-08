import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/* 10:30~10:50(20), 23:10 ~ 1:30(140) -> 160
 * 마리수 m, 턴수 t
 * 초기 위치 r,c
 * m개의 줄에는 r,c와 d
 * 
 * 1. 몬스터 알 생성(위치와 방향 동일, 알만 있는 맵)
 * 2. 몬스터 이동 (갈 수 없는 구간이면 반시계방향으로 45도)
 * 3. 상좌하우 순서대로 가장많이 먹을 수 있는 경우의 수로 이동
 *   - DFS로 4*4*4 방법 방향은 상좌하우 순으로 계산하면 마지막에 남아있는 경우가 무조건 가장 큼
 * 4. 몬스터 소멸 및 시체 생성
 * 5. 시체 2라운드 존재 (즉, 시체만 있는 맵 필요)
 * 6. 몬스터 태어남
 *  - t 만큼 반복
 * 결과 : 남아있는 몬스터 수
 * 
 * 문제 
 * 1. 방문체크할 때 이전으로 가는 방법이 있을 수 있음. 즉 먹은곳으로 돌아갈 수 있음
 * 2. temp 넣을 때 안움직이는 몬스터 넣어야함!!!!!!
 */

public class Main {
	private static BufferedReader br;
	
	//상 좌 하 우 대각선 (대각선 미포함 = 0,2,4,6)
	private static final int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	private static final int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	private static final int[] dr2 = {-1, 0, 1, 0};
	private static final int[] dc2 = {0, -1, 0, 1};
	
	private static int[][] deadMap = new int[4][4]; //시체 위치
	private static ArrayList<Monster> eggs = new ArrayList<>(); //알 정보
	private static ArrayList<Integer>[][] monsters = new ArrayList[4][4]; //몬스터 정보 r,c 방향
	
	private static int[] pacman = new int[2]; //팩맨 위치
	
	private static int maxCount;	 //가장 많은 몬스터 먹는 횟수
	private static int[][] maxEatPath; //그 경로

	private static class Monster {
		int r, c, d;

		public Monster(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Monster [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		int t = init();
		
		for (int i = 0; i < t; i++) {
			simulation();			
		}
		
		System.out.println(getMonsterCount());
	}

	private static int getMonsterCount() {
		int totalCount = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				totalCount += monsters[i][j].size();
			}
		}
		
		return totalCount;
	}

	private static int init() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				monsters[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			monsters[r][c].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		pacman[0] = Integer.parseInt(st.nextToken())-1;
		pacman[1] = Integer.parseInt(st.nextToken())-1;
		
		return t;
	}

	
	private static void simulation() {
		//1. 복제 (알 생성)
		copyMonster();
		
		//2. 몬스터 이동
		ArrayList<Integer>[][] tempMap = new ArrayList[4][4];  //이동한 몬스터들 저장할 임시 배열
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempMap[i][j] = new ArrayList<>();
			}
		}
		
//		printMonst();
		//이동
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(monsters[i][j].isEmpty()) continue;
				
				moveMonster(i, j, monsters[i][j], tempMap);				
			}
		}
		//이동 완료
		for (int i = 0; i < 4; i++) {
			monsters[i] = tempMap[i].clone();
		}
//		printMonst();
		
		//3. 팩맨 이동 & 먹기 (DFS)
		maxEatPath = new int[3][2];
		maxCount = -1;
		
		boolean[][] visited = new boolean[4][4];
		
		findPathMaxEat(0, 0, pacman[0], pacman[1], new int[3][2], visited); //depth, count, maxCount, r,c;
		eatMonsterAndsetDeadBody(); //monster 삭제 및 시체 저장
		movePacman(); //pacman 이동
		
		//4.복제완성
		addMonster();
		
		//5. 시체 카운터 줄이기
		deleteDeadBody();
		
//		printMonst();
		
	}

	//시체 카운터 줄이기
	private static void deleteDeadBody() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(deadMap[i][j] > 0) {
					deadMap[i][j]--;
				}
			}
		}
	}

	//부화
	private static void addMonster() {
		for (int i = 0, size=eggs.size(); i < size; i++) {
			Monster egg = eggs.get(i);
			
			monsters[egg.r][egg.c].add(egg.d);
		}
		eggs.clear();
	}
	
	//팩맨이동
	private static void movePacman() {
		pacman[0] = maxEatPath[2][0];
		pacman[1] = maxEatPath[2][1];
	}
	//몬스터 먹고 시체 남기기
	private static void eatMonsterAndsetDeadBody() {
		for (int i = 0; i < 3; i++) {
			int r = maxEatPath[i][0];
			int c = maxEatPath[i][1];
			if(isMonster(r, c)) {
				monsters[r][c].clear();
				deadMap[r][c] = 3;				
			}
		}
	}
	
	private static boolean isMonster(int r, int c) {
		if(!monsters[r][c].isEmpty()) { //다음 위치가 시체인가?
			return true;
		}
		return false;
	}

	private static void findPathMaxEat(int dep, int count, int r, int c, int[][] path, boolean[][] visited) {
		if(dep == 3) {
			if(maxCount < count) {
				for (int i = 0; i < 3; i++) {
					maxEatPath[i] = path[i].clone();
				}
				maxCount = count;
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextR = r+dr2[i];
			int nextC = c+dc2[i];
			
			if(!isRange(nextR, nextC)) continue;
			
			path[dep][0] = nextR;
			path[dep][1] = nextC;
			
			if(visited[nextR][nextC]) {
				findPathMaxEat(dep+1, count, nextR, nextC, path, visited);
				continue;
			}
			
			visited[nextR][nextC] = true;
			
			findPathMaxEat(dep+1, count+monsters[nextR][nextC].size(), nextR, nextC, path, visited);	
		
			visited[nextR][nextC] = false;
		}
	}


	private static void moveMonster(int r, int c, ArrayList<Integer> monstList, ArrayList<Integer>[][] tempMap) {
		for (int i = 0, size = monstList.size(); i < size; i++) {
			int startD = monstList.get(i);
			
			int nextR = r + dr[startD];
			int nextC = c + dc[startD];
			int nextD = startD;
			
			while(true) {
				if(isRange(nextR, nextC) && !isDeadBody(nextR, nextC) && !isPacMan(nextR, nextC)) {//이동시키고 값 삭제
					tempMap[nextR][nextC].add(nextD);
					break;
				}
				
				nextD = (nextD+7)%8; // 45도 회전
				if(nextD == startD) {
					tempMap[r][c].add(nextD);
					break; //갈 수 없으면 멈춤
				}
				
				nextR = r + dr[nextD];
				nextC = c + dc[nextD];
			}
		}
	}

	private static boolean isPacMan(int nextR, int nextC) {
		if(pacman[0] == nextR && pacman[1] == nextC) { //다음 위치가 시체인가?
			return true;
		}
		return false;
	}

	private static boolean isDeadBody(int nextR, int nextC) {
		if(deadMap[nextR][nextC] > 0) { //다음 위치가 시체인가?
			return true;
		}
		return false;
	}

	private static void copyMonster() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(monsters[i][j].isEmpty()) continue;
				
				for (int m = 0, size=monsters[i][j].size(); m < size; m++) {
					eggs.add(new Monster(i, j, monsters[i][j].get(m)));
				}
			}
		}
	}

	
	private static boolean isRange(int nextR, int nextC) {
		return nextR>=0 && nextR<4 && nextC>=0 && nextC<4;
	}

	private static void printMonst() {
		for (int i = 0; i < monsters.length; i++) {
			System.out.println(Arrays.toString(monsters[i]));
		}
		System.out.println("");
	}
	
	private static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("");
	}
}