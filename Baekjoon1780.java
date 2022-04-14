import java.io.*;
import java.util.*;

/*  종이의 개수 1780
 * 
 *  분할정복 재귀
 *  
 *  쿼드 트리에서 -> 9개 분할로 변경 9구역을 계산
 * 
 */

class Main {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
	static int cnt;
	static int[][] arr;
	static int[] res;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	 for(int j=0; j<N; j++) {
             	arr[i][j] = Integer.parseInt(st.nextToken());
             }
        }
        res = new int[3];
        req(N, 0, 0);
        
        sb.append(res[0]).append("\n");
        sb.append(res[1]).append("\n");
        sb.append(res[2]).append("\n");
        
        System.out.println(sb);
    }
	
	private static void req(int n, int x, int y) {
		if(n==1) {res[arr[x][y]+1]++; return;} //구역의 크기가 1이되면 그 구역의 값 count
		
		int num = arr[x][y];
		boolean chk = true;
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(num!=arr[i][j]) { //다른 수가 나오면 멈춤
					chk = false;
					break;
				}
			}
			if(!chk) break;
		}
		
		if(chk) res[arr[x][y]+1]++; //다른수가 안나왔으면 한구역이 같은 숫자임.
		else {//다른 수면 구역을 9개로 다시 나눔
			req(n/3, x, y);  //0
			req(n/3, x, y+n/3); //1
			req(n/3, x, y+(n*2)/3); //2
			req(n/3, x+n/3, y); //3
			req(n/3, x+n/3, y+n/3); //4
			req(n/3, x+n/3, y+(n*2)/3); //5
			req(n/3, x+(n*2)/3, y); //6
			req(n/3, x+(n*2)/3, y+n/3); //7
			req(n/3, x+(n*2)/3, y+(n*2)/3); //8
		}
	}
}