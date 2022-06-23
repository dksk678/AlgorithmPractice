import java.io.*;
import java.util.*;
/*  합분해 2225 50
 *  
 *  2차원 dp
 */
class Baekjoon2225 { 
	static int N, M;
	
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        
        long[][] dp = new long[N+1][M+1];
        for(int i=0; i<M; i++) {
        	dp[0][i] = i+1;
        }
        for(int i=0; i<N; i++) {
        	dp[i][0] = 1;
        }
    	
    	for(int i=1; i<N; i++) {
    		for(int j=1; j<M; j++) {
    			dp[i][j] = (dp[i-1][j]+dp[i][j-1])%1000000000;
    		}
    	}
    	
        bw.write(dp[N-1][M-1]+"");

        bw.flush();
        bw.close();
        br.close();
	}

	static void print(char[][] arr) {
		for(char[] i:arr) {
			for(char j:i) {
				System.out.print(j +"");
			}
			System.out.println("");
		}
	}
}