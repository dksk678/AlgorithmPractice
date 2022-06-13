import java.io.*;
import java.util.*;
/*   최소 스패닝 트리 (MST) 1197 70
 *   
 *   1. kruskal(unino-find사용) 방법 (나중에 풀어보기)
 *     - 사이클을 확인하면서 간선 연결하고 가중치 값 더하기 
 *   2. prim 방법
 *     - 시작 노드를 기준으로 가중치가 작은 값을 선택하여 뻗어 나가는 알고리즘
 *    
 */
class Baekjoon1197 {
	static int ans; 
	static ArrayList<ArrayList<Node>> list;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        list = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=V; i++) {
        	list.add(new ArrayList<Node>());
        }
        
        for(int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	list.get(a).add(new Node (b, c));
        	list.get(b).add(new Node (a, c));
        }
        ans = 0;
        prim(V);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void prim(int V) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] v = new boolean[V+1];
		pq.offer(new Node(1, 0));
		while(!pq.isEmpty()) {
			int cur = pq.peek().num;
			int cost = pq.poll().cost;
			
			if(v[cur]) continue;
			
			v[cur] = true;
			ans += cost;
			
			for(Node node:list.get(cur)) {
				int next = node.num;
				int nextcost = node.cost;
				if(v[next]) continue;
				pq.offer(new Node(next, nextcost));
			}
			
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int cost;
		public Node(int n, int c) {
			this.num = n;
			this.cost = c;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
	}
	

//	static void print(int[][] arr) {
//		int cnt=0;
//		for(int[] i:arr) {
//			for(int j:i) {
//				if(j==2) cnt++;
//				System.out.print(j+" ");
//			}
//			System.out.println("");
//		}
//		System.out.println(cnt);
//	}
}