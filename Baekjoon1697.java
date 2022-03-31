import java.io.*;
import java.util.*;

/*  숨바꼭질 1697  16:10 ~ 16:37
 *  
 *  BFS 
 *  방향 = -1, +1 , *2(현재위치+현재위치)
 *  
 *  시작 숫자에서 원하는 숫자 나올 때까지 최소 이동 횟수
 */
class Baekjoon1697 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int K;
	
	
    
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        if (N>=K) System.out.println(N-K); 
        else System.out.println(BFS(N,K)); //4319
    }
	
	public static int BFS(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		int max = (end>start?end:start) + 2;
		boolean[] visited = new boolean[max];
		int[] d = {-1, 1, 0};
		int[] cost = new int[max];
		q.offer(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			d[2] = cur;//현재위치
			for(int i: d) {
				int next = cur+i;
				if((next>max-1||next<0||visited[next])) continue;
				if (cost[next]<=cost[cur]+1) {
					visited[next] = true;
					cost[next]=cost[cur]+1;
					if(next==end) {
						return cost[next];
					}
					q.offer(next);	
				}
			}
		}
		return 0;
	}
}