import java.io.*;
import java.util.*;
/*  카드 정렬하기 1715 70
 *  
 *  3, 4, 5, 6 
 *  5, 6, 3+4  sum=7
 *  3+4, 5+6   sum=11
 *  0          sum=18
 *  ans = 36이 되는 형태
 *  -> 우선순위 큐
 *  ** N은 1일 때 비교 0번임.
 */
class Baekjoon1715
{	
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
//		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		} 
		
		int sum = 0;
		int ans = 0;
		
		while(pq.size()>1) {
			sum = pq.poll()+pq.poll();
			pq.offer(sum);
			ans += sum;
		}

		bw.write(ans+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
} 
