import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 150
 * 꼬리잡기놀이.
 * 맨 앞사림이 머리사람, 맨 뒤 사람이 꼬리사람
 * 
 * 1. 꼬리사람, 머리 사람 구하기 (1=머리, 2=일반, 3=꼬리)
 * 2. 공 던지기
 *  - 라운드 0~n=좌(0)>우(n) 공 던지기, n~2n = 하>상, 2n~3n = 우(n)>좌(0), 3n~4n = 상(n)>하(0)
 *  
 * 3. 점수 얻기
 *  - 가장 처음 맞은 사람의 점수, k번째 사람인 경우 k^2만큼 점수 획득
 *  - 점수를 획득하면 머리랑 꼬리 바뀜
 * 
 *  저장해야할 것. 머리, 꼬리, 순서
 *  //map에 머리를 1로 두고 나머진 +1
 *  //순서가 바뀌면 해당 그룹만 순서 반대로 바꿔주면 됨
 *  
 */
public class Main_꼬리잡기놀이 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	private static int[][] head;
	
	private static int[][] map1; //주어진 맵 저장
	private static Person[][] personMap; //
	private static int[][] lineMap; //이동 가능 경로 //붙어있는 경우가 없으므로 그냥 저장하면 됨
	private static int n, m, k, totalPoint;
	
	private static class Person {
		int num, gnum;

		public Person(int num, int gnum) {
			super();
			this.num = num;
			this.gnum = gnum;
		}

		@Override
		public String toString() {
			return num+"  "+gnum;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int i = 0; i < k; i++) {
			simulation(i);
		}
		
		System.out.println(totalPoint);
	}


	private static void simulation(int k) {
//		printMap2(head);
//		printMap(personMap);
		//사람 이동
		movePerson();
//		printMap(personMap);
		
		//공 던지기 , return = 공 맞은 사람의 그룹 번호
		int gnum = throwBall(k);
		
		//공 맞은 그룹 반대로전환
		if(gnum == -1) return;
		
		reversePerson(gnum);
//		printMap(personMap);
	}


	private static void reversePerson(int gnum) {
		boolean[][] vistied = new boolean[n][n];
		int r = head[gnum][0];
		int c = head[gnum][1];
		int size = head[gnum][2];
		
		personMap[r][c].num = size;
				
		for (int i = 1; i < size; i++) {
			for (int k = 0; k < 4; k++) {
				int nr = r+dr[k];
				int nc = c+dc[k];
				
				//이동 동선이고, 갈 수 있는 구간이면 이동하기
				if(!isRange(nr, nc) || personMap[nr][nc] == null) continue;
				if(vistied[nr][nc]) continue;
				
				if(personMap[nr][nc].num == size) {
					head[gnum][0] = nr;
					head[gnum][1] = nc;
				}
				
				personMap[nr][nc].num = size - personMap[nr][nc].num + 1;
				vistied[r][c] = true;
				r = nr;
				c = nc;
			}
		}
	}

	//라운드 0~n=좌(0)>우(n) 공 던지기, n~2n = 하>상, 2n~3n = 우(n)>좌(0), 3n~4n = 상(n)>하(0)
	private static int throwBall(int k) {
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		
		int round = k % (4*n); //0~28
		int dir = round/n; //0~4
		int start = round % n;
		
		int r = 0;
		int c = 0;
		
		if(dir==0) {
			r = start;
			c = 0;
		} else if(dir==1) {
			r = n-1;
			c = start;
		} else if(dir==2) {
			r = n-1-start;
			c = n-1;
		} else if(dir==3){
			r = 0;
			c = n-1-start;
		}
		
		for (int i = 0; i < n; i++) {
			if(personMap[r][c] != null) {
				totalPoint += (int) Math.pow(personMap[r][c].num, 2);
				
				return personMap[r][c].gnum;
			}
			r += dr[dir];
			c += dc[dir];
		}
		
		return -1;
	}


	private static void movePerson() {
		boolean[][] vistied = new boolean[n][n];
		
		for (int i = 0; i < m; i++) {
			int r = head[i][0];
			int c = head[i][1];
			int size = head[i][2];
			
			move(i, r, c, size, vistied);
		}
	}


	private static void move(int num, int r, int c, int size, boolean[][] vistied) {
		int nr = r;
		int nc = c;
		// 미는 방향 구하기
		for (int k = 0; k < 4; k++) {
			nr = r+dr[k];
			nc = c+dc[k];
			
			if(!isRange(nr, nc) || lineMap[nr][nc] == 0 || 
					(personMap[nr][nc] != null && personMap[nr][nc].num == 2)) continue;
			
			head[num][0] = nr;
			head[num][1] = nc;
			break;
		}
		Person temp = personMap[nr][nc];
		vistied[nr][nc] = true;
		personMap[nr][nc] = personMap[r][c];

		//사람들 이동
		for (int i = 1; i < size; i++) {
			for (int k = 0; k < 4; k++) {
				nr = r+dr[k];
				nc = c+dc[k];
				
				//이동 동선이고, 갈 수 있는 구간이면 이동하기
				if(!isRange(nr, nc) || lineMap[nr][nc] == 0 || personMap[nr][nc] == null) continue;
				if(vistied[nr][nc]) continue;
				
				personMap[r][c] = personMap[nr][nc];
				vistied[r][c] = true;
				r = nr;
				c = nc;
			}
		}
		
		personMap[r][c] = temp; //마지막 자리는 머리가 대체한 자리로
	}


	private static void init() throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = toInt(st.nextToken()); 
		m = toInt(st.nextToken()); //team 개수
		k = toInt(st.nextToken()); //round 수
		totalPoint = 0;
		
		map1 = new int[n][n];
		personMap = new Person[n][n];
		lineMap = new int[n][n];
		head = new int[m][3]; //좌표 + 그룹 크기
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map1[i][j] = toInt(st.nextToken());
				
				if(map1[i][j] > 0) {
					lineMap[i][j] = 4;					
				}
			}
		}
		
		setPersonMap();
	}

	private static void setPersonMap() {
		boolean[][] vistied = new boolean[n][n];
		int gcnt = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map1[i][j] == 0 || map1[i][j] == 4)  continue;
					
				if(map1[i][j] == 1) {
					personMap[i][j] = new Person(1, gcnt);
					head[gcnt][0] = i;
					head[gcnt][1] = j;
					
					vistied[i][j] = true;
					int cnt = 1;
					int r = i;
					int c = j;
					
					vistied[i][j] = true;
					
					boolean isEnd = false;
					
					while(!isEnd) {
						isEnd = true;
						for (int k = 0; k < 4; k++) {
							int nr = r+dr[k];
							int nc = c+dc[k];
							
							if(!isRange(nr, nc) || vistied[nr][nc]) continue;
							
							if(map1[nr][nc]==2) {
								personMap[nr][nc] = new Person(++cnt, gcnt);
								vistied[nr][nc] = true;
								r = nr;
								c = nc;
								isEnd = false;
								break;
							}
						}
					}
					
					for (int k = 0; k < 4; k++) {
						int nr = r+dr[k];
						int nc = c+dc[k];
						
						if(!isRange(nr, nc) || vistied[nr][nc]) continue;
						
						if(map1[nr][nc]==3) {
							personMap[nr][nc] = new Person(++cnt, gcnt);
							head[gcnt++][2] = cnt;
						}
					}
				}
			}
		}
	}

	private static boolean isRange(int nr, int nc) {
		return 0<=nr && nr<n && 0<=nc && nc<n;
	}

	private static int toInt(String str) {
		return Integer.parseInt(str);
	}
	
	private static void printMap(Person[][] map) {
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("");
	}
	
	private static void printMap2(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("");
	}
}
