import java.io.*;
import java.util.*;
/*  문자열 폭발 9935 80
 * 
 *  stack에 넣을 때마다 bomb와 비교
 *  bomb랑 일치하면 bomb길이 만큼 pop
 *  최종 stack길이가 0이되면 FRULA
 *  아니면 남은 stack 출력
 *  ** 일일이 삭제 보다 bomb이 되면 bomb 전 값으로 돌리고 배열삽입 위치가 0이 되면 frula
 */
class Baekjoon9935
{	
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String str, bomb;
		
		str = br.readLine();
		bomb = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		int bl = bomb.length(); //bomb길이
		int sl = 0; //스택 길이
		int cnt;
		
		for(int i=0; i<str.length(); i++) {
			stack.push(str.charAt(i));
			sl = stack.size();
			cnt = 0;
			if(sl>=bl) {
				for(char c: stack.subList(sl-bl, sl)) {
					if(c!=bomb.charAt(cnt)) break;
					cnt++;
				}
				if(cnt==bl) {
					for(int j=0; j<bl; j++) {
						stack.pop();
					}
				}
			}
		}
		
		if(stack.isEmpty()) bw.write("FRULA");
		else {
			for(char c:stack) {
				bw.write(c);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

} 
