import java.io.*;
import java.util.*;
/*  행렬 1080 40
 *  
 *  a와 b가 다르면 무조건 뒤집어야함.
 */
class Baekjoon1080
{	
	static StringBuilder sb = new StringBuilder();
	static int mintime=0;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] A = new char[N][M];
		char[][] B = new char[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				A[i][j] = str.charAt(j);
			}
		}
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				B[i][j] = str.charAt(j);
			}
		}
		
		boolean chk = false;
		int cnt=0;
		
		if(Arrays.deepEquals(A, B)) chk = true;
		
		loop : for(int i=0; i<N-2; i++) {
			for(int j=0; j<M-2; j++) {
				if(A[i][j]!=B[i][j]) {
					for(int i2=i; i2<i+3; i2++) {
						for(int j2=j; j2<j+3; j2++) {
							if(A[i2][j2]=='0') A[i2][j2]='1';
							else A[i2][j2]='0';
						}
					}
					
					cnt++;
					if(Arrays.deepEquals(A, B)) {
						chk = true;
						break loop;
					}
				}
			}
		}

		if(!chk) cnt = -1;
		bw.write(cnt+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
} 
