import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  DNA 문자열이란 모든 문자열에 등장하는 하는 문자가 {'A','C','G','T'}인 문자열
 * 
 *  A, C, G는 1개 이상 
 */
public class Baekjoon12891 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); //DNA문자열 길이
		int P = Integer.parseInt(st.nextToken()); //비밀번호 길이
		String str = br.readLine();
		
		st = new StringTokenizer(br.readLine());

		int a= Integer.parseInt(st.nextToken());
		int c= Integer.parseInt(st.nextToken());
		int g= Integer.parseInt(st.nextToken());
		int t= Integer.parseInt(st.nextToken());

		int[] chkACGT = new int[21];
		for (int i = 0; i < P; i++) {
			chkACGT[str.charAt(i)-'A']++;
		}
		int ans = 0;
		if(a<=chkACGT[0] && c<=chkACGT[2] && a<=chkACGT[6] && a<=chkACGT[19]) {
			ans++;
		}
		
		for (int i = 0; i < S-P; i++) {
			chkACGT[str.charAt(i)-'A']--;
			chkACGT[str.charAt(P+i)-'A']++;
			if(a<=chkACGT[0] && c<=chkACGT[2] && g<=chkACGT[6] && t<=chkACGT[19]) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
