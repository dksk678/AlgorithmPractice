import java.io.*;
import java.util.*;

/*  최단경로 1753 60
 * 
 *  다익스트라 (pq)
 *  제너릭배열 사용
 *  방문 체크
 */

class Baekjoon1753 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static ArrayList<Node>[] graph; //초기 맵
	static boolean[] v;
	
	static int res = 0; //결과

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(bf.readLine());
        graph = new ArrayList[N+1];
        v = new boolean[N+1];
        
        for(int i=0; i<N+1; i++) {
        	graph[i] = new ArrayList<Node>();
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int s =	Integer.parseInt(st.nextToken());
        	int e =	Integer.parseInt(st.nextToken());
        	int w =	Integer.parseInt(st.nextToken());
        	graph[s].add(new Node(e, w));
        }
        BFS(start);

        System.out.println(sb);
	}
	
	static void BFS(int start) { // 벽 3개 생성, cnt= 현재 열
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(start, 0));
		int[] cost = new int[N+1];
		Arrays.fill(cost, 100000000);
		cost[start] = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(v[cur.num]) continue;
			
			v[cur.num] = true;
			
			for(Node next: graph[cur.num]) { //graph에 저장되어있는 노드들 가져오기
				/* 다음 노드에 저장되어있는 값이
				 * 현재노드까지 값 + 다음 노드까지 가는 값 보다 작으면 coninue
				 * (이미 저장되어있는 최단거리보다 길면 continue)
				 */
				if(cost[next.num] <= cost[cur.num]+next.weight) continue;
				
				cost[next.num] = cost[cur.num]+next.weight;
				q.offer(new Node(next.num, cost[next.num]));
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(cost[i]==100000000) sb.append("INF\n");
			else sb.append(cost[i]+"\n");
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		
		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
}