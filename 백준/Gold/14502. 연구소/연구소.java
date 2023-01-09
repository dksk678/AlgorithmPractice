import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] arr; //초기 맵
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static ArrayList<int[]> virus; //초기 바이러스 있는 위치
	static int max = 0; //결과
	static int[][] wall;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        virus = new ArrayList<int[]>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		if(num==2) virus.add(new int[] {i,j}); //초기 바이러스 위치 저장
        		arr[i][j] = num;
        	}
        }
        
        wall = new int[3][2];
        buildWall(arr, 0, 0, 0);

        System.out.println(max);
	}

	static void BFS(int[][] arr) { //virus 퍼짐
		int x, y, nx, ny;
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int[] a: virus) {
			q.offer(new int[] {a[0],a[1]});
		}
		
		int[][] arr2 = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(arr[i], 0, arr2[i], 0, M);
		}
		for (int i = 0; i < 3; i++) {
			arr2[wall[i][0]][wall[i][1]] = 1;
		}

		while(!q.isEmpty()) {
			x = q.peek()[0];
			y = q.poll()[1];
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>=N||ny>=M||arr2[nx][ny]!=0) continue;
				
				q.offer(new int[]{nx, ny});
				arr2[nx][ny] = 2;
			}
		}
		
		findSafeZone(arr2);
	}
	
	static void buildWall(int[][] arr2, int wallcnt, int a, int b) { // 벽 3개 생성
		if(wallcnt==3) { //벽 3개 생성되면
			BFS(arr2); //바이러스 퍼짐
			return;
		}
		
		for(int i=a; i<N; i++) {
			for(int j=b; j<M; j++) {
				if(arr2[i][j]==0) {
					wall[wallcnt][0] = i;
					wall[wallcnt][1] = j;
					buildWall(arr2, wallcnt+1, i, j+1);
				}
			}
			b = 0; //a, 0부터 다시 탐색
		}
	}
	
	static void findSafeZone(int[][] arr2) { //안전구역 영역 확인
		int tmpmax = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr2[i][j]==0) tmpmax++;
			}
		}
		max = max<tmpmax?tmpmax:max;
	}
}