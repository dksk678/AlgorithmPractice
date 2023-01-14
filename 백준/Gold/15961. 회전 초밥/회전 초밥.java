import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/* 1. K개를 연속으로 먹을 경우 할인
 * 쿠폰 발행, 1번 행사에 참여할 경우 추가로 무료 제공.
 * 가능한한 다양한 종류의 초밥 먹으려함.
 * 
 * Queue에다가 먼저 담고 q 빼기 반복.
 * int[] v 배열로 어떤 접시가 들어왔는 지 확인.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //접시 N
		int d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); //연속 접시
		int c = Integer.parseInt(st.nextToken()); //쿠폰번호
		
		int[] plate = new int[N];
		for (int i = 0; i < N; i++) {
			plate[i] = Integer.parseInt(br.readLine());
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		int[] chkplate = new int[d+1];
		for (int j = 0; j < k; j++) { //처음 K접시 먹기 
			int p = plate[j];
			q.offer(p);
			chkplate[p]++;
		}
		
		chkplate[c]++;
		int ans = getCount(d, chkplate);
		int max = ans;
		
		for (int i = k, s=N+k-1; i < s; i++) { //K번째 부터 모든 경우 
			if(--chkplate[c]==0) ans--; //쿠폰 접시 제거
			
			if(i>=N) {
				if(--chkplate[q.poll()]==0) ans--;
				
				int p = plate[i-N];
				q.offer(p);
				
				if(++chkplate[p]==1) ans++;
			} else {
				if(--chkplate[q.poll()]==0) ans--; //앞접시 빼기 남은 접시가 없으면 빼기
				
				int p = plate[i]; //다음 접시 담기
				q.offer(p);
				
				if(++chkplate[p]==1) ans++; //새로운 접시면 카운트+
			}
			
			if(++chkplate[c]==1) ans++; //쿠폰 접시 추가
			
			max = max<ans?ans:max;
		}
		
		System.out.println(max);
	}

	private static int getCount(int d, int[] chkplate) {
		int cnt = 0;
		for (int i = 1; i <= d; i++) {
			if(chkplate[i]>=1) cnt++;
		}
		return cnt;
	}

}