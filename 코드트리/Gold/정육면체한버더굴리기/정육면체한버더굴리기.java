import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 
 * n : 배열크기 (홀수만 입력, 3<=n<=29)
 * arr : 배열 숫자 (1<=10)
 * 
 * 1. 구역 나누기 (각 구역마다 칸 수 저장)
 * 2. 나눠진 구역을 바탕으로 변의 수 저장
 * 3. 합 계산
 * 4. 십자모양 반시계로 돌리기 90
 * 5. 4개의 구역 시계방향으로 돌리기 90
 * 
 * 1부터 다시 반복
 */
public class Main {
	private static int[][] picture;
	private static int[][] visited;
	
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		picture = new int[n][n];
		visited = new int[n][n];
		// setting Picture
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				picture[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//printArr(n, picture);
		int answer = 0;
		// start Simulation
		for(int t=0; t<4; t++) { //총 3회
			
			// 1.구역 나누기 (각 구역마다 칸 수 저장, DFS)
			ArrayList<int[]> groupInfo = getGroup(n);
			int groupCount = groupInfo.size();
			//printArr(n, visited);
			
			// 2. 나눠진 구역을 바탕으로 변의 수 저장
			int[][] adjGroup = new int[groupCount][groupCount]; //겹쳐진 구역들 저장
			getAdjGroup(n, groupCount, adjGroup);
			
			// 3. 합 계산
			answer += calAdjGroup(groupInfo, groupCount, adjGroup);
			
			// 4. 십자모양 반시계로 돌리기 90
			int[][] newArr = new int[n][n];
			
			rotateCrossArr(n, newArr); //반시계
			//printArr(n, newArr);
			
			// 5. 4개의 구역 시계방향으로 돌리기 90
			rotateGroupArr(n, newArr);
			
			//printArr(groupCount, numsVisited);
			//printArr(n, newArr);
			
			picture = newArr;
			
			resetVisitedArr(n);
		}
	
		System.out.println(answer);
	}

	//구역의 시작위치를 구한 후 회전
	private static void rotateGroupArr(int n, int[][] newArr) {
		int mid = n/2;
		
		//r = 2미만일 때  0, 2이상일 때 mid+1, 짝수일때 0
		//c = 짝수일때 0, 홀수일때 3
		
		for(int i=0; i<4; i++) { //가운데를 제외한 4개의 구역 돌리기
			int r = i < 2 ? 0 : mid+1;
			int c = i%2==0 ? 0 : mid+1;
			
			rotate(mid, r, c, newArr);
		}
	}
	
	//시계방향 회전 하는 로직
	private static void rotate(int mid, int r, int c, int[][] newArr) {
		for (int i = 0; i < mid; i++) {
			for (int j = 0; j < mid; j++) {
				newArr[r+j][c+mid-i-1] = picture[r+i][c+j];
				// 초기값 에서 방향만 바꾸기
				//00 -> 01
				//01 -> 10
				
				// 33 -> 34  : 3->4  0->1
				// 34 -> 44  : 3->4
				// 44 -> 43  : 4->3
				// r이 0이면  c가 3
				
				// 1 -> 2
				// 2 -> 1
				// 3 -> 0
				
				// 5 -> 8 n = 9 // mid+5
				// 6 -> 7
				// 7 -> 6
				// 8 -> 5
			}
		}
	}
	
	//중앙 십자 모양 회전
	private static void rotateCrossArr(int n, int[][] newArr) {
		int mid = n/2;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i!=mid && j!=mid) continue;

				newArr[n-j-1][i] = picture[i][j]; //2,4
			}
		}
	}
	
	/**
	 * 인접그룹을 통해 조화로운 값 구하기
	 * @param groupInfo //그룹의 번호와 개수를 저장한 배열
	 * @param groupCount // 그룹 개수
	 * @param adjGroup // 인접한 그룹의 정보
	 * @return 조화로움 결과
	 */
	private static int calAdjGroup(ArrayList<int[]> groupInfo, int groupCount, int[][] adjGroup) {
		int cal = 0;
		for(int i=0; i<groupCount; i++) {
			for(int j=i+1; j<groupCount; j++) {
				if(adjGroup[i][j] == 0) continue;
				
				int[] cur = groupInfo.get(i);
				int[] target = groupInfo.get(j);
				
				cal += calculate(adjGroup[i][j], cur, target); //계산
			}
		}
		
		return cal;
	}
	
	//계산식
	private static int calculate(int adjCount, int[] cur, int[] target) {
		return (cur[1]+target[1])*cur[0]*target[0]*adjCount;
	}
	
	//인접한 그룹 찾기
	private static void getAdjGroup(int n, int groupCount, int[][] numsVisited) {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];

					if(!isRange(n, nr, nc) || visited[i][j] == visited[nr][nc]) continue;
					
					numsVisited[visited[i][j]-1][visited[nr][nc]-1]++;
				}
			}
		}
	}
	
	//방문배열 초기화
	private static void resetVisitedArr(int n) {
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], 0);
		}
	}
	
	//그룹 정보를 구하고 저장하기
	private static ArrayList<int[]> getGroup(int n) {
		int groupCount = 0;
		int cnt = 0;
		ArrayList<int[]> groupInfo = new ArrayList<>();
		for(int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j] != 0) continue;
				
				visited[i][j] = ++groupCount;
				groupInfo.add(new int[2]);
				cnt = dividePictureUsingDFS(0, n, new CurInfo(i, j, picture[i][j]), groupCount) + 1;
				
				groupInfo.get(groupCount-1)[0] = picture[i][j]; //현재 그룹의 숫자
				groupInfo.get(groupCount-1)[1] = cnt; //현재 그룹의 개수

			}
		}
		
		return groupInfo;
	}
	
	//그룹별 로 나누기
	private static int dividePictureUsingDFS(int cnt, int n, CurInfo curInfo, int groupCount) {
		for(int i=0; i<4; i++) {
			int nr = curInfo.r + dr[i];
			int nc = curInfo.c + dc[i];

			if(!isRange(n, nr, nc) || picture[nr][nc] != curInfo.num 
					|| visited[nr][nc] != 0) continue;
			
			visited[nr][nc] = groupCount;
			curInfo.r = nr;
			curInfo.c = nc;
			cnt = dividePictureUsingDFS(cnt+1, n, curInfo, groupCount);
			
			curInfo.r = nr-dr[i];
			curInfo.c = nc-dc[i];
		}
		
		return cnt;
	}
	
	//범위확인
	private static boolean isRange(int n, int nr, int nc) {
		return nr>=0 && nr<n && nc>=0 && nc<n;
	}
	
	//현재 위치와 그룹 번호를 위한 class
	public static class CurInfo {
		int r, c, num;
		
		public CurInfo(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	private static void printArr(int n, int[][] arr) {
		for (int j = 0; j < n; j++) {
			System.out.println(Arrays.toString(arr[j]));
		}
		
		System.out.println("");
	}
}
