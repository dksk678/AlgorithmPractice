import java.io.*;
import java.util.*;
/*  타임머신 11657 60 + 반례 30
 * 
 *  시간C가 음수일 수 있음. 
 *  1번에서 N번까지 모든 도시로 가는 가장 빠른 시간 출력
 *  -> 최단 경로 + 음수 = 벨만포드
 *   *최저 음수값이 int값 넘어감
 *   **출력 문제 ** (cost에 저장해서 마지막에 출력해줌)
 */
class Baekjoon11657 { 
	static int N, M;
	static StringBuilder sb;
	static ArrayList<Node> graph;
	static long[] cost;
	static int max = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());  
        
        graph = new ArrayList<Node>();

        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	graph.add(new Node(a, b, c));
        }
        
        cost = new long[N+1];
        if(bellmanford()) {
        	bw.write(-1+"");
        } else {
        	for(int i=2; i<=N; i++) {
        		long num = cost[i];
        		if(cost[i]==max) num = -1;
    			bw.write(num+"\n");
    		}
        }
        
//        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static boolean bellmanford() {
//		long[] cost = new long[N+1];
		Arrays.fill(cost, max);
		cost[1] = 0;
		for(int i=1; i<=N; i++) {
			boolean chk = false;
			
			for(Node node:graph) {
				if(cost[node.cur]==max || cost[node.next] <= (cost[node.cur] + node.cost)) continue;
				
				chk = true;
				cost[node.next] = cost[node.cur] + node.cost;
				
				if(i==N) return true; //마지막까지 싸이클 돌 때
			}
			
//			System.out.println(chk);
//			print(cost);
			if(!chk) break;
		}
		return false;
	}
	
	static class Node{
		int cur, next, cost;
		public Node(int a, int b, int c) {
			this.cur = a;
			this.next = b;
			this.cost = c;
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