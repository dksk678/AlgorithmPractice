import java.io.*;
import java.util.*;
/*  파이프 옮기기 1 17070  70
 * 
 *  1. 대각선은 3방향 다 실행, 가로면 가로,대각만 실행, 세로면 세로,대각만 실행
 *  2. 대각선으로 갈 때는 우,하,대각선 모두 1이 없어야함.
 */
class Baekjoon17070
{	
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(arr[N-1][N-1]==1) {
			bw.write(0+"");
		} else {
			bw.write(BFS(N, arr)+"");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static int BFS(int N, int[][] arr) {
		Queue<Pipe> q = new LinkedList<>();
		int cnt, d, x, y, nx, ny;
		cnt = 0;

		q.offer(new Pipe(0, 0, 1));
		
		while(!q.isEmpty()) {
			d = q.peek().d;
			x = q.peek().x;
			y = q.poll().y;
			//대각선
			
			for(int i=0; i<3; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				//가로일때 세로, 세로일 때 가로 X 
				if((d==0&&i==1) || (d==1&&i==0)) continue; 

				if(nx>=N||ny>=N||arr[nx][ny]==1) continue;
				// 대각선일 때 우,하,대각선에 1없어야함
				if(i==2&&(arr[nx][y]==1||arr[x][ny]==1)) continue;
				
				if(nx==N-1&&ny==N-1) { cnt++; continue; } //반례 다 통과되고 도착점이면 카운트
				q.offer(new Pipe(i, nx, ny));
			}
		}
		return cnt;
	}
	static class Pipe {
		int d; //방향 0 가로, 1 세로, 2 대각선
		int x;
		int y;
		public Pipe(int d, int x, int y) {
			this.d = d;
			this.x = x;
			this.y = y;
		}
	}
} 
