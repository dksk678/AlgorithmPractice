import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2133_타일채우기_G4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp1 = new int[31];
		int[] dp2 = new int[31];
		
		dp1[2] = 3;
		
		for (int i = 4; i <= N; i+=2) {
			dp1[i] = dp1[i-2]*3 + dp2[i-4] + 2;
			dp2[i-2] = dp1[i-2]*2+dp2[i-4]; 
		}
		
		System.out.println(dp1[N]);
	}

}
