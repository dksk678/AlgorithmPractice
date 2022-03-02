import java.io.*;
import java.util.*;
/*	남은 원판 갯수를 x 라고 함
 *  x가 홀수면 목표에 넣고
    x가 짝수면 목표가 아닌 곳에 넣기
 */ 

class Baekjoon11729{
	static StringBuilder sb = new StringBuilder(); //
	static int n;
	static int cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        hanoi(n, 1, 2, 3); 
        //출력
        System.out.println(cnt);
        System.out.println(sb);
    }
    
    static void hanoi(int n, int a, int b, int c) {
    	if (n==0) {
    		return;
    	}
    	cnt++; //   
    	
    	hanoi(n-1, a, c, b); //짝수 면 2번째에 넣기 (홀수면 재귀함수빠져나와도 처음이랑 동일 그래서 목표인 c로 옮김)
    						 //짝수면 c<->b가 바뀌므로 목표가 아닌 곳에 넣음
    	sb.append(a).append(" ").append(c); //옮기려는 원반 위치와 옮기는 곳 위치 
    	sb.append("\n");
    	hanoi(n-1, b, a, c); // 쌓여있는 원반을 옮김,
    }
}
