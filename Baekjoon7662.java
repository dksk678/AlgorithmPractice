import java.io.*;
import java.util.*;

/*  이중 우선순위 큐 7662 120
 *  D 1은 최댓값 삭제
 *  D -1은 최솟값 삭제
 */

class Baekjoon7662 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int T;
	static Map<Integer, Integer> map;
	static int res;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //set
        T = Integer.parseInt(bf.readLine());
        while(T-->0) {
        	PriorityQueue<Integer> pqmin = new PriorityQueue<Integer>();
        	PriorityQueue<Integer> pqmax = new PriorityQueue<Integer>(Collections.reverseOrder());
        	N = Integer.parseInt(bf.readLine());
        	map = new HashMap<Integer, Integer>();
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(bf.readLine());
        		int n = 0;
        		switch (st.nextToken()) {
				case "I": {
					n = Integer.parseInt(st.nextToken());
					pqmax.offer(n);
					pqmin.offer(n);
					map.put(n, map.getOrDefault(n, 0)+1);
					break;
				}
				default:
					n = Integer.parseInt(st.nextToken());
					if(!map.isEmpty()) {
						if(n==1) { remove(pqmax);} 
						else { remove(pqmin); }
					}
				}
        	}
        	if(map.isEmpty()) sb.append("EMPTY"+"\n");
        	else {
        		res = remove(pqmax);
        		sb.append(res+" ");
        		
        		if(!map.isEmpty()) res = remove(pqmin);
        		
        		sb.append(res+"\n");
        	}
        }
        System.out.println(sb);
	}
	
	static int remove(PriorityQueue<Integer> q) {
		int ret = 0;
		while(true) {
			ret = q.poll();
			
			int n = map.getOrDefault(ret, 0);
			if(n==0) continue; //이미 삭제된 번호면 건너 뛰기
			//삭제
			if(n==1) map.remove(ret); 
			else map.put(ret, n-1); 
			break;
		}
		return ret;
	}
}