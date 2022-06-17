import java.io.*;
import java.util.*;
/* 다리 놓기 1010 60
 * 수학, 조합론
 * 큰 수 계산 
 */
class Baekjoon1010 { 
	static int[][] sudoku;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        int T = Integer.parseInt(br.readLine());
        long n, r;
        for(int i=1; i<=T; i++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	int cnt = 2;
        	n = M;
        	r = 1;
        	for(int j=M-1; j>M-N; j--) {
        		if(n%cnt==0) {
        			n = n*j/cnt;
        		}
        		else {
        			n *= j;
        			r *= cnt;
        		}
        		cnt++;
            }
        	
        	bw.write(n/r+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
	}
}