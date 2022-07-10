import java.io.*;
import java.util.*;
/*  보급로  1249  25
 *  
 *  2차원 배열 BFS(Dijkstra) 
 */
class SWproblem1249 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <=T ; test_case++)
        {   //set
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            
            //최댓값 구하기
            for(int i=0; i<N; i++) {
            	String str = br.readLine();
            	for(int j=0; j<N; j++) {
	            	arr[i][j] = str.charAt(j)-48;
	            }
            }
//            print(arr);
            bw.write("#"+test_case+" ");
            bw.write(BFS(N, arr)+"");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int BFS(int N, int[][] arr) {
    	PriorityQueue<Node> pq = new PriorityQueue<Node>();
    	int[][] warr = new int[N][N];
    	int max = 1234578;
    	
    	pq.offer(new Node(0, 0, 0));
    	for(int[] i:warr) {
    		Arrays.fill(i, max);
    	}
    	warr[0][0] = 0;

    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		int cx = cur.x;
    		int cy = cur.y;
    		int cw = cur.weight;
    		
    		for(int i=0; i<4; i++) {
    			int nx = cx+dx[i];
    			int ny = cy+dy[i];
    			
    			if(nx<0||nx>=N||ny<0||ny>=N) continue; //범위 초과
    			if(warr[nx][ny] <= arr[nx][ny]+cw) continue; //기존값이 더 작음
    			
    			warr[nx][ny] = arr[nx][ny]+cw;
    			pq.offer(new Node(nx, ny, warr[nx][ny]));
    		}
    		
    	}
    	
    	return warr[N-1][N-1];
    }
    
    static class Node implements Comparable<Node>{
    	int x, y, weight;
    	public Node(int x, int y, int w) {
    		this.x = x;
    		this.y = y;
    		this.weight = w;
    	}
    	@Override
    	public int compareTo(Node o) {
    		// TODO Auto-generated method stub
    		return this.weight-o.weight;
    	}
    }
    //###########Array print#################
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