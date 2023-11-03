import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] par;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		par = new int[N];
		for (int i = 0; i < N; i++) {
			par[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken())==1) {
					union(i, j);
				}
			}
		}
		
		String ans = "YES";
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for (int i = 1; i < M; i++) {
			if(find(par[s-1]) != find(par[Integer.parseInt(st.nextToken())-1])) {
				ans = "NO";
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	private static void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);
		
		if(p1==p2) return;
		
		par[p2] = p1;
		return;
	}

	private static int find(int i) {
		if(i == par[i]) return i;
		return par[i] = find(par[i]);
	}

}
