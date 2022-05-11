import java.io.*;
import java.util.*;

/*  특정한 최단 경로 1504 50 + 반례(30)
 *  
 *  방향성 없는 그래프 두 정점은 반드시 통과(한번 간 곳 다시 가능)
 *  **마지막 더하는 부분에서 inf로 인해 값이 오버플로우 발생할 수 있음..
 *  * dijkstra가 여러번 실행 되도 N만큼만 가는게 더 빠름(사이즈가크면) 
 *    -> PriorityQueue로 항상 최선의 값에서 return가능
 */

class Baekjoon1504 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static ArrayList<ArrayList<Node>> arr;
	static int ans;
	
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		public Node (int n, int w) {
			this.num = n;
			this.weight = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=N; i++) {
        	arr.add(new ArrayList<Node>());
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	arr.get(from).add(new Node(to, w));
        	arr.get(to).add(new Node(from, w));
        }
        st = new StringTokenizer(bf.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        int[] f = dijkstra(1);
        int[] v1f = dijkstra(v1); //v1에서 N까지 모든 경로 == (반드시 v1거쳐간 값 있음)
        int[] v2f = dijkstra(v2); //v2에서 N까지 모든 경로 == (반드시 v1거쳐간 값 있음)
        
        long r1 = f[v1]+v1f[v2]+v2f[N];
        long r2 = f[v2]+v2f[v1]+v1f[N];
        
        if(r1>=10000000 || r2>=10000000) {
        	System.out.println(-1);
        } else if(r1<=r2) {
        	System.out.println(r1);
        } else {
        	System.out.println(r2);
        }
	}
	
	static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] cost = new int[N+1];
		boolean[] v = new boolean[N+1];
		Arrays.fill(cost, 10000000);
		cost[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
				
			if(v[cur.num]) continue;
			v[cur.num] = true;
			
			for(Node next:arr.get(cur.num)) {
				if(cost[next.num] <= next.weight+cost[cur.num]) continue;
				
				cost[next.num] = next.weight+cost[cur.num];
				pq.offer(new Node(next.num, cost[next.num]));
			}
		}
		return cost;
	}
}