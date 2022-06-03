import java.io.*;
import java.util.*;
/*  빵집  3109 60
 * 
 *  DFS로 탐색, 대각선 위, 오른쪽, 아래 순으로 탐색하는 방법으로 구현하면됨(최대횟수 구할려면)
 *   -> 아래로 탐색하면 나머지 첫째 열들이 막힐 수 있기 때문에 최대로 구할 수 가 없음
 */
class Baekjoon3109 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, K, ans=0;
	static boolean chk = false;
	static char[][] map;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1};
	

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        st = new StringTokenizer(br.readLine());  
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][K];
        
        for(int i=0; i<N; i++) {
        	String str = br.readLine();
        	for(int j=0; j<K; j++) {
        		map[i][j] = str.charAt(j);
        	}
        }
        v = new boolean[N][K];
        for(int i=0; i<N; i++) {
        	chk = false;
        	DFS(i, 0);
        }
        
        bw.write(ans+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void DFS(int x, int y) {
		if(y==K-1) { //마지막 열까지 오면 +1, 나머지 경우는 구할필요 없음.
			chk = true;
			ans++;
			return;
		}

		for(int i:dx) {
			int nx = x+i;
			if(nx<0||nx>=N||y+1>=K||v[nx][y+1]||map[nx][y+1]=='x') continue;
			
			v[nx][y+1] = true;
			DFS(nx, y+1);
			if(chk) return;
		}
	}
}