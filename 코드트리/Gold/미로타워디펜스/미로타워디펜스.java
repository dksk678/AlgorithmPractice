import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12:10 ~
/*
 * 1. 상하좌우 중 주어진 방향 공격.
 * (2. 몬스터 이동)
 * 3. 4개 이상 이어진 몬스터 있으면 삭제
 * 	- 삭제 되면 2번으로
 * 4. 나열했을 때 몬스터 번호와 이어진 개수를 통해 새로운 몬스터 배열 생성
 *  - 새로 생긴 배열이 배열 크기를 넘어가면 나머지 몬스터 무시
 * (5. 삭제된 몬스터 점수 합 계산, 삭제 되는 구간 마다 구하기)
 * 
 * -- > m 라운드 반복
 * 
 * ->> ArrayList로 몬스터 삭제시 자동 이동 되도록.
 * 
 * 예외
 * 1. 삭제할 때마다 위치 -1
 * 2. 중복계산할 때 마지막 계산 추가
 * 3. 격자의 범위를 넘는 경우 예외처리 해줘야 함 -> 시간 초과
 * 
 */
public class Main_미로타워디펜스_G1 {
	//좌,하,우,상 회전
	private static final int[] dr = {0, 1, 0, -1};
	private static final int[] dc = {1, 0, -1, 0};
	private static int[] center;
	private static ArrayList<Integer> monsterList;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//set center
		center = new int[2];
		center[0] = n/2;
		center[1] = n/2;
		
		//set map
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//simulation Start
		monsterList = new ArrayList<>();
		setMonster(n);
		
//		System.out.println(monsterList);
		int totalPoint = 0;
		
		for (int i = 0; i < m; i++) {
			//set attack var
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			//1. Attack (이동까지)
			totalPoint += attackMonster(n, d, p);
//			System.out.println(monsterList + " " + totalPoint);
			
			//3. 중복 몬스터 삭제
			totalPoint += deleteMonstersWith4();
//			System.out.println(monsterList + " " + totalPoint);
			
			//4. 삭제 끝난 후 다시 나열
			changeMonsterList(n);
			
//			System.out.println(monsterList + " " + totalPoint);
//			System.out.println(totalPoint + "=========================");
		}
		
		System.out.println(totalPoint);
	}
	
	private static void changeMonsterList(int n) {
		ArrayList<Integer> tempList = new ArrayList<>();
		int repCnt = 0; //반복 횟수
		int cur = monsterList.get(0); //현재 몬스터 종류
		
		int size = monsterList.size();
		
		int tsize = 0;
		for (int i=0; i<size; i++) {
			if(cur == monsterList.get(i)) {
				repCnt++;
			} else {
				tempList.add(repCnt);
				tempList.add(cur);
				tsize+=2;
				repCnt = 1;
				cur = monsterList.get(i);
			}
			
			//격자 판 넘어가면 
			if(tsize >= n*n) {
				break;
			}
		}
		
		//마지막 구간 저장.
		tempList.add(repCnt);
		tempList.add(cur);
		
		monsterList = tempList;
	}

	private static int deleteMonstersWith4() {
		int totalPoint = 0;
		
		while(true) {
			int start = 0; //반복구간 시작되는 위치
			int repCnt = 0; //반복 횟수
			int cur = 0; //현재 몬스터 종류
			int point = 0; //삭제 후 점수 저장
			
			//4번 이상 반복되는 몬스터들 동시 삭제,
			int size = monsterList.size();
			for (int i=0; i<size; i++) {
				if(cur == monsterList.get(i)) {
					repCnt++;
				} else {
					if(repCnt > 3) {
						point += deleteRepeatedMonsters(start, repCnt);
						size -= repCnt;
						i -= repCnt;
						start -= repCnt;
					}
					start = i;
					repCnt = 1;
					cur = monsterList.get(i);
				}
			}
			
			//마지막 계산
			if(repCnt > 3) {
				point += deleteRepeatedMonsters(start, repCnt);
			}
			
			// point가 0인 경우는 반복되는 구간이 없어서 끝남
			if(point == 0) {
				break;
			}
			
			totalPoint += point;
		}
		
		return totalPoint;
	}

	private static int deleteRepeatedMonsters(int start, int repCnt) {
		int point = 0;
		for (int i = 0; i <repCnt; i++) {
			point += monsterList.remove(start);
		}
		
		return point;
	}

	
	private static int attackMonster(int n, int d, int p) {
		final int[] RDLU = {4,2,0,6}; //방향에 따라 증가도 다름
		int mid = n/2; //p 최대값은 n/2이므로 
		int[] seq = new int[mid];
		
		//상 = 6, 21  44, 75
		//우 = 4, 17  38, 67
		//하 = 2, 13, 32, 59
		//좌 = 0, 9, 26, 51
		
		//0, 9, 17, 25, 33
		// 9  8   8   8
		
		// => 0,9, .. +8 씩 증가
		seq[0] = 0;
		seq[1] = 9;
		
		for (int i = 2; i < mid; i++) {
			seq[i] = seq[i-1]+8;
		}
		
		int attackLoc = 0;
		int point = 0;
		for (int i = 0; i < p; i++) {
			attackLoc += seq[i]+RDLU[d];
			
			if(monsterList.size() < attackLoc) break;
			point += monsterList.remove(attackLoc);
			attackLoc--;
		}
		
		return point;
	}

	//중앙 왼,하,우,상 회전
	private static void setMonster(int n) {
		int curr = center[0];
		int curc = center[1]; //왼쪽 시작
		
		int dir = 2; //2103순서  +3 %4
		
		int[][] visited = new int[n][n];
		visited[curr][curc] = -1;
		
		// 다음이 벽이고 다음 방향이 이미 방문했던 곳이면 멈춤. 다음이 0이거나 범위 탈출하 면 멈춤
        for(int i=0; i < n*2; i++) {
        	for(int j=0; j<=i/2; j++) {
                curr += dr[dir];
                curc += dc[dir];

                if(!isRange(n, curr, curc) || !isMonster(curr,curc)) return;

                monsterList.add(map[curr][curc]);
            }
            
            dir = (dir+3) % 4;
        }
		
		
//		int cnt = 1;
//		while(true) {
//			for(int i=0; i<cnt; i++) {
//				curr += dr[dir];
//				curc += dc[dir];
//				
//				if(!isRange(n,curr,curc) || !isMonster(curr,curc)) return;
//				
//				monsterList.add(map[curr][curc]);
//			}
//			dir = (dir+3) % 4;
//			
//			cnt++;
//			if(cnt > n) break;
//			
//			for(int i=0; i<cnt-1; i++) {
//				curr += dr[dir];
//				curc += dc[dir];
//				
//				if(isRange(n,curr,curc) && !isMonster(curr,curc)) return;
//				
//				monsterList.add(map[curr][curc]);
//			}
//			dir = (dir+3) % 4;
//		}
	}

	private static boolean isMonster(int curr, int curc) {
		return map[curr][curc] > 0;
	}

	private static boolean isRange(int n, int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}

}