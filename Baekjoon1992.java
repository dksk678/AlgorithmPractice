import java.io.*;
import java.util.*;

/*  쿼드트리 1992
 *  
 *  2-1-3-4 사분면 순으로 재귀실행
 *  1. 시작점의 값과 사분면안에 있는 값 중 다른값 비교
 *  2. 시작점값과 다른 값을 발견하면 n의 절반값으로 다시 1번 실행
 *  3. 값이 같으면 시작 값 저장
 *  반복
 */

class Main {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int[][] arr;
	static int[][] d;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	String str = bf.readLine();
			for(int j=0; j<N; j++) {
				int n = str.charAt(j)-48;
				arr[i][j] = n;
			}
        }
        req(N, 0, 0);
        System.out.println(sb);
    }

	private static void req(int n, int x, int y) {
		if(n==1) {
			sb.append(arr[x][y]);
			return;
		}
		boolean chk = false;
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(arr[x][y]!=arr[i][j]) {
					chk = true;
					break;
				}
			}
			if(chk) break;
		}
		if(!chk) {sb.append(arr[x][y]);}
		else {
			sb.append("(");
			req(n/2, x, y); //2사분면 왼쪽위부터 ~오른쪽아래순으로 
			req(n/2, x, y+n/2); //1사분면
			req(n/2, x+n/2, y); //3사분면
			req(n/2, x+n/2, y+n/2); //4사분면
			sb.append(")");
		}
	}
}