import java.io.*;
import java.util.*;

/*  DFS와 BFS  1260  18:40
 *  DFS = stack
 *  bfs = queue
 *  
 *  
 */

class Baekjoon1260 {
//	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Integer>> list;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start_node = Integer.parseInt(st.nextToken());
        list = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[N+1];

        for(int i=0; i<N+1; i++) {
        	list.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	//양방향
        	list.get(x).add(y);
        	list.get(y).add(x);
        }
        //정점번호가 더적은곳을 가기 위해 정렬해줌.
        for(int i=1; i<N+1; i++) {
        	Collections.sort(list.get(i));
        }
        //dfs
        sb = new StringBuilder();
        sb.append(start_node).append(" ");
        dfs(start_node, visited);
        
        //bfs
        visited = new boolean[N+1];
        sb.append("\n");
        sb.append(start_node).append(" ");
        bfs(start_node, visited);

        System.out.println(sb);
    }	
//	BFS = queue
	private static void bfs(int n, boolean v[]) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);//시작 노드를 저장
		v[n] = true;
		while(!q.isEmpty()) {
			int c = q.poll(); //시작노드를 뽑고
			ArrayList<Integer> cur_node = list.get(c);
			for(int i=0; i<cur_node.size(); i++) {
				if(!v[cur_node.get(i)]) {
					q.offer(cur_node.get(i));
					v[cur_node.get(i)] = true; //시작노드에 연결되어있는 노드들 넣기. 
					sb.append(cur_node.get(i)).append(" ");
				}
			}//반복
		}
	}

//	DFS = stack
	private static void dfs(int n, boolean v[]) {
		v[n] = true;
		for(int i=0; i<list.get(n).size(); i++) {
			if(!v[list.get(n).get(i)]) {
				sb.append(list.get(n).get(i)).append(" ");
				dfs(list.get(n).get(i), v);
			}
		}
	}
}