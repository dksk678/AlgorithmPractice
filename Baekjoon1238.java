import java.io.*;
import java.util.*;

/*  파티 1238 60
 * 
 *  N개의 마을 한 명의 학생
 *  N명의 학생이 N번 마을에 모여서 파티. M개의 단방향 도로들 지나는 데 시간걸림
 *  파티장까지 오고가는 시간이 최장인 학생의 시간 구하기
 *  ** compare를 num으로 함.. 근데 왜 결과가 같았지..
 */

class Baekjoon1238 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static int X;
	static int res;
	
	static class Node implements Comparable<Node> {
		int num;
		int time;
		public Node(int n, int t) {
			this.num = n;
			this.time = t;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.time-o.time;
		}
	}
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> adjlist = new ArrayList<ArrayList<Node>>();
    	ArrayList<ArrayList<Node>> revadjlist = new ArrayList<ArrayList<Node>>();
    	
        for(int i=0; i<=N; i++) {
        	adjlist.add(new ArrayList<Node>());
        	revadjlist.add(new ArrayList<Node>());
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	adjlist.get(a).add(new Node(b, c)); 
        	revadjlist.get(b).add(new Node(a, c)); 
        }
        
        int res = 0;
        int[] go = dijkstra(X, revadjlist); //모든 마을에서 X까지 거리 구하기 
        int[] back = dijkstra(X, adjlist); //X에서 모든 마을 거리 구하기
        for(int i=1; i<=N; i++) {
        	if(i==X) continue;
        	res = Math.max(res, go[i]+back[i]);
        }
        System.out.println(res);
	}
	
	static int[] dijkstra(int start, ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] v = new boolean[N+1];
		int[] cost = new int[N+1];
		
		pq.offer(new Node(start, 0));
		Arrays.fill(cost, 999999999);
		cost[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(v[cur.num]) continue;
			v[cur.num] = true;
			
			for(Node next : list.get(cur.num)) {
				if(cost[next.num]<=next.time+cost[cur.num]) continue;

				cost[next.num] = next.time+cost[cur.num];
				pq.offer(new Node(next.num, cost[next.num]));
			}
		}
		return cost;
	}
}