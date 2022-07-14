import java.io.*;
import java.util.*;
/*  contact  1238  35
 *  
 *  BFS 응용
 *  Node에 거리를 저장 
 *  -거리가 가장 큰 노드들 중 가장 큰 값 출력.
 */
class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<ArrayList<Integer>> arr;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
//        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <=10; test_case++)
        {   //set
        	arr = new ArrayList<ArrayList<Integer>>();
        	st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            
            for(int i=0; i<N; i++) {
            	arr.add(new ArrayList<Integer>());
            }
            
            //최댓값 구하기
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N/2; i++) {
            	int from = Integer.parseInt(st.nextToken());
            	int to = Integer.parseInt(st.nextToken());
            	arr.get(from).add(to);
            }
            
            bw.write("#"+test_case+" ");
            bw.write(BFS(start)+"");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int BFS(int start) {
    	Queue<Node> pq = new LinkedList<Node>();
    	int[] v = new int[101]; //최대 100
    	int end = 0;
    	
    	pq.offer(new Node(start, 0));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
			end = v[cur.num];
            
			for(int i:arr.get(cur.num)) {
				if(v[i]!=0) continue;

				v[i] = cur.cost+1;
				pq.offer(new Node(i, cur.cost+1));	
	    	}
    	}
        
    	int ans = 0;
    	for(int i=0; i<101; i++) {
    		if(end==v[i]) {
    			ans = ans<i?i:ans;
    		}
    	}
    	return ans;
    }
    
    static class Node{
    	int num, cost;
    	public Node(int n, int c) {
    		this.num = n;
    		this.cost = c;
    	}
    }
}