import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/* 미네랄 G2 180~
 * 홀수번 째는 ->  dr
 * 짝수번 째는 <-
 * 
 * R이 낮은 순서 즉 밑에 있는 순서대로 q에 담기
 * q찾을 때  R-1에 도달하면 chk
 * 바닥에 도착 안했으면 떨어져야 하는 클러스터임
 * -> 중간에 다른 클러스터 위에 떨어지거나 바닥에 떨어지는 경우 중 더 짧은 경우를 구해서 그만큼 떨어트림
 * 한번 떨어지면 다음 창 던지기로 넘어감
 * 
 * -> 더 좋은 방법: 바닥 부분만 BFS돌려서 방문체크. 방문 체크 안된 것들은 떨어지는 클러스터임.
 */
public class Main_2933_미네랄_G2 {
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			if(i%2==1) {
				//창 , 시작 위치(왼, 오), 세로 길이, 가로 길이, map
				throwS(Integer.parseInt(st.nextToken()),  1, R, C);
			} else {
				throwS(Integer.parseInt(st.nextToken()), -1, R, C);
			}
		}
		
		for (char[] c1:map) {
			for (char c2:c1) {
				sb.append(c2);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void throwS(int r, int d, int R, int C) {
		int nc = d==1?0:C-1; //창 던지는 위치(왼, 오)
		for (int i = 0; i < C; i++) {
			if(map[R-r][nc]=='x') {
				map[R-r][nc] = '.';
				//창 맞으면 떨어지는 지 확인
				drop(R, C);
				
				break;
			} 
			nc += d;
		}
	}
	
	static class loc implements Comparable<loc>{
		int r, c;

		public loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(loc o) {
			// TODO Auto-generated method stub
			return o.r-this.r;
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	private static void drop(int R, int C) {
		PriorityQueue<loc> q = new PriorityQueue<>(); //클러스터구할 queue
		PriorityQueue<loc> cluster = new PriorityQueue<>(); //클러스터 저장
		int[][] v = new int[R][C];
		boolean chkb = false;
		int cnt = 1;
		
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++) {
				if(v[i][j]>=1 || map[i][j]=='.') continue;
				
				//클러스터 구하기
				v[i][j] = cnt;
				q.offer(new loc(i,j));
				cluster.offer(new loc(i, j));
				
				while(!q.isEmpty()) {
					int r = q.peek().r;
					int c = q.poll().c;
					
					if(r==R-1) chkb = true; //바닥이랑 붙어있으면 안떨어짐
					
					for (int k = 0; k < 4; k++) {
						int nr = r+dr[k];
						int nc = c+dc[k];
						
						if(nr<0||nr>=R||nc<0||nc>=C||v[nr][nc]>=1||map[nr][nc]=='.') continue;
						
						v[nr][nc] = cnt;
						q.offer(new loc(nr,nc));
						cluster.offer(new loc(nr,nc));
					}
				}
				
				if(!chkb) { //바닥과 이어져 있지 않으면 떨어진다.
					int mindif = R-cluster.peek().r-1;
					for (loc l:cluster) {
						int r = l.r;
						int c = l.c;
						
						for (int k = 2; k <= mindif; k++) {
							if(r+k>R) break;
							if(r+k<R && v[r+k][c]==cnt) break;  //밑에 x가 같은 클러스터면 건너 뜀
							
							if(r+k==R || map[r+k][c]=='x') {
								mindif = Math.min(mindif, k-1);
								break;
							}
						}
					}
					
					//저장되어있는 클러스터 값 변경 해주기
					while(!cluster.isEmpty()) {
						loc cur = cluster.poll();
						map[cur.r][cur.c] = '.';
						map[cur.r+mindif][cur.c] = 'x';
					}
					
					return; //클러스터 1개가 떨어지면 바로 끝
				}
				
				cluster.clear(); //다음 클러스터를 위해 초기화
				chkb = false;
				cnt++;
			}
		}
	}

}
