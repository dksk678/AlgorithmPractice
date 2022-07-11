import java.io.*;
import java.util.*;
/*  외판원 순회 2 10971  90
 * 
 *  백트래킹
 *  시작 위치저장(마지막 에 항상 시작위치로 오기 때문에)
 *  마지막 부분에 0인 경우 있으면 안됨 (0은 갈 수 없기 때문에)
 */
class Baekjoon10971 { 
	static int N, M;
	static StringBuilder sb;
	static int min = 123456789;
	static int[][] arr;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
       
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        boolean[] v = new boolean[N];
        for(int i=0; i<N; i++) {
        	v[i] = true;

        	backtracking(i, i, 0, 0, v);
        }
        bw.write(min+"");
        
//        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void backtracking(int start, int now, int cnt, int cost, boolean[] v) {
		if(cnt==N-1) {
			if(arr[now][start]!=0) min = Math.min(min, cost+arr[now][start]);
			return;
		}
		for(int i=0; i<N; i++) {
			if(arr[now][i]==0||v[i]) continue;
			v[i]=true;
			backtracking(start, i, cnt+1, cost+arr[now][i], v);
			v[i]=false;
		}
	}
	
	static class Node{
		int cur, next, cost;
		public Node(int a, int b, int c) {
			this.cur = a;
			this.next = b;
			this.cost = c;
		}
	}
	
	//######### arr_print #########################
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}