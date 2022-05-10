import java.io.*;
import java.util.*;

/*  미세먼지 안녕! 17144 120
 * 
 *  RxC 격자판
 *  1. 미세먼지 확산 모든 칸 동시 (4방향) rc/5 만큼 확산.(소수점 x) 남은 양은 rc-(rc/5)*2
 *  2. 위쪽 공기청정기는 반시계 방향 아래쪽 공기청정기는 시계방향
 *     바람의 방향대로 한칸씩 이동 공기청정기로 들어간 미세먼지 모두 정화
 */

class Baekjoon17144 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static int T;
	static int res;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int ap;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));      
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); //T초가 지난 후 결과 값
        int[][] arr = new int[N][M];
        ap = 0;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if(arr[i][j]==-1&&ap==0) {
        			ap = i;
        		}
        	}
        }
        
        int[][] res = new int[N][M];
        for(int i=0; i<T; i++) {
        	arr = airpurifier(diffuse(arr));
        }

        print(arr);
        
//        System.out.println(res);
	}
	
	static int[][] diffuse(int[][] arr) { //확산
		int[][] tmp = new int[N][M];
		int nr, nc, cnt = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				cnt = 0;
				if(arr[r][c]>=5) {
					for(int k=0; k<4; k++) {
						nr = r+dx[k];
						nc = c+dy[k];
						if(nr<0||nr>=N||nc<0||nc>=M||arr[nr][nc]==-1) continue;
						
						tmp[nr][nc] += arr[r][c]/5;
						cnt++;
					}
				}
				tmp[r][c] += arr[r][c]-((arr[r][c]/5)*cnt);
			}
		}
		return tmp;
	}
	
	static int[][] airpurifier(int[][] arr) {
		int tmp[][] = new int[N][M];  //i==0 left, j==0 down, j==M up, j==ap right 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==-1) { tmp[i][j] = -1; continue;}
				if(i<=ap) { //반 시계 
					if(j==0&&i!=ap) {//down
						if(arr[i+1][j]==-1) continue;//공기청정기 흡수
						tmp[i+1][0] = arr[i][0];
					} else if (i==0) {// left
						tmp[0][j-1] = arr[0][j];
					} else if(j==M-1) {//up
						tmp[i-1][j] = arr[i][j];
					} else if(i==ap) { //right
						if(arr[i][j+1]==-1) continue;//공기청정기 흡수
						tmp[i][j+1] = arr[i][j];
					} else {
						tmp[i][j] = arr[i][j];
					}
				} else { //시계
					if(j==0&&i!=ap+1) {//up
						if(arr[i-1][0]==-1) continue;
						tmp[i-1][0] = arr[i][0];
					} else if (i==N-1) {// left
						tmp[i][j-1] = arr[i][j];
					} else if(j==M-1) {//down
						tmp[i+1][j] = arr[i][j];
					} else if(i==ap+1) { //right
						if(arr[i][j+1]==-1) continue;//공기청정기 흡수
						tmp[i][j+1] = arr[i][j];
					} else {
						tmp[i][j] = arr[i][j];
					}
				}
			}
		}
		
		return tmp;
	}
	
	static void print(int[][] arr) {
		int ans=0;
		for(int[] i: arr) {
			for(int j:i) {
				if(j>=1) {
					ans+=j;
				}
//				System.out.print(j + " ");
			}
//			System.out.println("");
		}
		System.out.println(ans);
	}
}