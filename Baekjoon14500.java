import java.io.*;
import java.util.*;

/*  테트로미노 14500
 *  n,m 위치에서 가장 큰 값을 구하기
 *  ㅗ 도형을 제외한 나머지 4개의 도형은 BFS로 4번 검사하면 됨
 *  ㅗ 도형의 형태는 따로 계산
 */

class Main {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int M;
	static int T;
	static int[][] arr;
	static int MAX;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][][] ㅜ = {{{1,1},{0,1},{0,2}},{{-1,1},{0,1},{0,2}},{{1,-1},{1,0},{2,0}},{{1,0},{1,1},{2,0}}};
	static boolean[][] v;

	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M]; // 2,3,5
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        v = new boolean[N][M];
    	MAX = 0;
    	
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<M; j++) {
	    		v[i][j] = true;
	    		DFS(i,j,1,arr[i][j]);
	    		v[i][j] = false; //다음 위치에서 전의 위치를 탐색할 수 있기 때문에 초기화
	    	}
	    }
	    
	    System.out.println(MAX);
    }
	
	private static void DFS(int x, int y, int depth, int sum) {
		if(depth==4) {MAX=MAX<sum?sum:MAX; return;} //Max값과 비교
		
		int nx = 0;
		int ny = 0;
		int s = sum;
		if(depth==1) { //길이가 1일 때 ㅗ모양 4개 값 구하기
			for(int i=0; i<4; i++) {
				s = sum;
				for(int j=0; j<3; j++) {
					nx = x+ㅜ[i][j][0];
					ny = y+ㅜ[i][j][1];
					if(0<=nx&&nx<N&&0<=ny&&ny<M&&!v[nx][ny]) {
						s += arr[nx][ny];
					}
				}
				DFS(nx, ny, 4, s);
			}
		}
		
		int d = depth;
		
		for(int i=0; i<4; i++) { //나머지
			nx = x+dx[i];
			ny = y+dy[i];
			if(0>nx||nx>=N||0>ny||ny>=M) continue;
			if(!v[nx][ny]) {
				v[nx][ny]=true;
				DFS(nx, ny, d+1, sum+arr[nx][ny]);
				v[nx][ny] = false;//다음도형에서도 확인할 수 있기 때문에 다시 초기화
			}
		}
	}
}