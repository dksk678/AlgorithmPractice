import java.io.*;
import java.util.*;
/*  최적경로  1247 D5  120
 *  
 *  DFS를 통해 완전 탐색 -> 더 빠른 방법 있음(비트마스킹을 이용한 TSP) **
 */
class SWproblem1247 {
	static int N;
	static ArrayList<ArrayList<Integer>> arr;
	static int[][] arr2;
	static int sx, sy, ex, ey, min;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {   //set
        	arr = new ArrayList<ArrayList<Integer>>();
            N = Integer.parseInt(br.readLine());
            
            for(int i=0; i<N; i++) {
            	arr.add(new ArrayList<Integer>());
            }
            arr2 = new int[N][2];
            //최댓값 구하기
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());
            min = 123456789;
            for(int i=0; i<N; i++) {
            	int x = Integer.parseInt(st.nextToken());
            	int y = Integer.parseInt(st.nextToken());
            	arr2[i][0] = x;
            	arr2[i][1] = y;
//            	arr.get(from).add(to);
            }

            boolean[] v = new boolean[N];
            DFS(sx, sy, 0, 0, v);
            
            bw.write("#"+test_case+" ");
            bw.write(min+"");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static void DFS(int cx, int cy, int cnt, int dis, boolean[] v) {
    	if(cnt == N) {
    		int dis2 = Math.abs(ex-cx)+Math.abs(ey-cy);
    		min = Math.min(min, dis+dis2);
    		return;
    	}
    	for(int i=0; i<N; i++) {
    		if(v[i]) continue;
    		
    		v[i] = true;
    		DFS(arr2[i][0], arr2[i][1], cnt+1, dis+Math.abs(cx-arr2[i][0])+Math.abs(cy-arr2[i][1]), v);
    		v[i] = false;
    	}
    	
    	return;
    }
    
    //###########Array print#################
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