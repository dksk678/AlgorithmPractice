import java.io.*;
import java.util.*;

/*  수 찾기  1920 
 *  이분탐색
 */
class Baekjoon1920 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int M;
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	static int[] arr; //처음 주어진 배열
	static boolean[][] visited; //방문한배열 체크
	static int[] arr2; //결과 값 저장된 배열
	static ArrayList<Integer> res2; //결과 값 저장된 배열
//	static int cnt;
	static int cnt2;
	static ArrayList<Integer> res1; //결과 값 저장된 배열
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        arr2 = new int[M];
        for(int i=0; i<M; i++) {
    		arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for(int i=0; i<M; i++) {
    		sb.append(binary(i)).append("\n");
        }
        
        System.out.println(sb); //4319
    }
	private static int binary(int i) {
		int start = 0;
        int end = N-1;
        int mid = (end+start)/2;
		while(end-start>=0) {
			if (arr[mid]==arr2[i]) return 1;
			if(arr[mid]>arr2[i]) {
				end = mid-1;
			} else {
				start = mid+1;
			}
			mid = (end+start)/2;
		}
		return 0;
	}

}