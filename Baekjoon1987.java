import java.io.*;
import java.util.*;
/*   알파벳  1987  G4  60
 * 
 *   DFS 한 방향 씩 탐색하면서 cnt 쌓아감. 계속 갈 수 있으면 cnt+1
 *   탐색한 언어면 coninue
 *   - String.contains -> O(n) 시간초과
 *     set.contains ->  O(1) 시간단축
 */
class Baekjoon1987 {
//	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] arr; 
	static int ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        ans = 1;
        for(int i=0; i<N; i++) {
        	String str = br.readLine();
        	for(int j=0; j<M; j++) {
        		arr[i][j] = str.charAt(j);
        	}
        }

        Set<Character> set = new HashSet<>();
        set.add(arr[0][0]);
        DFS(0, 0, 1,  set);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void DFS(int x, int y, int cnt, Set<Character> set) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>=N||ny<0||ny>=M||set.contains(arr[nx][ny])) continue;

			set.add(arr[nx][ny]);
			ans = ans<cnt+1?cnt+1:ans;
			DFS(nx, ny, cnt+1, set);
			set.remove(arr[nx][ny]);
		}
	}
}