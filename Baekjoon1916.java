import java.io.*;
import java.util.*;

/*  최소비용 구하기  1916 60
 *  
 *  다익스트라
 *  **pq.offer 할때 바뀐 cost값 넣었어야함..
 *   
 *  *N=1일 때 ?
 */
class Baekjoon1916 {
	static StringBuilder sb;
	static ArrayList<ArrayList<Node>> adjlist;
	static boolean[] v;
	static int N;
	static int M;
	
	static class Node implements Comparable<Node> {
		int num;
		int cost;
		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        //set
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        v = new boolean[N+3];
        adjlist = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=N; i++) {
        	adjlist.add(new ArrayList<Node>());
        }

        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	adjlist.get(a).add(new Node(b, c));
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        //print
        System.out.println(dijkstra(start, end));
    }
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(start, 0));
		int[] costList = new int[N+3];
		Arrays.fill(costList, 2100000000);
		costList[start] = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(v[cur.num]) continue;
			v[cur.num] = true;
			for(Node next: adjlist.get(cur.num)) {
				
				if(costList[next.num]<=next.cost+costList[cur.num]) continue;
				
				costList[next.num] = next.cost+costList[cur.num];
				pq.offer(new Node(next.num, costList[next.num]));
			}
		}
		return costList[end];
	}
}