import java.io.*;
import java.util.*;

/*   오목 2615  60
 * 
 *   2차원 배열 탐색
 */
public class Baekjoon2615 {
	static int N, M, K;
	static int[][] arr;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //8방향 탐색
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int size = 19;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		

		arr = new int[19][19];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//가장 왼쪽 부터 탐색
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
//				System.out.println(j+" "+i);
				if(arr[j][i]==0) continue;
				
				if(chkOmok(j, i, arr[j][i])) {
					sb.append(arr[j][i]+"\n");
					sb.append((j+1)+" "+(i+1));
					System.out.println(sb);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
	
	static boolean chkOmok(int r, int c, int cur) {
		int cnt = 1;
		
		for(int i=0; i<8; i++) {
			int nr = r;
			int nc = c;
			int br = r;
			int bc = c;
			cnt = 1;

			for (int j = 0; j < 5; j++) {//전의 값과 다음값을 각각 찾아야 함
				br -= dr[i];
				bc -= dc[i];
				if(!(isInside(br, bc))) continue;
				if(arr[br][bc]!=cur) {
					break;
				}
				cnt++;
			}
			
			for (int j = 0; j < 5; j++) {
				nr += dr[i];
				nc += dc[i];
				if(!(isInside(nr, nc))) continue;
				if(arr[nr][nc]!=cur) {
					break;
				}
				cnt++;
				if(cnt>=6) break;
			}
			
			if(cnt==5) return true;
		}
		return false;
	}

	private static boolean isInside(int nr, int nc) {
		if(nr<0||nr>=19||nc<0||nc>=19) return false;
		return true;
	}

	static void print(boolean[][] arr) {
		for(boolean[] i:arr) {
			for(boolean j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
}
