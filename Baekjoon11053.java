import java.io.*;
import java.util.*;

/*  가장 긴 증가하는 부분 11053  16
 *  
 *  중복가능한 수열 A 가 주어짐.
 *  증가하는 부분 수열의 가장 긴 길이를 구하기.
 *  
 *  배열에서 첫 배열이 1로 시작. 다음게 더 작으면 더 작은 값이 있는 배열의 cnt 가져오기. 없으면 그 위치에 +1
 *  
 *  min 변수하나
 *  A저장할 배열 하나,
 *  
 *  1 10 2 3 4 5 11
 * -> 1, 2, 1+1,  
 * 
 *  
	바텀-업
	
 */
class Baekjoon11053 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int K;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        int[] num = new int[N]; //지금값과 현재값.
        int[] cnt = new int[N]; //지금값과 현재값.
        
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        cnt[0] = 1;
        int p = 0;
        for(int i=1; i<N; i++) { //
        	cnt[i] = 1;
        	for(int j=i-1; j>=0; j--) {
	        	if(num[i]>num[j]) { //3, 10
	        		if(cnt[i]<cnt[j]+1) {
	        			cnt[i] = cnt[j]+1;
	        		}
	        		if(max<cnt[i]) {
	        			max=cnt[i];
	        		}
	        	} 
        	}
        }
//        6
//        1 10 2 3 4 11
        // 1 2 3  11 12 13 6 7 8 9 10 
        System.out.println(max);
    }
	
}