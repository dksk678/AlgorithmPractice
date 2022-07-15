import java.io.*;
import java.util.*;
/*  공통 조상 1248 D5 120~
 *  
 *  1. DFS를 이용하여 깊이와 부모 구하기
 *  2. 2개의 정점 중 같은 부모 찾기 (반복)
 *  3. 찾은 부모노드를 이용하여 부모노드를 루트로 하는 서브트리의 크기 구하기 (BFS)
 */
class SWproblem1248 {
	static StringBuilder sb;
	static ArrayList<ArrayList<Integer>> tree;
	static int[] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int V = Integer.parseInt(st.nextToken());
        	int E = Integer.parseInt(st.nextToken());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	
        	tree = new ArrayList<ArrayList<Integer>>();
        	for(int i=0; i<=V; i++) {
        		tree.add(new ArrayList<Integer>());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<E; i++) {
	        	int a = Integer.parseInt(st.nextToken());
	        	int b = Integer.parseInt(st.nextToken());
	        	tree.get(a).add(b);
        	}
        	
        	parent = new int[V+1];
        	depth = new int[V+1];
        	getDepth(1, 1);
        	int ca = findParent(n1, n2);
        	int subtree = cntSubTree(ca)+1;
        	
        	sb.append("#"+t+" "+ca+" "+subtree+"\n");
        }
                
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	//모든 노드들의 부모노드와 깊이 구하기.
	static void getDepth(int d, int p) {
		depth[p] = d;
		
		for(int i:tree.get(p)) {
			if(depth[i]!=0) continue;

			parent[i] = p;
			getDepth(d+1, i);
		}
	}
	//위에서 구해진 깊이와 부모노들을 이용하여 같은 부모 찾기.
	static int findParent(int n1, int n2) {
		if(depth[n1]<depth[n2]) {
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		//깊이의 차이가 있기 때문에 같은 깊이 일때로 맞춰줌.(더 깊은 노드의 부모노드를 구함) 
		int diff = depth[n1]-depth[n2];
		for(int i=0; i<diff; i++) {
			n1 = parent[n1];
		}
		while(n1!=n2) {
			
			n1 = parent[n1];
			n2 = parent[n2];
		}
		
		return n1;
	}
	//서브트리 크기 구하기
	static int cntSubTree(int p) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(p);
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next:tree.get(cur)) {
				cnt++;
				q.add(next);
			}
		}
		return cnt;
	}
}