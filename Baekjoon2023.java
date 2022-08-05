import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*  신기한 소수 2023
 *  
 *  가지치기
 *  백트래킹 
 */
public class Baekjoon2023 {
	static StringBuilder sb;
	static int[] arr2 = {1, 3, 5, 7, 9}; //나머지 자리 수는 5개만
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
        
        int[] arr1 = {2, 3, 5, 7}; //첫째 자리 수는 4개만
		for(int i:arr1) {
			DFS(N, 1, i);
		}
		System.out.println(sb);
	}

	private static void DFS(int N, int cnt, int num) {
		if(!isP(num)) return; //소수면 리턴
		
		if(cnt==N) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i = 0; i < 5; i++) {		
			DFS(N, cnt+1, num*10+arr2[i]);
		}
	}
	
	private static boolean isP(int n) {
		if(n==1) return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if(n%i==0) return false;
		}
		return true;
	}
}
