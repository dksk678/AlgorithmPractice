import java.io.*;
import java.util.*;
/*  LCA 11437 90 
 * 
 *  1. 자식노드의 깊이와 부모노드들을 구하기
 *  2. 1번으로 구한 값을 활용하여 공통 조상 구하기 -> 자식노드들의 깊이를 맞춘 후 부모 노드 찾기.
 */
class Baekjoon11437 { 
	static int N, M;
	static StringBuilder sb;
	static int min = 123456789;
	static ArrayList<ArrayList<Integer>> tree;
	static int[] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        StringTokenizer st;
        sb = new StringBuilder();
        
    	N = Integer.parseInt(br.readLine());
    	tree = new ArrayList<ArrayList<Integer>>();
    	for(int i=0; i<=N; i++) {
    		tree.add(new ArrayList<Integer>());
    	}
    	for(int i=1; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	tree.get(a).add(b);
        	tree.get(b).add(a); //입력 순서의 의미가 없음. 자식 노드와 부모 노드를 모름.
    	}
    	parent = new int[N+1];
    	depth = new int[N+1];
    	getParentAndDepth(1, 1);
    	
    	M = Integer.parseInt(br.readLine());
    	for(int i=0; i<M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	int ca = findCA(n1, n2);
        	sb.append(ca+"\n");
    	}
    	
        System.out.println(sb);
	}
	static void getParentAndDepth (int d, int p) { //노드의 깊이와 부모 노드 구하기.
		depth[p] = d;
		
		for(int i:tree.get(p)) {
			if(depth[i]!=0) continue;
//			System.out.println(p);
			parent[i] = p;
			getParentAndDepth(d+1, i);
		}
	}
	
	static int findCA(int n1, int n2) { //공통조상 찾기.
		if(depth[n1]<depth[n2]) {
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		//깊이 맞추기
		int diff = depth[n1]-depth[n2];
		for(int i=0; i<diff; i++) {
			n1 = parent[n1];
		}
		
		while(n1!=n2) {
			n1 = parent[n1];
			n2 = parent[n2];
		}
		
		return n1;
	}
	
	//######### arr_print #########################
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}