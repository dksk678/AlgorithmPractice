import java.io.*;
import java.util.*;

/*  트리의 부모 찾기  11725  40
 *  
 *  트리 구조 세팅!!! DFS, BFS
 */

class Main {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T;
	static int[] ans;
	static ArrayList<ArrayList<Integer>> tree;

	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        
        //set
        tree = new ArrayList<ArrayList<Integer>>();
        ans = new int[T+1];
        for(int i=0; i<=T; i++) {
        	tree.add(new ArrayList<Integer>());
        }
        int node = 0;
        int node2 = 0;
        for(int i=1; i<T; i++) {
        	st = new StringTokenizer(bf.readLine());
        	node = Integer.parseInt(st.nextToken());
        	node2 = Integer.parseInt(st.nextToken());
        	tree.get(node).add(node2);
        	tree.get(node2).add(node);
        }
        DFS(1);
        //BFS(1);
        for(int i=2; i<=T; i++) {
        	sb.append(ans[i]+"\n");
        }
        System.out.println(sb);
	}

	static void BFS(int cur) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(cur);
		
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i: tree.get(cur)) {
				if(ans[i]==0) {
					ans[i] = cur;
					q.offer(i);
				}
			}
		}
	}
    
	static void DFS(int cur) {
		for(int i: tree.get(cur)) {
			if(ans[i]==0) {
				ans[i] = cur;
				DFS(i);
			}
		}
	}
}