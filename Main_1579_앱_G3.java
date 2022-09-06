

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1579_앱_G3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] bytes = new int[N];
		int[] c = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bytes[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			c[i] = num;
			sum += num;
		}
		int[] dp = new int[10001]; //N번째 cost에 byte저장된 값 cost는 최대 10000
		
		for (int i = 0; i < N; i++) {
			 //작은 값에서 큰값으로 가면 기존 사용한 값 중복해서 저장됨 3이 30이면 6은 dp[3]+30이므로 60이됨
			for (int j = sum; j >= c[i]; j--) { //하지만 sum부터 시작하면  15는 30이고 dp[12]+30이므로 30 유지
				dp[j] = Math.max(dp[j], dp[j-c[i]]+bytes[i]);
			}
		}
		
		int ans = 0;
		for (int i = 0; i < 10002; i++) {
			if(dp[i]>=M) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
