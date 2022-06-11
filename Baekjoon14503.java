import java.io.*;
import java.util.*;
/*   로봇 청소기 14503 G5 150
 *   
 *   N,M 이 주어지고
 *   시작 위치(r,c)와 보는 방향 d(0:북, 1:동, 2:남, 3:서)
 *   1. 4방향 탐색(바라보는 곳에서 왼쪽(반시계) 방향으로)
 *   2. 4방향 다 안되면 반대 방향으로 전진하면서 4방향 탐색
 *   2-2. 후진하면서 4방향 탐색 중 0발견하면 1. 시작
 *   2-3. 후진하다가 1발견되면 종료
 */
class Baekjoon14503 {
	static int N, M;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] arr; 
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int ans = 1;
        int[] rcd = new int[3];
        arr[r][c] = 2;
        
        while(true) {
        	rcd = find4d(r, c, d);
        	if(rcd!=null) {
        		r = rcd[0];
            	c = rcd[1];
            	d = rcd[2];
            	ans++;
            	arr[r][c] = 2;
        	} else {
        		rcd = back(r, c, d);
        		if(rcd==null) break; //벽만나면 break
        		else {//아니면 현재 위치 저장
        			r = rcd[0];
        			c = rcd[1];
        			d = rcd[2];
        			ans++;
        			arr[r][c] = 2;
        		}
        	}
        }
       
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
	}

	static int[] back(int r, int c, int d) {
        //0->남, 1->서, 2->북, 3->동
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, -1, 0, 1};
		int[] rcd = new int[3];
		
		while(arr[r][c]!=1) {
			r = r+dx[d];
			c = c+dy[d];
			if(r<0||r>=N||c<0||c>=M||arr[r][c]==1) return null; //다음 후진 위치가 1이면 break
			
			//4방향 탐색 중 처음인 곳 발견하면 위치 리턴
			rcd = find4d(r, c, d);
			if(rcd!=null) {
				d = rcd[2];
				return new int[] {rcd[0], rcd[1], d};
			}
		}
		return null;
	}
	
	static int[] find4d(int r, int c, int d) {//4방향 탐색
		for(int i=0; i<4; i++) {
			int nr = r+dx[d];
        	int nc = c+dy[d];
        	// d(0:북, 1:동, 2:남, 3:서) 
        	// 0->3, 1->0,  2->1, 3->2
        	if(d==0) d=4;
	    	d--;
			if((nr<0||nr>=N||nc<0||nc>=M||arr[nr][nc]>=1)) continue;
			
			return new int[] {nr, nc, d};
		}
		return null;
	}
	
	static void print(int[][] arr) {
		int cnt=0;
		for(int[] i:arr) {
			for(int j:i) {
				if(j==2) cnt++;
				System.out.print(j+" ");
			}
			System.out.println("");
		}
		System.out.println(cnt);
	}
}