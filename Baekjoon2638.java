import java.io.*;
import java.util.*;
/*  치즈 2638  90
 *  
 *  1. BFS를 통해 공기인 구역 찾기
 *  2. 공기 2개이상 접촉한 치즈 찾은 후 다음에 공기로 바꿈
 *  3. 남아있는 치즈조각 개수 확인
 *  4. 치즈조각 개수가 0일 때 까지 반복
 *  
 *  치즈 2636에서 2개 이상 접촉했는 지 체크 
 */
class Baekjoon2638 { 
	static int N,M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] arr;
	static boolean[][] v;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
//        sb = new StringBuilder();
        
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans1 = 0; //시간
        
        while(true) {
        	if(chkCheeze()==0) break; //남아있는 치즈조각 확인

        	BFS(); //녹을 수 있는 치즈 찾고 공기로 바꿈
        	ans1++;
        }
        
        bw.write(ans1+"\n");
        bw.flush();
        bw.close();
        br.close();
	}
	//치즈조각 개수와 다 녹았는 지 확인
	static int chkCheeze() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int cur = arr[i][j];
				if(cur>=3||cur==-1) { //다음 공기가 될 곳
					arr[i][j] = 0;
				} else if(arr[i][j]!=0){
					cnt++;
					arr[i][j] = 1;
				}
			}
		}
		return cnt;
	}
	//구멍 찾기(녹을 수 있는 치즈 조건 찾기)
	static void BFS() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0, 0});
		arr[0][0] = -1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx<0||nx>=N||ny<0||ny>=M||arr[nx][ny]==-1) continue;
				
				if(arr[cur[0]][cur[1]]==-1&&arr[nx][ny]>=1) {//공기와 닿아있는 치즈를 만나면 
					arr[nx][ny]++;
				} else { //현재 공기임.
					arr[nx][ny] = -1;
					q.add(new int[] {nx, ny}); 
				}
			}
		}
	}
	
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