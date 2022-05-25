import java.io.*;
import java.util.*;
/*  숨바꼭질 2 12851 120
 *  
 *  최단 경로 개수 세기
 *  1. 우선 최소 시간 구한 후  값 저장 = mintime
 *  2. 다음 탐색할 때 mintime보다 오래 걸리면 continue
 *  3. mintime를 구하기 위해 처음 방문했던 곳의 시간보다 더 빠른 방법이 있으면 새로운 길 탐색
 *  
 */
class Baekjoon12851
{	
	static StringBuilder sb = new StringBuilder();
	static int mintime=0;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		if(N>=K) {
			cnt = 1;
			mintime = N-K;
		} else {
			cnt = BFS(N, K);
		}
	
		bw.write(mintime+"\n");
		bw.write(cnt+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int BFS(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		int max = (start>end?start:end)+10;
		int[] time = new int[max];
		int[] d = {-1, 1, 0};
		int cur, next, cnt;
		
		time[start] = 0;
		q.offer(start);
		cur=next=cnt=0;
		mintime = 9999999;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			d[2] = cur;
			for(int i=0; i<=2; i++) {
				next = cur+d[i];
				
				if(next<0||next>=max||mintime<time[next]) continue; //거리 초과, 최단시간보다 오래걸림
				if(time[next]>=1&&time[next]<time[cur]+1) continue; //방문했던 곳 중 더 빠른 방법 없으면
				
				time[next] = time[cur]+1;
				if(next==end) {
					mintime = time[next];
					cnt++;
				} else 
					q.offer(next);
			}
		}
		
		return cnt;		
	}
} 
