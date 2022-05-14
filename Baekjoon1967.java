import java.io.*;
import java.util.*;
/*  트리의 지름  1967  180
 *  
 *  가장 깊이 가는 것이 가장 긴 값이므로 한 루트에서 가장 긴 값 찾기
 *  
 *  -> 큰 수 2개 저장하면 for한번에 가능 (1500->230)ms
 */
class Baekjoon1967 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static int ans;
	
	static class Node {
		int num, cost;
		public Node(int c, int co) {
			this.num = c;
			this.cost = co;
		}
	}
	static ArrayList<ArrayList<Node>> arr;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        N = Integer.parseInt(bf.readLine());
        arr = new ArrayList<ArrayList<Node>>();
        
        for(int i=0; i<=N; i++) {
        	arr.add(new ArrayList<Node>());
        }  
        for(int i=0; i<N-1; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int p = Integer.parseInt(st.nextToken());
    		arr.get(p).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        DFS(1);
        System.out.println(ans);
	}

	static int DFS(int num) {
		int f, s; //첫 큰수, 두 번째 큰수
		f = 0; 
		s = 0;
		int m=0;
		for(int i=0; i<arr.get(num).size(); i++) {
			m = DFS(arr.get(num).get(i).num) + arr.get(num).get(i).cost;
			if(f<m) {
				s = f;
				f = m;
			} else if(s<m) {
				s = m;
			}
		}
		m = s + f;
		if (ans<m) ans = m;
		return f;
	}
}