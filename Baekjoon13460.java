import java.io.*;
import java.util.*;
/*  구슬 탈출2 13460 150+40(반례)
 *  
 *  큐에 빨간 구슬과 파란 구슬, 굴린 횟수를 저장
 *  다른 구슬과 겹쳐질 수 없음.
 *   -> 오른쪽으로 굴릴 때 오른쪽에 있는 구슬 먼저 굴림
 *  파란 구슬이 빠지는 경우 바로 -1하면 안됨. 다른 경우의 수로 빨간 구슬만 넣을 수 있음.
 *  
 *  **조건문 잘못 씀
 *  ***위치 4곳의 방문체크를 활용하여 구현하면 훨씬 빠르게 가능 BFS2
 */
class Baekjoon13460 { 
	static int N, M;
	static int[][] arr;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[] O;
	
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        O = new int[2];
        int rx, ry, bx, by;
        rx = ry = bx = by = 0;
        
        for(int i=0; i<N; i++) {
        	String str = br.readLine();
        	for(int j=0; j<M; j++) {
        		char c = str.charAt(j);
        		arr[i][j] = c;
        		if(c=='R') {
        			rx = i;
        			ry = j;
        		} else if (c=='B') {
        			bx = i;
        			by = j;
        		} else if (c=='O') {
        			O[0] = i;
        			O[1] = j;
        		}
        	}
        }
	    
        bw.write(BFS(rx, ry, bx, by)+"\n");
        bw.write(BFS2(rx, ry, bx, by)+"");

        bw.flush();
        bw.close();
        br.close();
	}
	
	static int BFS2(int rx, int ry, int bx, int by) {
		Queue<bead> q = new LinkedList<bead>();
		boolean[][][][] v = new boolean[N][M][N][M];
		int cnt = 0;
		int[] next = new int[5];
		
		q.offer(new bead(rx, ry, bx, by, 1));
		v[rx][ry][bx][by] = true;
		
		while(!q.isEmpty()) {
			bead b = q.poll();
			rx =  b.rx;
			ry =  b.ry;
			bx =  b.bx;
			by =  b.by;
			cnt =  b.cnt;
			
			if(cnt>10) return -1;
			//0 우, 1 하, 2 좌, 3 상
			for(int i=0; i<4; i++) {
				next = new int[5];
				int nrx = rx;
				int nry = ry;
				int nbx = bx;
				int nby = by;
				boolean redchk = false;
				boolean bluechk = false;
//				System.out.println(nrx+" "+nry+" "+);
				while(arr[nrx+dx[i]][nry+dy[i]] != '#') {
					nrx += dx[i];
					nry += dy[i];
					if(nrx==O[0]&&nry==O[1]) {
						redchk = true;
						break;
					}
				}
				while(arr[nbx+dx[i]][nby+dy[i]] != '#') {
					nbx += dx[i];
					nby += dy[i];
					if(nbx==O[0]&&nby==O[1]) {
						bluechk = true;
						break;
					}
				}
				
				if(bluechk) continue;
				if(redchk) return cnt;
				
        		if(nrx==nbx&&nry==nby) {
        			if(i==0) {
	        			if(rx>bx) nbx-=1;
	        			else nrx -=1;
        			}
        			if(i==1) {
	        			if(ry>by) nby-=1;
	        			else nry -=1;
        			}
        			if(i==2) {
	        			if(rx<bx) nby+=1;
	        			else nry +=1;
        			}
        			if(i==3) {
	        			if(ry<by) nbx+=1;
	        			else nrx +=1;
        			}
        		}
        		
        		if(v[nrx][nry][nbx][nby]) continue;
        		v[nrx][nry][nbx][nby] = true;
        		
        		q.offer(new bead(nrx, nry, nbx, nby, cnt+1));
			}
		}
		return -1;
	}
	
	static int BFS(int rx, int ry, int bx, int by) {
		Queue<bead> q = new LinkedList<bead>();
		q.offer(new bead(rx, ry, bx, by, 0));
		
		int cnt = 0;
		int[] next = new int[5];
		
		while(!q.isEmpty()) {
			bead b = q.poll();
			rx =  b.rx;
			ry =  b.ry;
			bx =  b.bx;
			by =  b.by;
			cnt =  b.cnt;
			
			if(cnt>=10) break;
			//0 우, 1 하, 2 좌, 3 상
			for(int i=0; i<4; i++) {
				next = new int[5];
        		if(i==0) {
        			if(ry>by) { //빨간 구슬이 오른쪽에 있으면 빨간색 먼저
        				next = comp(rx, ry, bx, by, 0, i); //0이면 빨간거 먼저
        			} else {
        				next = comp(rx, ry, bx, by, 1, i); 
        			}
        		}
        		if(i==1) {
        			if(rx>bx) {
        				next = comp(rx, ry, bx, by, 0, i);
        			} else {
        				next = comp(rx, ry, bx, by, 1, i); 
        			}
        		}
        		if(i==2) {
        			if(ry<by) {
        				next = comp(rx, ry, bx, by, 0, i);
        			} else {
        				next = comp(rx, ry, bx, by, 1, i); 
        			}
        		}
        		if(i==3) {
        			if(rx<bx) {
        				next = comp(rx, ry, bx, by, 0, i);
        			} else {
        				next = comp(rx, ry, bx, by, 1, i); 
        			}
        		}
        		if(next[4]==2) continue; //파란구슬이 빠졋을 경우
        		if(next[4]==1) return cnt+1;
        		if(cnt==10) break;
        		q.offer(new bead(next[0], next[1], next[2], next[3], cnt+1));
			}
		}

		return -1; 
	}
	
	static int[] comp(int rx, int ry, int bx, int by, int chk, int i) {
		int[] next = new int[3];
		int nrx, nry, nbx, nby;
		int Ochk = 0;
		if(chk==0) {
			next = move(rx, ry, bx, by, i);
			nrx = next[0];
			nry = next[1];
			if(next[2]==1) {
				Ochk++;
				nrx = nry = 0; //빨간 구슬이 구멍에 빠지면 구슬 위치 초기화
			}
			next = move(bx, by, nrx, nry, i);
			nbx = next[0];
			nby = next[1];
			if(next[2]==1) return new int[] {0, 0, nbx, nby, 2};
		} else {
			next = move(bx, by, rx, ry, i);
			nbx = next[0];
			nby = next[1];
			if(next[2]==1) return new int[] {0, 0, nbx, nby, 2};
			
			next = move(rx, ry, nbx, nby, i);
			nrx = next[0];
			nry = next[1];
			if(next[2]==1) Ochk++;
		}
//		System.out.println("Ochk " + Ochk + " next " + next[2]+" "+i);
		return new int[] {nrx, nry, nbx, nby, Ochk};
	}
	
	static int[] move(int x, int y, int diffx, int diffy, int i) {
		int nx = x;
		int ny = y;
		while(true) {
			nx = nx+dx[i];
			ny = ny+dy[i];
			
			if(nx==O[0]&&ny==O[1]) {
//				System.out.println("goal");
				return new int[] {nx, ny, 1};
			}
			if(nx<=0 || nx>=N-1 || ny<=0 || ny>=M-1 || arr[nx][ny]=='#' || (nx==diffx&&ny==diffy)) break;
		}
		return new int[] {nx-dx[i], ny-dy[i], 0};
	}

	static class bead {
		int rx, ry, bx, by, cnt;
		
		public bead(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
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
}