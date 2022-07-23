import java.io.*;
import java.util.*;
/*   2048(easy) 180~~
 * 	
 * 	 한번 합쳐진 번호는 불가능
 * 	 백트래킹, 구현
 *   오른쪽으로 가는건 오른쪽 블럭 부터 아래로 가는건 아래쪽 블럭 부터
 *   남은 번호 중 중복 되는 번호 없으면 무조건 X
 */
class Baekjoon12100 { 
	static int N;
	static StringBuilder sb;
	//우하좌상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static boolean[][] v;
	static int ans = 1;
	static int max = 0;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        StringTokenizer st;
        sb = new StringBuilder();
        
    	N = Integer.parseInt(br.readLine());
    	int[][] arr = new int[N][N];

    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			int n = Integer.parseInt(st.nextToken());
    			
				arr[i][j] = n;
			}
    	}
    	DFS(0, arr);
    	System.out.println(max);
	}
	
	
	private static void DFS(int cnt, int[][] arr2) {
		if(cnt==3) {
			max = Math.max(max, getMax(arr2));
			print(arr2);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(!chkNum(arr2)) continue;

			int[][] arr3 = new int[N][N];
			for (int s = 0; s < arr3.length; s++) {
				System.arraycopy(arr2[s], 0, arr3[s], 0, arr2.length);
			}
			v = new boolean[N][N];
			switch (i) {
			case 0: { //오른쪽 으로 가는건 오른쪽 부터
				for (int j = 0; j < N; j++) {
					for (int k = N-1; k >= 0; k--) {
						if(arr3[j][k]==0) continue;
						arr3 = combine(j, k, i, arr3);
					}
				}
				break;
			}
			case 1: { //아래쪽으로 가는건 아래부터
				for (int j = N-1; j >= 0; j--) {
					for (int k = 0; k < N; k++) {
						if(arr3[j][k]==0) continue;
						arr3 = combine(j, k, i, arr3);
					}
				}
				break;
			}
			default:
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						if(arr3[j][k]==0) continue;
						arr3 = combine(j, k, i, arr3);
						
					}
				}
			}
			DFS(cnt+1,arr3);
		}
	}
	
 	private static boolean chkNum(int[][] arr) { //남은 숫자 중 한 숫자라도 2개 이상일 때 더 큰 수를 찾을 수 있음.
    	HashMap<Integer, Integer> num = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			for(int j=0; j < N; j++) {
				int cur = arr[i][j];
				if(cur==0) continue;
				if(num.get(cur)!=null) {
					num.put(cur, num.get(cur)+1);
				} else {
					num.put(cur, 1);
				}
			}
		}
		
		for(int i:num.values()) {
			if(i>=2) return true;
		}
		
		return false;
	}

	private static int getMax(int[][] arr2) {
		int m = 0;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				m = Math.max(arr2[i][j], m);
			}
		}
		return m;
	}

	private static int[][] combine(int i, int j, int idx, int[][] arr2) {
		int nr = i;
		int nc = j;
		int cur = arr2[i][j];
		int next = 0;
		//다른 수 나오기 전까지 쭉 진행
		while(!check(nr+dr[idx], nc+dc[idx])) {
			nr +=dr[idx];
			nc +=dc[idx];
			if(arr2[nr][nc]!=0) {
				next = arr2[nr][nc];
				break;
			}
		}
		if(i==nr&&j==nc) return arr2;

		if(next==cur && !v[nr][nc]){ //같은 숫자면 2배 하고 처음 수 0만들고 합쳐진 자리 체크
			cur *= 2;
			v[nr][nc] = true;
			arr2[nr][nc] = cur;
			arr2[i][j] = 0;
		} else if(arr2[nr][nc]==0) {//0이면 자리 체크 안하고 넣기
			arr2[nr][nc] = cur;
			arr2[i][j] = 0;
		} else if(v[nr][nc]) {//체크된 곳이면 전위치에 넣기
			arr2[nr-dr[idx]][nc-dc[idx]] = cur;
			arr2[i][j] = 0;
		} else if(next!=cur) { 
			if(i!=nr-dr[idx]||j!=nc-dc[idx]) {//시작지점이 아니고 같은 숫자도 아니면 
				arr2[nr-dr[idx]][nc-dc[idx]] = cur;
				arr2[i][j] = 0;
			}
		}
		
		return arr2;
	}
	

	private static boolean check(int i, int j) {
		if(i<0||i>=N||j<0||j>=N) return true;
		
		return false;
	}
	



	//######### arr_print #########################
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}