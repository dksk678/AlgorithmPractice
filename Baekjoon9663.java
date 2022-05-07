import java.io.*;
import java.util.*;

/*  N-Queen  
 *  
 *  2차원배열로 직접 못가는 곳 구하고 계산 하는 코드를 짰으나 9부터 시간 오래걸림
 *  
 *  -> 1차원 배열을 사용 (현재보다 먼저 놓인 퀸쪽은 사용이 불가능하므로 (해당 위치의 행은 사용 불가))
 *   -> 현재 퀸의 위치가 확정되면 다음 열로 내려감 (2차원 필요 없는 이유 2)
 *  
 *  현재 위치를 arr에 저장.
 */

class Baekjoon9663 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	
	static int[] arr; //초기 맵
	
	static int res = 0; //결과
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        //set
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];

        DFS(arr, 0);

        System.out.println(res);
	}
	
	static void DFS(int[] arr, int cnt) {
		if(cnt==N) { 
			res++; 
			return;
		}

		for(int i=0; i<N; i++) { //행
			boolean chk = true;
			for(int j=0; j<cnt; j++) { //첫 열부터 현재 바로 위 열까지
				 //같은 열이거나 			대각선(현재열-퀸이 있는 열 == 현재행 - 퀸이 있는 행)이면 넘어감
				if(arr[j]==i || Math.abs(cnt-j)==Math.abs(i-arr[j])) {
					chk = false;
					break; 
				}	                     
			}
			if(chk) {arr[cnt] = i; DFS(arr, cnt+1);}
		}
	}
}