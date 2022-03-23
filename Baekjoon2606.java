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
class Baekjoon2606 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static int N;
	static int M;
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> list;
	static int max;
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
//        int start_node = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N+1]; //방분한곳 체크
        list = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<N+1; i++) {
//        	st = new StringTokenizer(bf.readLine());
        	list.add(new ArrayList<Integer>());
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
        	list.get(A).add(B);
        	list.get(B).add(A);
        }
        
//        arr2D_print();
        
        dfs(1, visited);
        System.out.println(max);
    }	
//	BFS = queue
//	private static void bfs(boolean v[][]) {
//		Queue<node> q = new LinkedList<>();
////		int[] c_arr;
//		int n_r = 0;
//		int n_c = 0;
//		
//		q.offer(new node(0, 0));//시작 노드를 저장
//
//		while(!q.isEmpty()) {
//			node cur_node = q.poll(); //시작노드를 뽑고
//			
//			if((cur_node.row == N-1) && (cur_node.col == M-1)) break;
//
//			for(int i=0; i<4; i++) {
//				n_r = cur_node.row+addRow[i];
//				n_c = cur_node.col+addCol[i];
//				if(n_r<0||n_r>=N||n_c>=M||n_c<0||v[n_r][n_c]||arr[n_r][n_c]==0) {
//					continue;
//				}
////				System.out.println(dis+" "+ n_r +" "+n_c+" ");
//				v[n_r][n_c]=true;
//				q.offer(new node(n_r, n_c));
//				arr[n_r][n_c] = arr[cur_node.row][cur_node.col]+1;
//			}//반복
//		}
//	}

//	DFS = stack
	private static void dfs(int n, boolean v[]) {
		if(v[n]) return;
		v[n] = true;
		for(int i=0; i<list.get(n).size(); i++) {
//			System.out.println(list.get(n).size()+" "+n+" "+i);
			if(!v[list.get(n).get(i)]) {
				max++;
				dfs(list.get(n).get(i), v);
			}
		}
		return;
	}
	
	private static void arr2D_print() {
		for(int i=0; i<N+1; i++) {
        	for(int j=0; j<list.get(i).size(); j++) {
        		System.out.print(list.get(i).get(j)+" ");
        	}
        	System.out.println("");
        }
	}
}