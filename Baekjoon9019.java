import java.io.*;
import java.util.*;

/*  DSLR 9019 90
 *  
 *  D는 n값을 2배로 -> 9999보다 크면 10000으로 나눈 나머지 저장
 *  S는 n에서 1뺀값 저장
 *  L은 왼편으로 회전. 
 *  R은 오른편으로 회전
 *  
 *  ** 현재가 0일 때 D를 실행 안하게 해야함. (실수로 next가 0일 때로 적음)
 *  *** 정답 나오면 바로 탈출(시간 줄이기)
 */
class Baekjoon9019 {
	static StringBuilder sb;
	static String[] arr;
	static String[] dslr = {"D", "S", "L", "R"};
	static int A;
	static int B;
	static int T;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        T = Integer.parseInt(bf.readLine());
        while(T-->0) {
        	st = new StringTokenizer(bf.readLine());
	        A = Integer.parseInt(st.nextToken());
	        B = Integer.parseInt(st.nextToken());

	        arr = new String[10001];
	        Arrays.fill(arr, "");
	        BFS(A);
	        sb.append(arr[B]+"\n");
        }
        System.out.println(sb);
    }
	
	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		int next=0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(String s:dslr) {
				next = DSLR(s, cur);
				if(next==B) { arr[next] = arr[cur]+s; return; } //***
				if(cur==0&&s.equals("D")||arr[next]!="") continue; //**
				
				arr[next] = arr[cur]+s;
				q.offer(next);
			}
		}
	}
	static int DSLR(String s, int a) {
		int num = a;
		switch(s){
			case "D":
				return num*2%10000;
			case "S":
				return num==0?9999:num-1;
			case "L": //11->110
				return num==9999?9999:(num*10)%9999;
			default: //111->1011, 1011-> 1101
				return num%10*1000 + num/10;
		}
	}
}