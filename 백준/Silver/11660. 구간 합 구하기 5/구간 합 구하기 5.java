import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] memoi = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				memoi[i][j] = Integer.parseInt(st.nextToken())+memoi[i][j-1]+memoi[i-1][j]-memoi[i-1][j-1];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken()); //y는 0부터 시작
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());  //r은 0부터 c는 1부터
			
			sb.append(memoi[x2][y2]-memoi[x2][y1-1]-memoi[x1-1][y2]+memoi[x1-1][y1-1]).append("\n"); //y가 열
		}
		
		System.out.println(sb);
	}
}