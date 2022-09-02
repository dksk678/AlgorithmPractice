import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16637_괄호추가하기_G4 {
	static int N;
	static int ans;
	static char[] str;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		ans = -123456789;
		str = br.readLine().toCharArray();
		DFS(str[0]-'0', 0);
		
		System.out.println(ans);
	}
	private static void DFS(int tot, int cnt) {
		if(cnt==N-1) { 
			ans = ans<tot?tot:ans;
			return;
		}
		
		DFS(calc(tot, str[cnt+2]-'0', str[cnt+1]), cnt+2);
		
		if(cnt+4<N) {
			int cal1 = calc(str[cnt+2]-'0', str[cnt+4]-'0', str[cnt+3]);
			DFS(calc(tot, cal1, str[cnt+1]), cnt+4);
		}
	}
	
	private static int calc(int t, int num, char o) {
		switch (o) {
		case '+':
			return t+num;
		case '-':
			return t-num;
		default:
			return t*num;
		}
	}

}
