import java.io.*;
import java.util.*;
/*   스도쿠 2580 150
 * 
 *   백트래킹
 *   값이 없는(0) 곳에 1~9 값을 넣고 가로, 세로, 대각선 비교 전부 통과되면 값 저장
 *   통과 안되면 맞지 않기 때문에 0으로 초기화 해주고 백트랙
 */
class Baekjoon2580 { 
	static int[][] sudoku;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        sudoku = new int[9][9];
        for(int i=0; i<9; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<9; j++) {
        		sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        DFS(0, 0);

        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void DFS(int x, int y) {
		if(y==9) {
			if(x==8) {
				print(sudoku);
				return;
			}
			DFS(x+1, 0);
			return;
		}

		if(sudoku[x][y]==0) {
			for(int n=1; n<=9; n++) {
				if(checkColRow(n, x, y)) {
					if(check3x3(n, x, y)) {
						sudoku[x][y] = n;
						DFS(x, y+1);
					}
				}
			} 
			sudoku[x][y]=0; // 맞는 수가 없으면 0으로 바꿔줌
			return;
		}
		
		DFS(x, y+1);
	}
	
	static boolean checkColRow(int num, int x, int y) {	
		for(int i=0; i<9; i++) {
			if(num == sudoku[x][i]||num == sudoku[i][y]) {
				return false;
			}
		}

		return true;
	}
	
	static boolean check3x3(int num, int x, int y) { //x가로 비교
		int x2, y2;
		x2 = x/3*3;
		y2 = y/3*3;
		
		for(int i=x2; i<x2+3; i++) {
			for(int j=y2; j<y2+3; j++) {
				if(i==x&&j==y) continue;
				if(num == sudoku[i][j]) return false;
			}
		}
		
		return true;
	}
	

//	static void print(int[] arr) {
//		for(int i:arr) {
//			System.out.print(i+" ");
//		}
//		System.out.println("");
//	}
	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				sb.append(j+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		System.exit(0);
	}
}