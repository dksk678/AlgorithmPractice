import java.io.*;
import java.util.*;

/*  연결 요소의 개수  11724
 *  BFS, DFS 둘다 구해서 모든 노드 탐색해서 연결되어있는 것들 체크
 */

class Main {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> arr;
	static boolean[] v;
	
	static int N;
	static int M;
	static int T;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=N; i++) {
        	arr.add(new ArrayList<Integer>());
        }
        int a;
        int b;
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	arr.get(a).add(b);
        	arr.get(b).add(a);
        }

        v = new boolean[N+1]; 
        for(int i=1; i<=N; i++) {
        	if(v[i]==true) continue;
//        	DFS(i);
        	BFS(i);
        	cnt++;
        }
        
        System.out.println(cnt);
    }
	
	private static void DFS(int i) {
		if (v[i]) {return; }
		v[i] = true;
		for(int l: arr.get(i)) { //l
			if(v[l]==true) continue;
			DFS(l);
		}
	}
	
	private static void BFS(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		v[i] = true;
		q.add(i);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int l: arr.get(cur)) { //l
				if(v[l]==true) continue;
				v[l] = true;
				q.add(l);
			}
		}
	}
}