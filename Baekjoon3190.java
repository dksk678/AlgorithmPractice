import java.io.*;
import java.util.*;
/*   뱀 3190 90
 * 
 *   다음 위치에 사과없으면 꼬리 삭제, 사과 있으면 꼬리 삭제x
 *   벽 or 자신과 만나면 종료
 *   
 *   1. 가장 늦게 들어온 값에서 현재 방향 대로 +1
 *   2. 방향에 사과 없으면 가장 먼저 들어온 값 pop
 *   3. 사과 있으면 popX
 *   4. 배열에 있는 값 방향 만큼 전부 전진(꼬리popfirst, 머리부분에서 +1값 addLast)
 *   5. 방향 변환 시간되면 방향 변환 D(현재 방향에서 오른쪽) L(현재 방향에서 왼쪽)
 *   ** 사과 먹으면 삭제
 */
class Baekjoon3190 {
	static int N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] arr; 
	static int[][] turn;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        N = Integer.parseInt(br.readLine());
       
        arr = new int[N][N];
        
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	arr[x-1][y-1] = 1;
        }
        
        int L = Integer.parseInt(br.readLine());
        turn = new int[L+1][2];
        for(int i=0; i<L; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = st.nextToken().charAt(0)-'A'; //3은 오른쪽 , 11은 왼쪽
        	turn[i][0] = x;
        	turn[i][1] = y;
        }

        //방향은 우 하 좌 상 순으로
        int d, time, turntime, length; //오른쪽 부터
        d = time = turntime = 0; //방향, 지난시간, 회전시간
        length = 1; //몸 길이
        
        Deque<int[]> deq = new ArrayDeque<int[]>();
        deq.add(new int[] {0, 0});
        
        int[][] v = new int[N][N];

        while(true) {
        	int[] cur = deq.peekLast();
        	
        	if(time==turn[turntime][0]) {
        		if(turn[turntime][1]==3) { //D면
        			if(d==3) d = -1;
        			d++;
        		} else { //L이면
        			if(d==0) d = 4;
        			d--;
        		}
        		turntime++;//다음 회전 시간
        	}
        	
        	int nx = cur[0]+dx[d];
        	int ny = cur[1]+dy[d];
        	time++;
        	
        	if(nx<0||nx>=N||ny<0||ny>=N) break;
        	if(v[nx][ny]>=1&&v[nx][ny]>=time-length) break;
        	
        	v[nx][ny] = time;
        	deq.addLast(new int[] {nx, ny});
        	
        	if(arr[nx][ny]!=1) { //사과가 없으면 꼬리 줄이기
        		deq.pollFirst(); 
        	} else { //사과가 있으면 몸길이 늘어남
        		arr[nx][ny] = 0;
        		length++;
        	}
        	
        }
       
        bw.write(time+"");
        bw.flush();
        bw.close();
        br.close();
	}

//	static void print(int[][] arr) {
//		int cnt=0;
//		for(int[] i:arr) {
//			for(int j:i) {
//				if(j==2) cnt++;
//				System.out.print(j+" ");
//			}
//			System.out.println("");
//		}
//		System.out.println(cnt);
//	}
}