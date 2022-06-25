import java.io.*;
import java.util.*;
/*  인구 이동 16234 70
 *  
 *  1. 0,0 부터 탐색 시작
 *  2. 조건이 성립하면 값이 바뀌었으니 chk해줌.
 *  3. chk==true면 다시 1로 돌아감 -> ans++(횟수)
 *  4. 0,0 부터 끝까지 조건 달성 안되면 멈춤
 *  
 *  조건이 성립하는 기준 = 절대값이 L보다 크거나 R보다 작아야함.
 *  연결된 나라들의 값과 위치를 저장. 해당 위치에 평균 값으로 변경
 */
class Baekjoon16234 { 
	static int N, L, R;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] arr;
	static int ans = 0;
	
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
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int ans = 0;
        
        while(BFS()) { //값이 바꼈는 지 확인.  바꼈으면 처음 부터 진행
        	ans++;
        }

        bw.write(ans+"");

        bw.flush();
        bw.close();
        br.close();
	}
	
	static boolean BFS() {
		Queue<int[]> q = new LinkedList<int[]>(); //연결된 나라 저장
		boolean[][] v = new boolean[N][N];
		boolean chk = false;
		ArrayList<int[]> mArr = new ArrayList<int[]>();
		int sum = 0;
		
		for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(v[i][j]) continue;
    			
    			q.offer(new int[] {i, j});
    			v[i][j] = true;
    			
    			mArr = new ArrayList<int[]>();
    			sum = 0;
    			
    			while(!q.isEmpty()) { //q에 저장된 값들은 연결된 나라들
					int x =  q.peek()[0];
					int y =  q.poll()[1];
    				
					sum+=arr[x][y]; //연결된 나라의 값 저장
					mArr.add(new int[] {x, y}); //연결된 나라의 위치 저장
					
					for(int k=0; k<4; k++) {
		        		int nx = x+dx[k];
		        		int ny = y+dy[k];
		        		
		        		if(nx<0 || nx>=N || ny<0 || ny>=N || v[nx][ny]) continue;
		        		int c = arr[x][y]-arr[nx][ny];
		        		if(L > Math.abs(c) || R < Math.abs(c)) continue;
		        		
		        		v[nx][ny] = true;
		        		chk = true;
		        		q.offer(new int[] {nx, ny});
					}
        		}
    			int l = mArr.size();
				for(int[] a:mArr) {
        			arr[a[0]][a[1]] = sum/l;
        		}
			}
		}
		
		if(chk) return true; // 값이 바꼈으면 
		
		return false; //바뀐게 없으면 멈춤
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