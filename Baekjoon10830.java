import java.io.*;
import java.util.*;
/*  행렬 제곱 10830 120
 *  
 *  분할정복을 이용한 거듭 제곱
 *  제곱값이 짝수면 제곱값 바로 넘기기 -> A*(B/2) 반복
 *  홀수면 제곱값*원본값 한 후 넘기기 -> A*(B/2) 반복 후 ==> res *A
 *  
 *  **M은 Long형태로 받아야함. 
 */
class Baekjoon10830 { 
	static int N,M;
	static int[] arr;
	static int[] num;
	static StringBuilder sb;
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        int[][] arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken())%1000;
            }
        }
        
        for(int[] i:req(M, arr)) {
        	for(int j:i) {
        		bw.write(j+" ");
        	}
        	bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
	}
	
	//행렬 곱셈 계산
	static int[][] cal(int[][] a, int[][] b) {
		int[][] ret = new int[N][N];
		for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		for(int k=0; k<N; k++) {
        			ret[i][j] += a[i][k]*b[k][j];
        			ret[i][j] %= 1000;
        		}
            }
        }
		return ret;
	}
	
	//분할정복 거듭제곱 구하기
	static int[][] req(long b, int[][] arr) {
		if (b == 1) {
			return arr;
		}
		
		int[][] ret = req(b/2, arr);
		ret = cal(ret, ret);
		
		if(b%2==0) return ret; //제곱값이 짝수면 바로 넘기기
		
		return cal(ret, arr); //홀수면 한번더 원본값 곱한 후 넘기기
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