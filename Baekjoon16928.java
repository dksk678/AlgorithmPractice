import java.io.*;
import java.util.*;
/*  뱀과 사다리 게임  16928 21:00
 * 
 *  100초과면 이동 못함.
 * 	 
 */
class Baekjoon16928 { 
	static int N, M;
	static StringBuilder sb;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        StringTokenizer st;
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	arr = new int[101];
    	
    	for(int i=0; i<101; i++) {
    		arr[i] = i;
    	}

    	for(int i=0; i<N+M; i++) {
    		st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
    	}
    	
    	System.out.println(BFS());
	}
	
	static int BFS() {
		Queue<Node> q = new LinkedList<Node>();
		boolean[] v = new boolean[101];
		q.offer(new Node(1, 0));
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i=1; i<=6; i++) {
				int next = cur.n+i;
				if(next>=101 || v[next]) continue;
				
				if(next==100) {
					return cur.cost+1;
				}
				next = arr[next];
				v[next] = true;
				q.offer(new Node(next, cur.cost+1));
			}
		}
		
		return 0;
	}
	
	static class Node {
		int n, cost;

		public Node(int n, int cost) {
			this.n = n;
			this.cost = cost;
		}
	}
	
	
	
//############### print ##############	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
	
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.println(i+" ");
		}
		System.out.println("-----------");
	}
}