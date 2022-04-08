import java.io.*;
import java.util.*;

/*  적록색약  10026
 * 	
 * 	RGB 그림이 있음
 * 
 *  구역의 개수를 구하기.
 *  적록색약있는 사람(RG,B)과 아닌 사람(R,G,B)의 구역의 차이.
 *  
 *  -> RG를 동일한 값으로 저장
 */

class Baekjoon10026 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
	static int cnt;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static Queue<Color> q = new LinkedList<Color>();
	
	static boolean[][] v;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
//        M = Integer.parseInt(st.nextToken());
        
        char[][] arr = new char[N][N];
        char[][] arr2 = new char[N][N]; //적록색약
        
        
        for(int i=0; i<N; i++) {
        	String str = bf.readLine();
        	for(int j=0; j<N; j++) {
        		char t = str.charAt(j);
            	arr[i][j] = t;
            	
            	if(t=='R'||t=='G')arr2[i][j] = 'R'; //적록색약있는 사람은 r,g둘다 r로 저장.
            }
        }

        v = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(v[i][j]) continue;
        		q.add(new Color(i, j, arr[i][j])); //아닌 사람
        		BFS(arr);
        		cnt++;
            }
        }
        sb.append(cnt);
        
        cnt = 0;
        v = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(v[i][j]) continue;
        		q.add(new Color(i, j, arr2[i][j])); //아닌 사람
        		BFS(arr2);
        		cnt++;
            }
        }

        System.out.println(sb+" "+cnt);
    }
	
//	private static void DFS(int i) {
//		if (v[i]) {return; }
//		v[i] = true;
//		for(int l: arr.get(i)) { //l
//			if(v[l]==true) continue;
//			DFS(l);
//		}
//	}
	
	private static class Color {
		private int x;
		private int y;
		private char c;
		
		public Color(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	
	private static void BFS(char[][] arr) {
		int nx;
		int ny;
		while(!q.isEmpty()) {
			int qx = q.peek().x;
			int qy = q.peek().y;
			char qc = q.poll().c;
        	for(int k=0; k<4; k++) {
        		nx = qx + dx[k];
        		ny = qy + dy[k];
        		if(nx>=0&&nx<N&&ny>=0&&ny<N&&arr[nx][ny]==qc&&!v[nx][ny]) {
        			v[nx][ny] = true;
        			
        			q.add(new Color(nx, ny, qc));
        		}
	        }
		}
	}
}