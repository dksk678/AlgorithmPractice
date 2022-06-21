import java.io.*;
import java.util.*;
/*  공유기 설치 2110 120
 *  
 *  이분 탐색
 *  1개의 간격으로 시작 공유 다 쓰면 가장 짧은 간격 저장
 *  N개의 간격 까지 한 후 저장된 간격 중 가장 큰 값 출력
 *  이분탐색으로 간격을 구하면서 해결
 *  
 *  1. 가장 멀리 배치가 1순위
 *  2. 그 중 최소값
 */
class Baekjoon2110 { 
	static int[] arr;
	static int N, C;
	static int ans;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
    	arr = new int[N];
    	for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	Arrays.sort(arr);
    	int start = 1;
    	int end = arr[N-1]-arr[0];
    	int mid = 0; //간격
    	int d = 0;
    	
    	while(start<=end) {
    		int n1 = 1;
    		int cnt = 1;
    		
    		mid = (start+end)/2;
    		
    		for(int i=1; i<N; i++) {
    			int n2 = arr[i];
    			d = n2-n1;
    			if(d>=mid) { //다음 집 거리가 현재 mid(간격)보다 작으면 
    				cnt++; //공유기 설치 
    				n1 = n2; //다음 집 넘어감
    			}
    		}
    		if(cnt<C) { //간격이 커서 공유기를 다 못쓰면
    			end = mid-1;
    		} else {
    			ans = mid;
    			start = mid+1; //더 넓은 간격이 남아 있을 수 있음
    		}
    		
    	}
    	
        bw.write(ans+"");

        bw.flush();
        bw.close();
        br.close();
	}
	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
}