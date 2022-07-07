import java.io.*;
import java.util.*;
/*  웜홀 1865 180
 * 
 *  월드나라 N개 지점. M개의 도로. w개의 웜홀. 도로는 방향x 웜홀은 방향 o
 *  웜홀 내에서는 시간이 거꾸로감. 
 *  
 *  음수 계산은 벨만-포드
 *  다익스트라: 한곳부터 쭉 이어진 노드로 계산
 *  벨만-포드: start의 주변 노드부터 차례대로 계산(값이 바뀌지 않을 때 까지) -> 음수 싸이클 예외
 *  **start 지점까지 저장한 후 작은 값 계속 탐색하여 한번에 하는 것이 더 빠름.
 */
class Baekjoon1865 { 
	static int N,M;
	static StringBuilder sb;
	static ArrayList<Node> path;
	
	static class Node{
		int s, e, cost;
		public Node(int s, int e, int cost) {
			this.s = s;
            this.e = e;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        while(tc-->0) {
            //set
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken()); //나라 수
        	M = Integer.parseInt(st.nextToken()); //도로 수
        	int w = Integer.parseInt(st.nextToken()); //웜홀 수
        	
        	path = new ArrayList<Node>();

        	
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken()); //지점1
             	int e = Integer.parseInt(st.nextToken()); //지점2
             	int t = Integer.parseInt(st.nextToken()); //거리
        		path.add(new Node(s, e, t));
        		path.add(new Node(e, s, t));
        	}
        	
        	for(int i=0; i<w; i++) {
        		st = new StringTokenizer(br.readLine());
        		int s = Integer.parseInt(st.nextToken()); //지점1
             	int e = Integer.parseInt(st.nextToken()); //지점2
             	int t = Integer.parseInt(st.nextToken()); //거리
        		path.add(new Node(s, e, -t));
        	}
        	
        	String ans = "NO";
            if(bellmanford()) ans = "YES";
            
        	bw.write(ans+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
	
	static boolean bellmanford() {
		int[] cost = new int[N+1];
		Arrays.fill(cost, 123456789);
		
		for(int i=1; i<=N; i++) {
			boolean chk = false;
			for(Node node : path) {
				if(cost[node.e]<=cost[node.s]+node.cost) continue;

				cost[node.e] = cost[node.s]+node.cost;				
				chk = true;
				if(i==N) return true; //싸이클 다 돌면 음수임
				
			}
			if(!chk) break; //바뀐게 없으면 멈추기
		}
		return false;
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