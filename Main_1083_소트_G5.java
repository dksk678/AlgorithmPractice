import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1083_소트_G5 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		int S = Integer.parseInt(br.readLine());
		int max = 0;
		int midx = 0;
		for (int i = 0; i < N-1; i++) {
			max = arr.get(i);
			midx = i;
			for (int j = i+1; j < i+1+S; j++) {
				if(j==N) break;
				
				if(max<arr.get(j)) {
					max = arr.get(j);
					midx = j;
				}
			}
			
			arr.remove(midx);
			arr.add(i, max);
			S -= (midx-i);
		}
			
		for (int i:arr) {
			sb.append(i+" ");
		}
		
		System.out.println(sb);
	}

}
