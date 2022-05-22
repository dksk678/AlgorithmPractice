import java.io.*;
import java.util.*;
/* A->B 16953 30+(30)
 * 2를 곱한다. 1을 수의 가장 오른쪽에 추가한다.
 * 
 *  A를 B로 바꾸는데 연산의 최솟값을 구해보자
 *  
 *  **시간,메모리 관리 List로 방문체크하면 10^9승이므로 메모리, 시간 초과
 *  -> Map으로 바꿈
 */
class Baekjoon16953
{	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		System.out.println(BFS(A, B));
	}
	static long BFS(int A, int B) {
		Queue<Long> q = new LinkedList<>();
		Map<Long, Integer> v = new HashMap<Long, Integer>();
		long cur = 0;
		long tmp = 0;
		
		q.offer((long)A);
		v.put((long)A, 1);
		
		while(!q.isEmpty()) {
			cur = q.poll();
			
			if(cur == B) {
				return v.get(cur);
			}
			tmp = cur*2;
			if(tmp<=B && !v.containsKey(tmp)) {
				v.put(tmp, v.get(cur)+1);
				q.offer(tmp);
			}
			tmp = cur*10+1;
			if(tmp<=B && !v.containsKey(tmp)) {
				v.put(tmp, v.get(cur)+1);
				q.offer(tmp);
			}
		}
		return -1;
	}
} 
