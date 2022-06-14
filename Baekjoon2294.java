import java.io.*;
import java.util.*;
/*   동전 2 2294  30+반례(10)
 *   
 *   1. 사용할 수 있는 동전 위치는 무조건 +1
 *   2. j와 i-j위치의 합을 구한 후 최소값 저장(ex 6원이면 1,5 2,4 3,3)
 *   2-2 최소값이 0이면 방법이 없는 것이므로 건너 뜀
 *   3. i위치에 최소값 저장
 *   ** 동전의 최대값은 10만(구하고자하는 k값은 1만)
 *   *** 불가능한 경우 -1 출력(까먹음..) -> 마지막이 99999999면 -1 출력
 */
class Main { 
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[1000001];

        for(int i=0; i<n; i++) {
        	dp[Integer.parseInt(br.readLine())] = 1;
        }

        int min;
        for(int i=1; i<=k; i++) {
        	min = 99999999;
        	for(int j=0; j<=i/2; j++) {
        		if(dp[j]+dp[i-j]==0) continue; 
        		min = Math.min(dp[j]+dp[i-j],min);
        	}
        	dp[i] = min;
        }
        int ans = dp[k];
        bw.write((ans==99999999?-1:ans)+"");
        bw.flush();
        bw.close();
        br.close();
	}

//	static void print(int[] arr) {
//		for(int i:arr) {
//			System.out.print(i+" ");
//		}
//		System.out.println("");
//	}
	
//	static void print(int[][] arr) {
//		int cnt=0;
//		for(int[] i:arr) {
//			for(int j:i) {
//				if(j==2) cnt++;
//				System.out.print(j+" ");
//			}
//			System.out.println("");
//		}
//		System.out.println(cnt);
//	}
}