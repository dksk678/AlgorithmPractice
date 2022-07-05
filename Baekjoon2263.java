import java.io.*;
import java.util.*;
/*  트리의 순회 2263  180~ (*처음부터 구현하라고 하면 못할듯)
 * 
 *  post오더의 특징상 맨 오른쪽값이 루트임.
 *  1. 루트 값 구한 후 출력, in오더 기준 새로운 루트 찾기
 * 
 *  반복.
 */
class Baekjoon2263 { 
	static int N,M;
	static int[] in;
	static int[] post;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        
        //set
        N = Integer.parseInt(br.readLine());
        in = new int[N];
        post = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	in[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	post[i] = Integer.parseInt(st.nextToken());
        }
        
        getPreOrder(0, N-1, 0, N-1);
        
        bw.write(sb+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void getPreOrder(int inL, int inR, int poL, int poR) {
		
		if(poL>poR) return;
		
		int par = post[poR];
		int p = 0; //위치 
		
		sb.append(par+" ");
		
		for(int i=inL; i<N; i++) {
			if(in[i]==par) {
				p = i;
				break;
			}
		}
		//0 5 0 5 에서  0 1 0 1 이 됨
		getPreOrder(inL, p-1, poL, poL+p-inL-1); //새로운 루트 기준 왼쪽 탐색
		getPreOrder(p+1, inR, poL+p-inL, poR-1); //루트 오른쪽 탐색
		
	}
	
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