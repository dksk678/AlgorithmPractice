import java.io.*;
import java.util.*;
/*  줄 세우기 2252 90
 * 
 *  위상 정렬: 순서가 정해져 있는 작업들을 차례대로 수행할 때 사용
 *   사이클이 없는 방향 그래프에서 수행
 *  - 다음방향의 노드와 진입개수를 저장한 후
 *    진입개수가 0인 노드부터 시작하여 
 *    다음 노드의 진입개수를 1개 씩 지우고, 진입개수가 0이 되면 저장(순서 대로 저장됨)
 *    모든 노드의 진입개수가 전부 0이 될 때 까지 반복한다.
 */
class Baekjoon2252 { 
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> graph;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	indegree = new int[N+1];
    	graph = new ArrayList<ArrayList<Integer>>();
    	
    	for(int i=0; i<=N; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	
        	graph.get(A).add(B);
        	indegree[B]++;
        }
        
        TopologicalSort();
        
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void TopologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				q.offer(i);
				sb.append(i+" ");
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();

			for(int i:graph.get(cur)) {
				indegree[i]--;
				if (indegree[i]==0) {
					q.offer(i);
					sb.append(i+" ");
				}
			}
		}

	}
}