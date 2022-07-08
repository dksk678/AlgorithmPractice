import java.io.*;
import java.util.*;
/*  네트워크 연결 1922 90
 *  
 *  모든 경로 최소 비용 검사 = 최소스패닝트리 (MST)
 *  
 *  우선순위 큐 활용 가장 작은 노드들만 탐색.
 */
class Baekjoon1922 { 
	static int N, M;
	static StringBuilder sb;
	static ArrayList<ArrayList<Node>> graph;
	static int ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=N; i++){
        	graph.add(new ArrayList<Node>());
        }
        
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	graph.get(a).add(new Node(b, c));
        	graph.get(b).add(new Node(a, c));
        }
        
        prim();
        
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean[] v = new boolean[N+1];
		pq.offer(new Node(1, 0));

		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(v[cur.num]) continue;
			
			v[cur.num] = true;
			ans += cur.cost;
			System.out.println(cur.cost);
			for(Node next:graph.get(cur.num)) {
				
				pq.offer(new Node(next.num, next.cost));
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int num, cost;
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
	
	//######### arr_print #########################
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}