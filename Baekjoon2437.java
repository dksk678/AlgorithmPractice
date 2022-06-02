import java.io.*;
import java.util.*;
/*  저울  2437  90
 *  
 *  1. sum이하는 항상 가능 함
 *  2. sum[i-1] >= w[i] 이면 sum+w 또한 항상 가능함 그러므로 sum += w
 *  3. 2번이 안되면 sum+w+1 이 값이 최솟값임
 *  4. 3번이 될 때 까지 2번 반복
 */
class Baekjoon2437 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());  
        
        int[] weight = new int[N];
        for(int i=0; i<N; i++) {
        	weight[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(weight);
        int sum = weight[0];
        int ans = 0;
        
        if(sum>1) {
        	ans = 1;
        } else {
	        for(int i=1; i<N; i++) {
	        	if(sum+1>=weight[i]) {
	        		sum += weight[i];
	        	} else {
	        		ans = sum+1;
	        		break;
	        	} //다음강의 끝나는 시간 저장
	        }
        } 
        if(ans==0) ans = sum+1;
        bw.write(ans+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
}