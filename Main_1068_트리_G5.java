import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1068_트리_G5 {
	static ArrayList<ArrayList<Integer>> tree;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			tree.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		int root = 0;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n==-1) root = i;
			else {
				tree.get(n).add(i);
			}
		}
		
		int del = Integer.parseInt(br.readLine());
		
		DFS(root, del);
			
		System.out.println(cnt);
	}
	private static void DFS(int cur, int del) {
		if(cur==del) return;
		
		if(tree.get(cur).size()==0) {
			cnt++;
			return;
		}
		
		for (int child:tree.get(cur)) {
			DFS(child, del); //자식노드가 삭제노드인데 현재 노드에서 자식이 1명 있을 경우
			if(child==del && tree.get(cur).size()==1) {
				cnt++;
			}
		}
	}

}
