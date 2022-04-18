import java.io.*;
import java.util.*;

/*  아기 상어 16236
 *  
 *  M마리의 물고기, 1마리의 아기상어, 한칸에는 최대 1마리
 *  
 *  아기상어크기 2시작, 1초에 상하좌우 가능. 큰 물고기칸은 지나갈 수 없음
 *  
 *  1.먹을 수 있는 물고기 없으면 끝 (같아도 못먹음. 지나만 갈 수 있음.)
 *  2. 1마리면 그 물고기 먹으러 감
 *  3. 1마리보다 많으면 가장 가까운 물고기 먹으로 감
 *  4. 같은 거리면 위쪽 -> 그래도 똑같으면 -> 왼쪽
 *  5. 크기와 같은 수의 먹이를 먹을 때 마다 크기 +1
 */

class Baekjoon16236 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int[][] arr;
	static int[] dx = {-1,0,0,1}; //상 좌 우 하
	static int[] dy = {0,-1,1,0};
	static Queue<Node> q;
	static int[][] arr2;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        arr2 = new int[N][N];
        q = new LinkedList<Node>();
        int a = 0;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<N; j++) {
        		a = Integer.parseInt(st.nextToken());
        		if(a==9) q.offer(new Node(i, j, 0));
        		else arr[i][j] = a;
        	}
        }

        System.out.println(BFS());
    }

	private static int BFS() {//플로이드-와샬
		int shark = 2;
		int cx, cy, nx, ny;

		int cd = 0;
		int eat = 0; //먹은 물고기 개수
		int res = 0; //결과(시간)

		ArrayList<int[]> fish = new ArrayList<int[]>(); //먹을 수 있는 물고기들 정보를 담은 배열
		
		boolean[][] v = new boolean[N][N];
		while(!q.isEmpty()) {
			cx = q.peek().x;
			cy = q.peek().y;
			cd = q.poll().dist;
			v[cx][cy] = true;

			for(int i=0; i<4; i++) {
				nx = cx+dx[i];
				ny = cy+dy[i];
				
				if(0>nx||N<=nx||0>ny||N<=ny||arr[nx][ny]>shark||v[nx][ny]) continue;

				q.offer(new Node(nx, ny, cd+1));
				v[nx][ny] = true;

				if(arr[nx][ny]!=0&&arr[nx][ny]<shark) { //먹을 수 있으면 fish 증가
					fish.add(new int[] {nx, ny, cd+1});
				}
			}
			
			if(!fish.isEmpty()&&q.size()==1) {
				Collections.sort(fish, (o1, o2) -> { //거리 > 열 > 행
					if(o1[2] != o2[2])  return o1[2]-o2[2];
					else if(o1[0] != o2[0])  return o1[0]-o2[0];
					else return o1[1]-o2[1];
				});
	
				arr2[fish.get(0)[0]][fish.get(0)[1]] = fish.get(0)[2];
				res += fish.get(0)[2];
				
				q = new LinkedList<Node>(); //먹으면 현재 위치, 거리 초기화
				q.offer(new Node(fish.get(0)[0], fish.get(0)[1], 0));
				v = new boolean[N][N];
				
				arr[fish.get(0)[0]][fish.get(0)[1]] = 0; //먹힌 물고기 위치 -> 0

				eat++; //먹은 수 증가
				if(eat==shark) {eat=0; shark++;} //먹은 수가 크기만큼되면 먹은 수 초기화, 크기 증가
				
				fish.clear();
			}
		}
		return res;
	}
	
	static class Node {
		int x;
		int y;
		int dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}