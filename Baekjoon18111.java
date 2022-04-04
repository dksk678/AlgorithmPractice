import java.io.*;
import java.util.*;

/*  마인크래프트 18111 16:05~
 *  
 *  땅 크기 NxM이 주어짐
 *  B는 인벤토리에 흙이 들어있는 개수
 *  
 *  1. 기준인 블록 보다 더 높으면 블록 제거, 제거한 블록 저장.
 *  2. 기준인 블록보다 낮으면 인벤토리에있는 블록 꺼내서 블록 쌓기
 *  
 */
class Baekjoon18111 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        
        int N, M, B;
        int max = 0;
        int min = 257;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[N][M]; 
        
        //입력저장 && 최고높이 최저높이 구하기.
        int h = 0;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		h = Integer.parseInt(st.nextToken());
        		arr[i][j] = h;
        		if(h>max) { //123
        			max = h;
        		}
        		if (min>h) {
        			min = h;
        		}
        	}
        }

        int time=0;
        int resTime=2000000000;
        int resHeight = 0;
        int block = 0;
        
        //최저높이부터 최고높이 까지 모든 경우의 수 찾기
        for(h=min; h<=max; h++) {
        	time=0;
        	block=B;
        	for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
            		if(arr[i][j]<h) { //1. h가 더 높으면 빼기     //63, 64
            			block-=h-arr[i][j];
            			time += (h-arr[i][j]);
            		} else if(arr[i][j]>h) { //2. h(현재 블록이)다른블록보다 더작으면 넣기.
            			block+=arr[i][j]-h;
            			time += (arr[i][j]-h)*2;
            		}
//            		System.out.println(time);
            	}
        	}
        	if(block>=0&&resTime>=time) { //블록이 부족하지않고 시간이 짧으면 저장
        		resHeight = h;
        		resTime = time;
        	}
        }
        System.out.println(resTime+" "+resHeight);
        
    }
}