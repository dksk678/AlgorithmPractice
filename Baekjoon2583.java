import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*   영역 구하기 2583 40
 * 
 *   왼쪽 아래 부터 오른쪽 위까지.
 *   xy좌표 이므로 x가 가로 y가 세로
 */

public class Baekjoon2583 {
	static int N, M, K;
	static boolean[][] arr;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new boolean[N][M];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sc = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			for(int j=sr; j<er; j++) {
				for(int k=sc; k<ec; k++) {
					arr[j][k] = true;
				}
			}
		}
		
		int cnt = 0;
		ArrayList<Integer> area = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]) continue;
				cnt++;
				area.add(BFS(i, j));
			}
		}
		Collections.sort(area);
		
		for(int i:area) {
			sb.append(i+" ");
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	static int BFS(int r, int c) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(r, c));
		int cnt = 1;
		arr[r][c] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]) continue;
				
				arr[nr][nc] = true;
				cnt++;
				q.add(new Node(nr, nc));
			}
		}
		return cnt;
	}
	
	static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void print(boolean[][] arr) {
		for(boolean[] i:arr) {
			for(boolean j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
}
