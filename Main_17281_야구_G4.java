import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17281_야구_G4 {
	static int[][] hitter;
	static int[] hitnums;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		hitter = new int[N][9]; //타자 점수
		hitnums = new int[8]; //4번 타자를 제외한 타자 순서 담김.
		int h4 = 0; // 4번 타자
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				hitter[i][j] = Integer.parseInt(st.nextToken());
				
				if(j<3) {//4번타자(3)는 고정이기 때문에 1~3,5~9타자 순서 정하기
					hitnums[j] = j+1;
				} else if(j>3) {
					hitnums[j-1] = j;
				}
			}
		}
		int ans = 0;
		//next_perm으로 타자 순서 정하기
		do {
			ans = Math.max(ans, play(h4));
		}while(np(7)); 
		
		System.out.println(ans);
	}

	private static int play(int h4) {
		int outcnt, inning, point, cur, curp;
		inning = point = cur = curp = 0; //이닝, 점수, 현재타자번호, 타자의결과
		boolean chk4 = false;
		
		while(inning!=N) {
			outcnt = 0; //이닝이 바뀔 때마다 아웃카운트 초기화
			boolean[] base = new boolean[4]; //출루 상태
			
			while(outcnt<3) {
				if(cur==3 && !chk4) { //0번타자를 4번 타자로
					curp = hitter[inning][0];
					chk4 = true;
				} else {
					curp = hitter[inning][hitnums[cur]];
					cur = (cur+1)%8; //다음 타자로
					chk4 = false;
				}
				
				if(curp>=1) { //안타 치면
					for (int i = 3; i >= 1; i--) { //3루부터 베이스 전진
						if(base[i]) { //베이스에 타자가 있으면
							if(i+curp>=4) { //점수 추가 후 베이스 초기화
								point++;
								base[i] = false;
							} else { //베이스 이동 후 이전 베이스 초기화
								base[i+curp] = true;
								base[i] = false;
							}
						}
					}
					if(curp==4) point++; //홈런타자는 바로 점수
					else base[curp] = true; //현재 타자를 베이스로
				} else {
					outcnt++;
				}
			}
			
			inning++;
		}
		
		return point;
	}

	private static boolean np(int size) {
		int i = size;
		while(i>0 && hitnums[i-1]>=hitnums[i]) i--;
		if(i==0) return false;
		
		int j = size;
		while(hitnums[i-1]>=hitnums[j]) j--;
		int tmp = hitnums[i-1];
		hitnums[i-1] = hitnums[j];
		hitnums[j] = tmp;
		
		int k = size;
		while(i<k) {
			tmp = hitnums[i];
			hitnums[i++] = hitnums[k];
			hitnums[k--] = tmp;
		}
		
		return true;
	}

}
