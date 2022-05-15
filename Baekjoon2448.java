import java.io.*;
import java.util.*;
/*  별 찍기 11  2448 180
 *  
 *  N=12일때  6칸에서 공백 (y=6,18)
 *  N=24일때 12칸 에서 공백(y=12,36)
 *   -> 공백은 N/2일 때 좌우 y=n-n/2, y=n+n/2
 *   
 *   String -> char = 183432->145536, 780->560
 *   StBuilder -> BWriter = 145536->54120, 560->320
 */
class Baekjoon2448 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static char[][] arr;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        N = Integer.parseInt(bf.readLine());
        
        arr = new char[N][N*2-1];
        for(char[] ch: arr) {
        	Arrays.fill(ch, ' ');
        }
        
        DFS(0, N-1, N);
        for(char[] s:arr) {
    		bw.write(s);
        	bw.write("\n");
        }
        bw.flush();
        bw.close();
        bf.close();
//        System.out.println(sb);
	}

	static void DFS(int cnt, int y, int n) {

		if(n>3) { 
			n = n>>1;
			DFS(cnt, y, n); //일반
			DFS(cnt+n, y-n, n); //공백 왼쪽
			DFS(cnt+n, y+n, n); //공백 오른쪽
		}
		else {
			arr[cnt][y] = '*';       
			arr[cnt+1][y-1] = '*';   
			arr[cnt+1][y+1] = '*';
			for(int i=0; i<5; i++) {
				arr[cnt+2][y+i-2] = '*';
			}
		}
	}
}