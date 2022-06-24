import java.io.*;
import java.util.*;
/*  부분합 1806 30+20
 *  
 *  start, end 부분을 지정 수열합이 S보다 큰 수열 중 가장 작은 수열길이
 *  
 *  sum이 S보다 작으면 end를 늘리고 sum에 저장
 *  sum이 S보다 크면  길이 저장 후 start값 삭제 start++
 *  
 */
class Baekjoon1806 { 
	static int N, S;
	
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
        S = Integer.parseInt(st.nextToken());  
        int[] arr = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken()); 
        }
        
        int start, end, ans, sum;
        start = end = sum = 0;
        ans = 123456789;
        
        while(end!=N) {
        	if(sum+arr[end] < S) {
        		sum += arr[end++];
        	} else {
        		int l = end-start;
        		ans = ans>l?l:ans;
        		if(ans==0) break;
        		sum -= arr[start++]; 		
        	}
        }

        bw.write((ans==123456789?0:(ans+1))+"");

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