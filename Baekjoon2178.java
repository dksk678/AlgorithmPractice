import java.io.*;
import java.util.*;

/*  DFS와 BFS  2178  18:40
 *  DFS = stack
 *  bfs = queue
 *  
 *  
 */
class node { //배열로 했는데 깊은복사 안해서 에러뜸 귀찮아서 클래스배열로만듬
    int row;
    int col;
    public node(int n, int m) {
        this.row = n;
        this.col = m;
    }
}
class Main {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static int[][] arr;
	static int N;
	static int M;
	private static int[] addRow = {1, 0, -1, 0 }; //
    private static int[] addCol = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
//        int start_node = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[N][M]; //방분한곳 체크
        
        for(int i=0; i<N; i++) {
        	String st2 = bf.readLine();
        	for(int j=0; j<M; j++) {
            	int x = st2.charAt(j)-48;
        		arr[i][j] = x;
            }
        }
        bfs(visited);
        System.out.println(arr[N-1][M-1]);
    }	
//	BFS = queue
	private static void bfs(boolean v[][]) {
		Queue<node> q = new LinkedList<>();
//		int[] c_arr;
		int n_r = 0;
		int n_c = 0;
		
		q.offer(new node(0, 0));//시작 노드를 저장

		while(!q.isEmpty()) {
			node cur_node = q.poll(); //시작노드를 뽑고
			
			if((cur_node.row == N-1) && (cur_node.col == M-1)) break;

			for(int i=0; i<4; i++) {
				n_r = cur_node.row+addRow[i];
				n_c = cur_node.col+addCol[i];
				if(n_r<0||n_r>=N||n_c>=M||n_c<0||v[n_r][n_c]||arr[n_r][n_c]==0) {
					continue;
				}
//				System.out.println(dis+" "+ n_r +" "+n_c+" ");
				v[n_r][n_c]=true;
				q.offer(new node(n_r, n_c));
				arr[n_r][n_c] = arr[cur_node.row][cur_node.col]+1;
			}//반복
		}
	}

//	DFS = stack
//	private static void dfs(int n, boolean v[]) {
//		v[n] = true;
//		for(int i=0; i<list.get(n).size(); i++) {
//			if(!v[list.get(n).get(i)]) {
//				sb.append(list.get(n).get(i)).append(" ");
//				dfs(list.get(n).get(i), v);
//			}
//		}
//	}
}