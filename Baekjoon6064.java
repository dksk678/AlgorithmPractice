import java.io.*;
import java.util.*;

/*  카잉 달력 6064
 *  중국인의 나머지 정리
 */

class Baekjoon6064 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int T;
	static int M;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        
        T = Integer.parseInt(bf.readLine());
        
        while(T-->0) {
	        st = new StringTokenizer(bf.readLine());
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());

	        int y1=x;
	        int res = x;
	        boolean chk = false;
	        
	        if(x==y) {sb.append(x).append("\n"); continue;} //x랑 y랑 같을 경우
	        int lcm = (N*M)/GCD(N<M?M:N, N<M?N:M);
	        while(res<=lcm) {
	        	y1 = y1%M; 
	        	y1 = y1==0?M:y1;
	        	if(y1==y) {sb.append(res).append("\n"); chk=true; break;}
	        	res += N;
	        	y1 += N;
	        }
	        if(!chk) sb.append(-1).append("\n");
        }

        System.out.println(sb);
    }
	
	static int GCD(int N, int M) {
		if(N%M==0) {
			return M;
		}
		return GCD(M, N%M);
	}
}