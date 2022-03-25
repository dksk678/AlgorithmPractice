import java.io.*;
import java.util.*;

/*  마법사 상어와 비바라기 20:50~
 * 	N*N 크기에서 연습. 각 칸에 바구니 하나. 
 * r,c는 바구니 위치,  A[r][c]는 저장된 물의 양
 * 왼쪽 위칸 1,1     1--= N, N++ = 1
 * 왼쪽 하단에 2*2 비구름 생성.
 * 
 * 이동을 M번 명령한다. 명령에는 방향 d와 거리 s로 이루어져 있다. 방향은 8방향, 
 * 
 * 1. d방향으로 s칸만큼 이동
 * 2. 이동후 위치에 비가 내림 => 저장된 물의 양 1++
 * 3. 구름 제거
 * 4. 2에서 물이 증가한 칸에 물복사 버그 시전. 
 *  4-1. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 해당 위치에 물의 양 증가
 *  4-2. 이동과는 다르게 경계를 벗어나면 대각선으로 안친다.
 * 5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름 생김, 물의 양 2 빼기.
 *    이때 3에서 제거된 칸은 구름 생성 안된다.
 *  
 */
class Baekjoon21610 {
//	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static int max;
	
	static int[][] d = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; //8방향
	static int[][] digona = {{-1,-1},{-1,1},{1,1},{1,-1}};//대각선
	static int[][] A; //크기
	static LinkedList<int[]> cloud = new LinkedList<int[]>(); //비구름
	static boolean[][] visited; //처음엔 그냥 구름 생성된 곳 계속 찾아서 했는데 시간 초과나옴. 그래서 방문한곳 저장할 배열 생성
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cloud.add(new int[] {N-1,0});
        cloud.add(new int[] {N-1,1});
        cloud.add(new int[] {N-2,0});
        cloud.add(new int[] {N-2,1});
        A = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<N; j++) {
        		A[i][j]=Integer.parseInt(st.nextToken());
        	}
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int D = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
        	
            moveCloud(D, S);
//            addWater();
            startBug();
            addCloud();
//            delWater();
        }
        int res = 0;

        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
            	res += A[i][j];
            }
        }
        System.out.println(res);
    }
	//1. d방향으로 s칸만큼 이동
	private static void moveCloud(int di, int si) {
		for(int[] j : cloud) {
			if(d[di-1][0]*(si%N)+j[0]<0) {
				j[0]=N+d[di-1][0]*(si%N)+j[0];
			} else {
				j[0] = (d[di-1][0]*(si%N)+j[0])%N;
			}
			if(d[di-1][1]*(si%N)+j[1]<0) {
				j[1] = N+d[di-1][1]*(si%N)+j[1];
			} else {
				j[1] = (d[di-1][1]*(si%N)+j[1])%N;
			}
			A[j[0]][j[1]]+=1;//2. 이동후 위치에 비가 내림 => 저장된 물의 양 1++
			visited[j[0]][j[1]]=true; //현재 구름 위치 저장
		}
	}

//	4. 2에서 물이 증가한 칸에 물복사 버그 시전. 
//	 4-1. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 해당 위치에 물의 양 증가
//	 4-2. 이동과는 다르게 경계를 벗어나면 대각선으로 안친다.
	private static void startBug() {
		for(int[] i : cloud) {
			int cnt=0;
			for(int[] j: digona) {
				int y = i[0]+j[0];
				int x = i[1]+j[1];
				if(x<N && x>=0 && y<N && y>=0 && A[y][x]>=1) { //경계선을 안벗어나고 물이 있으면
					cnt++;//카운트
				}
			}
			A[i[0]][i[1]] += cnt;
		}
	}
//	5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름 생김, 물의 양 2 빼기.
//	  이때 3에서 제거된 칸은 구름 생성 안된다.
	private static void addCloud() {
		cloud.clear();//3. 구름 제거
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if ((A[i][j]>=2)&&!(visited[i][j])) { //2이상이고, 
					cloud.addLast(new int[] {i,j});
					A[i][j]-=2;
				} else {
					visited[i][j]=false;
				}
			}
		}
	}
//	private static void delWater() {
//		for(int j=0; j<cloud.size(); j++) {
//			A[cloud.get(j)[0]][cloud.get(j)[1]]-=2;
//		}
//	}
}