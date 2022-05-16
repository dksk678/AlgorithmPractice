import java.io.*;
import java.util.*;
/*  트리의 지름  1167 70
 *  
 *  1일때 가장 긴 값 2개.
 *  *각 노드에서 가장 긴 값 2개 구해서 더한 값 구하기
 *    
 *  *다른 방법 1에서 가장먼곳(f) 위치 저장 -> f에서 가장 먼곳 까지의 거리 = 지름 
 *  DFS(DFS(1))의 거리를 구해도 됨. 
 */
class Baekjoon1167 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int res;
	static ArrayList<ArrayList<Node>> arr;
	
	static class Node{
		int num;
		int dis;
		public Node(int n, int d) {
			this.num = n;
			this.dis = d;
		}
	}

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        N = Integer.parseInt(bf.readLine());
        arr = new ArrayList<ArrayList<Node>>();
        for(int i=0; i<=N; i++) {
        	arr.add(new ArrayList<Node>());
        }
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int cur = Integer.parseInt(st.nextToken());
        	while(st.hasMoreTokens()) {
        		int next = Integer.parseInt(st.nextToken());
        		if(next==-1) break;
        		
        		int d = Integer.parseInt(st.nextToken());
        		arr.get(cur).add(new Node(next, d));
        	}
        }

        boolean[] v = new boolean[N+1];
        DFS(1, v);
        
        bw.write(res+"");

        bw.flush();
        bw.close();
        bf.close();
	}

	static int DFS(int n, boolean[] v) {
		int f, s, m;
		f = s = m = 0;
		
		for(int i=0; i<arr.get(n).size(); i++) {
			if(v[arr.get(n).get(i).num]) continue; //다음 노드에 탐색한 적 있으면 건너뜀
			v[n] = true; //부모노드 탐색
			m = DFS(arr.get(n).get(i).num, v) + arr.get(n).get(i).dis;
			v[n]=false;
			if(f<m) {
				s = f;
				f = m;
			} else if(s<m) {
				s = m;
			}
		}
		m = f+s;
		if(res<m) res = m;
		return f;
	}
}