import java.io.*;
import java.util.*;

/*  단지번호붙이기 2667
 *  
 *  DFS 방문하지 않고 1이 연속 될 때마다 카운트
 *  
 *  1. 처음 1이 나오는 곳으로 실행. DFS가 실행 될 때마다 집 개수+1 -> DFS가 실행 된다는것은 1로 이어지는 길이 있다는 뜻임.
 *  2. 전부 DFS가 실행되어 빠져 나오면 다음 방문이므로 단지 개수+1
 *  
 *  1~2 를 배열 끝날 때 까지 반복
 *  
 *  //결과를 오름차순으로 정렬
 */
class Baekjoon2667 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[][] A; //처음 주어진 배열
	static boolean[][] visited; //방문한배열 체크
	static int[] res; //결과 값 저장된 배열
	static ArrayList<Integer> res2; //결과 값 저장된 배열
	static int cnt;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());

        A = new int[N][N];
        visited = new boolean[N][N];
        res2 = new ArrayList<Integer>();
        
        for(int i=0; i<N; i++) {
        	String str = bf.readLine();
        	for(int j=0; j<N; j++) {
        		A[i][j]=str.charAt(j)-48;
        	}
        }
//        int cnt = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(A[i][j]>=1 && !visited[i][j]) {
        			cnt=0;
        			dfs(i, j, visited);
        			res2.add(cnt);
        		}
        	}
        }
        System.out.println(res2.size());
        Collections.sort(res2);
        for(int i=0; i<res2.size(); i++) {
        	System.out.println(res2.get(i));
        }
        
    }
	private static void dfs(int x, int y, boolean[][] v) {
		cnt++;
		if(v[x][y]) return;
		v[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx>=0&&nx<N&&ny>=0&&ny<N&&!v[nx][ny]) {
				if(A[nx][ny]==1) {
					dfs(nx, ny, v);	
				} 
			}
		}
	}
}