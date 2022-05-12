import java.io.*;
import java.util.*;

/*  숨바꼭질 3 13549 45
 *  
 *  방향성 없는 그래프 두 정점은 반드시 통과(한번 간 곳 다시 가능)
 *  **마지막 더하는 부분에서 inf로 인해 값이 오버플로우 발생할 수 있음..
 *  * dijkstra가 여러번 실행 되도 N만큼만 가는게 더 빠름(사이즈가크면) 
 *    -> PriorityQueue로 항상 최선의 값에서 return가능
 *  ** 방문 체크 값 잘못 넣음
 *  *** -1하고 2배하는 경우가 더 빠른 경우가 있음
 *  **** 가중치 늘림.
 */

class Baekjoon13549 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int start;
	static int end;
	static ArrayList<ArrayList<Node>> arr; 
	static int ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        
        if(start>end) {
        	System.out.println(start-end);
        } else {
        	System.out.println(BFS01());  
        }
	}
	
	static int BFS01() {
		Deque<Node> dq = new ArrayDeque<Node>();
		int[] v = new int[100001];
		Arrays.fill(v, 10001);
		v[start] = 0;
		dq.offer(new Node(start, 0));
		
		while(!dq.isEmpty()) {
			Node cur = dq.poll();
			if(cur.p==end) return cur.time;
			
			if(cur.p<=50000&&Math.abs(end-cur.p)>Math.abs(end-(cur.p*2))) { //2배
				if(v[cur.p*2]>cur.time) {
					v[cur.p*2] = cur.time;
					dq.offer(new Node(cur.p*2, cur.time));
				}
			}
			if (cur.p-1>=0) { //-1
				if(v[cur.p-1]>cur.time+1) {
					v[cur.p-1] = cur.time+1;
					dq.offerLast(new Node(cur.p-1, cur.time+1));
				}
			}
			if(cur.p+1<=100000) { //+1
				if(v[cur.p+1]>cur.time+1) {
					v[cur.p+1] = cur.time+1; 
					dq.offerLast(new Node(cur.p+1, cur.time+1));
				}
			}
		} 
		return 0;
	}
	
	static class Node {
		int p;
		int time;
		public Node(int p, int t) {
			this.p = p;
			this.time = t;
		}
	}
}