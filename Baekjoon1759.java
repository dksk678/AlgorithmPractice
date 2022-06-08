import java.io.*;
import java.util.*;
/*  암호 만들기 1759 60
 *  
 *  DFS -> 모음과 자음 개수를 따로 체크
 *  
 *  **문자열 더하는 작업에서 시간 더 걸린듯
 */
class Baekjoon1759 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, C;
	static char[] arr;
	static ArrayList<Character> vowel = new ArrayList<Character>();
	static char[] ans;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        ans = new char[N];
        
        for(int i=0; i<C; i++) {
        	arr[i] = st.nextToken().charAt(0);
        }
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');
        
        Arrays.sort(arr);
        DFS(0, 0, 0, 0); //다음 수, 길이, 모음, 자음, 문자열
        
        System.out.println(sb);
	}
	
	static void DFS(int idx, int cnt, int v, int c) {
		if(cnt==N) {
			if(v>=1&&c>=2) {
				for(char c1:ans) {
					sb.append(c1);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=idx; i<C; i++) {
			ans[cnt] = arr[i];
			if(vowel.contains(arr[i])) {
				DFS(i+1, cnt+1, v+1, c);
			} else {
				DFS(i+1, cnt+1, v, c+1);
			}
			
		}
	}
}