import java.io.*;
import java.util.*;

/*  연구소  14502  90
 *  
 *  2는 바이러스 1은 벽, 0이 안전지역
 *  
 *  1. 0인곳에 벽 3개를 세움
 *  2. 바이러스 퍼짐(BFS) 
 *  3. 퍼진 후 안전영역 크기 구하기.
 */

class Baekjoon14502 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	
	static int[][] arr; //초기 맵
	static Queue<int[]> q;
	static int[] dx = {0,1,0,-1}; //우 하 좌 상
	static int[] dy = {1,0,-1,0};
	
	static ArrayList<int[]> virus; //초기 바이러스 있는 위치
	static int max = 0; //결과
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        virus = new ArrayList<int[]>();
        q = new LinkedList<int[]>();
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		if(num==2) virus.add(new int[] {i,j}); //초기 바이러스 위치 저장
        		arr[i][j] = num;
        	}
        }

        buildWall(arr, 0, 0, 0);

        System.out.println(max);
	}

	static void BFS(int[][] arr2) { //virus 퍼짐
		int x, y, nx, ny;
		
		for(int[] a: virus) {
			q.offer(new int[] {a[0],a[1]});
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
		int[][] tmparr = new int[N][M]; //벽 3개를 생성한 임시 맵
		for(int i=0; i<N; i++) {
			System.arraycopy(arr2[i], 0, tmparr[i], 0, M); 
        }
		
		if(wallcnt==3) { //벽 3개 생성되면
			BFS(tmparr); //바이러스 퍼짐
			return;
		}
		for(int i=a; i<N; i++) {
			for(int j=b; j<M; j++) {
				if(tmparr[i][j]==0) {
					tmparr[i][j] = 1;
					buildWall(tmparr, wallcnt+1, i, j); //중복제거
					tmparr[i][j] = 0;
				}
			}
			b = 0; //중복제거하기 위해  필요한 b변수 초기화
		}
	}
	
	static void findSafeZone(int[][] arr2) { //안전구역 영역 확인
		int tmpmax = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr2[i][j]==0) tmpmax++;
			}
		}
		if(max<tmpmax) { 
			max = tmpmax;
		}
	}
}