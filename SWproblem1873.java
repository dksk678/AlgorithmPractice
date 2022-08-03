package java0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* . 갈 수 있음
 * * 벽돌 -> 못감, 파괴됨
 * # 강철 -> 못감, 파괴X
 * - 물 -> 못감. 포탄은 계속
 */
public class Solution_1873_배틀필드 {
	static char[][] map;
	static int H; //맵 크기
	static int W;
	static int curc; //현재 위치
	static int curr;
	//보는 방향
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			int d = 0;
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					char chr = str.charAt(j);
					map[i][j] = chr;
					
					if(chr=='^'||chr=='v'||chr=='<'||chr=='>') { //시작 위치와 방향
						curr = i;
						curc = j;
						switch (chr) {
						case '^':
							d = 0;
							break;
						case 'v':
							d = 1;
							break;
						case '<':
							d = 2;
							break;
						default:
							d = 3;
							break;
						}
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			char[] action = br.readLine().toCharArray();
			
			for (int i = 0; i < N; i++) {
				if(action[i]=='S') {
					shoot(d);
				} else {
					d = move(action[i], d);
				}
			}
			
			sb.append("#").append(t).append(" ");
			for (char[] c:map) {
				for (char cc:c) {
					sb.append(cc);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static void shoot(int d) {
		int bombr = curr+dr[d];
		int bombc = curc+dc[d];
		char nextState;
		while(true) {
			if(checkMap(bombr, bombc)) break;
			
			nextState = map[bombr][bombc];
			
			if(nextState=='#') break;
			if(nextState=='*') {
				map[bombr][bombc] = '.';
				break;
			}
			bombr += dr[d];
			bombc += dc[d];
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int move(char c, int d) {
		switch (c) {
		case 'U':
			d = 0;
			c = '^';
			break;
		case 'D':
			d = 1;
			c = 'v';
			break;
		case 'L':
			d = 2;
			c = '<';
			break;
		default:
			d = 3;
			c = '>';
			break;
		}
		
		if(go(d)) {
			map[curr][curc] = '.';
			curr += dr[d];
			curc += dc[d];
		}
		map[curr][curc] = c;
		
		return d;
	}

	private static boolean go(int i) {
		int nr = curr+dr[i];
		int nc = curc+dc[i];
		if(checkMap(nr, nc))  return false;
		
		char nextState = map[nr][nc];
		if(nextState=='-'||nextState=='*'||nextState=='#') return false;
		
		return true;
	}
	

	private static boolean checkMap(int nr, int nc) {
		if(nr<0||nr>=H||nc<0||nc>=W) return true; //못가면
		return false;
	}
}
