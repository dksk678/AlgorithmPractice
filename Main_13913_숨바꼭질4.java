import java.io.*;
import java.util.*;
/*  숨바꼭질 2 12851 120
 *  
 *  최단 경로 개수 세기
 *  1. 우선 최소 시간 구한 후  값 저장 = mintime
 *  2. 다음 탐색할 때 mintime보다 오래 걸리면 continue
 *  3. mintime를 구하기 위해 처음 방문했던 곳의 시간보다 더 빠른 방법이 있으면 새로운 길 탐색
 */
public class Main_13913_숨바꼭질4{
	static StringBuilder sb = new StringBuilder();
	static int mintime=0;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N>=K) {
			mintime = N-K;
			
			for(int i=N; i>K; i--) {
				sb.append(i).append(" ");
			}
			sb.append(K);
			
			System.out.println(mintime);
			System.out.println(sb);
			
		} else {
			BFS(N, K);
		}
		
	}
	
	static void BFS(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		int max = (start>end?start:end)+10;
		int[] time = new int[max];
		int[] d = {-1, 1, 0};
		int next;
		int[] v = new int[max];
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(start);
		time[start] = 0;
		
		q.offer(start);
		mintime = 9999999;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			d[2] = cur;
			for(int i=0; i<=2; i++) {
				next = cur+d[i];
				
				if(next<0||next>=max||mintime<time[next]) continue; //거리 초과, 최단시간보다 오래걸림
				if(time[next]>=1&&time[next]<time[cur]+1) continue; //방문했던 곳 중 더 빠른 방법 없으면
				
				time[next] = time[cur]+1;
				
				if(next==end) {
					mintime = time[next];
					
					v[next] = cur;
					
					Stack<Integer> stack = new Stack<>();
					int cnt = 0;
					while(true) {
						stack.add(v[next]);
						next = v[next];
						if(cnt==mintime-1) break;
						cnt++;
					}
					
					while(!stack.isEmpty()) {
						sb.append(stack.pop()).append(" ");
					}
					sb.append(end);
					
//					System.out.println("");
					System.out.println(mintime);
					System.out.println(sb);
					return ;
				} else {
					v[next] = cur;
					q.offer(next);
				}
			}
		}
		
		return;		
	}
	
	public static class subin {
		int p;
		ArrayList<Integer> path;
		
		public subin(int p, ArrayList<Integer> path) {
			super();
			this.p = p;
			this.path = path;
		}
		
	}
} 
