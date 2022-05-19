import java.io.*;
import java.util.*;
/*  N과 M(9) 15663 90
 *  백트래킹
 */
class Baekjoon15663 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        
        for(int i=0; i<N; i++) {
        	arr[i] =  Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        boolean[] v = new boolean[N];
        int[] arr2=new int[M];
        back(0, arr2, v);
        
        System.out.println(sb);
	}
	static void back(int cnt, int[] a, boolean[] v) {
		if(cnt==M) {
			for(int i:a) {
				sb.append(i+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			if(v[i]||a[cnt]==arr[i]) continue; //자신 불가, 중복 수 불가.
			
			v[i] = true;
			a[cnt] = arr[i];//0=2 
			back(cnt+1, a, v);//1, 2, v[2]
			v[i] = false;
		}
		a[cnt] = 0; //같은 수 있는 경우가 있으므로 해당 위치만 초기화
	}
}