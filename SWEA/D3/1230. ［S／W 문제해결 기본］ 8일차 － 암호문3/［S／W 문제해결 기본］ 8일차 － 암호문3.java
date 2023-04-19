import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
        
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			ArrayList<Integer> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String com = st.nextToken();
				if(com.equals("I")) {
					commI(list, st);					
				} else if(com.equals("A")){
					commA(list, st);					
				} else {
					commD(list, st);					
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void commD(ArrayList<Integer> list, StringTokenizer st) {
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < y; i++) {
			list.remove(x);
		}
	}

	private static void commA(ArrayList<Integer> list, StringTokenizer st) {
		int y = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < y; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
	}

	private static void commI(ArrayList<Integer> list, StringTokenizer st) {
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < y; i++) {
			list.add(x+i, Integer.parseInt(st.nextToken()));
		}
	}
}
