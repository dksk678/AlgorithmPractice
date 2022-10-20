import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1016_제곱nn수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		long d = max-min+1;
		boolean[] era = new boolean[(int) d];
		
		int smax = (int) Math.sqrt(max);
		
		for (long i = 2; i <= smax; i++) {
			long ip = i*i;
			long num = min /ip;
			
			if(min%ip!=0) num++; //현재 값이 나눠 떨어지지않으면 다음 값부터
			
			for (long j = num; j*ip <= max; j++) { //min부터 제곱수 구해서 체크 
				int t = (int) (j*ip-min);
				
				if(era[t]) continue;
				
				era[t] = true;
				d--; //제곱수로 나누어 떨어지면 전체 개수에서 빼주기
			}
		}
		
		System.out.println(d);
	}

}
