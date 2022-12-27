import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/* 돌 던지기 p4 180
 * 처음 던진 돌 위치에 돌이 지나간 경로를 저장해서 같은 위치에서 던지면 경로를 가져와서 비교해본다.
 */
public class Main {
	static char[][] map;
	static int[][] dp;
	static ArrayList<Stack<int[]>> stack;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //맵 크기
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M]; //맵
		
		//set map
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		//돌 던지는 횟수
		int rockcnt = Integer.parseInt(br.readLine());
		
		//이전 위치 정보를 저장하기 위한 배열
		stack = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			stack.add(new Stack<>());
		}
		//날아올 돌 만큼 반복 
		for (int i = 0; i < rockcnt; i++) {
			int c = Integer.parseInt(br.readLine())-1;
			if(stack.get(c).isEmpty()) {
				play(0, c, c, N, M);
			} else {
				play(stack.get(c).peek()[0], stack.get(c).peek()[1], c, N, M); //돌 위치와, 맵 크기
			}
		}
		
		//print
		for (char[] cc:map) {
			for (char ccc:cc) {
				sb.append(ccc);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void play(int r, int c, int sc, int n, int m) {
		if(stack.get(sc).isEmpty()) { //스택이 비어있으면 현재 위치 저장.(처음 떨어뜨리는 돌이면)
			stack.get(sc).add(new int[] {r, c});
		} else {
			while(!stack.get(sc).isEmpty() && map[r][c]=='O') {//현재 위치에 돌이 있으면 이전 위치로 r, c변경해줌.
				stack.get(sc).pop();
				r = stack.get(sc).peek()[0];
				c = stack.get(sc).peek()[1];
			} 
		}
		//현재 위치가 돌멩이가 아니고 왼쪽이나 오른쪽으로 갈 수 있는 지
		while(r<n) { //r이 마지막줄로 내려올 때 까지 반복
			int nr = r+1;
			//마지막줄이거나 벽만나면 그위에다 O표시
			if(nr==n || (map[nr][c]=='X' && map[r][c]!='O')) {
				map[r][c] = 'O';
				return; 
			} else if(map[nr][c]=='O'){
				//왼쪽과 왼쪽아래가 비어 있으면
				if(c-1>=0 && map[r][c-1]=='.' && map[nr][c-1]=='.') {
					c--; //왼쪽아래로 이동
					r++;
				} else if(c+1<m && map[r][c+1]=='.' && map[nr][c+1]=='.') { //오른쪽으로 비교
					c++; //오른쪽 아래로 이동
					r++;
				} else { //못가면 그 자리 고정
					map[r][c] = 'O';
					return;
				}
			} else { //아래에 아무것도 없으면 아래로
				r++;
			}
			
			stack.get(sc).add(new int[] {r, c}); //돌 던진 위치마다 이동경로 저장.
		}
	}

}