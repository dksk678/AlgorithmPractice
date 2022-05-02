import java.io.*;
import java.util.*;

/*  벽 부수고 이동하기 2206  120
 *  DFS
 *  
 *  1.벽부셨으면 벽 부신 노드 chk
 *  2.chk가 되어있고 벽을 만나면 continue;
 *  3.chk안되어있으면 계속 진행
 *  
 *  **벽을 깨고 방문한곳인지, 아닌지 방문체크를 나눠서 해야함.
 */
class node {
    int row;
    int col;
    boolean brk;
    public node(int n, int m, boolean brk) {
        this.row = n;
        this.col = m;
        this.brk = brk;
    }
}
class Baekjoon2206 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static int[][] arr;
	static int[][] arr2;
	static int N;
	static int M;
	static boolean[][][] visited;
	private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = { 0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        arr2 = new int[N][M];

        visited = new boolean[2][N][M]; //방분한곳 체크
        
        for(int i=0; i<N; i++) {
        	String st2 = bf.readLine();
        	for(int j=0; j<M; j++) {
        		arr[i][j] = st2.charAt(j)-48;
            }
        }
        bfs();

        System.out.println(arr2[N-1][M-1]==0?-1:(arr2[N-1][M-1]));
    }	

	private static void bfs() {
		Queue<node> q = new LinkedList<>();
		int n_r = 0;
		int n_c = 0;
		int br=0;
		q.offer(new node(0, 0, false));
		visited[0][0][0]=true;
		visited[1][0][0]=true;
		arr2[0][0] = 1;
		while(!q.isEmpty()) {
			node cur_node = q.poll();
			
			if((cur_node.row == N-1) && (cur_node.col == M-1)) break;

			for(int i=0; i<4; i++) {
				n_r = cur_node.row+dx[i];
				n_c = cur_node.col+dy[i];
				if(cur_node.brk) br = 1;  //벽을 깨고 방문한 경우 1에 저장
				else br = 0; //깨지 않고 방문한경우 0에 저장
				
				if(n_r<0||n_r>=N||n_c>=M||n_c<0||visited[br][n_r][n_c]||(arr[n_r][n_c]==1&&cur_node.brk)) {//벽인데 벽깬적있으면 continue
					continue;
				}

				visited[br][n_r][n_c]=true;
				if(arr[n_r][n_c]==1||cur_node.brk) q.offer(new node(n_r, n_c, true)); //벽깬 후 이동은 전부 true
				else q.offer(new node(n_r, n_c, false)); //벽깬적없으면 false
				arr2[n_r][n_c] = arr2[cur_node.row][cur_node.col]+1; //최단거리 저장
			}
		}
	}
}