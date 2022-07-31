import java.io.*;
import java.util.*;
/*  멀티탭 스케줄링 1700 70
 * 
 * 	1. 이미 사용 중인 용품일 경우 
 *  2. 사용해야할 용품인데 멀티탭이 남았을 경우
 *  3. 멀티탭이 꽉차서 빼고 꽂아야 하는 경우
 *  
 *  1,2 번은 cnt추가 안해도됨
 *  3번만 추가
 *  
 *  앞으로 사용 될 숫자가 없는 것 1순위
 *  사용 될 횟수 상관없이 바로 앞에 있는 것 2순위
 */
public class Baekjoon1700 {
	static int N, K;
	static Queue<Integer> q;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		q = new LinkedList<Integer>();
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int n = Integer.parseInt(st.nextToken());
			q.add(n);
		}
		
		System.out.println(func());
	}
	 
	static int func() {
		int ans = 0;
		int usedCnt = 0;
		boolean[] used = new boolean[K+1];
		for (int i = 0; i < K; i++) {
			if(used[q.peek()]) { //다음 용품이 이미 사용 중인 경우
				used[q.poll()] = true;
			} else {
				if(usedCnt<N) { //멀티탭을 다 안쓰고 있을 경우 멀티탭에 꽂기만함
					if(!used[q.peek()]) { //다안쓰고 멀티탭에 안꽂아져 있을 경우
						usedCnt++;
						used[q.poll()] = true;
					}
				} else { //빼고 꽂아야 하는 경우
					ArrayList<Integer> ol = new ArrayList<Integer>(); //
					for (int j:q) { //남은 용품 사용 횟수 확인
						if(!used[j] || ol.contains(j)) continue; //현재 사용중이 아니거나 이미 체크한 물품이면 건너 뜀
						ol.add(j);
					}
					if(ol.size()<N) { //남은 용품의 종류가 N보다 작을 때
						for (int j = 0; j < K+1; j++) { 
							if(used[j] && !ol.contains(j)) { //사용 중인 용품이면서, 다음 용품에 없으면
								used[j] = false; //빼기
								break;
							}
						}
					} else { //다음에 계속 사용 될 용품일 경우 가장 마지막에 사용되는 용품을 빼기 
						used[ol.get(ol.size()-1)] = false;
					}
					
					ans++;
					used[q.poll()] = true;//모든 멀티탭이 다음에도 쓸 예정이면 아무거나와 바꿔줘도 됨.
				}
			}
		}
		return ans;
	}
/*
 *  
3 9
1 2 3 4 1 1 1 1 2
2 9
1 3 2 1 2 3 3 3 3
 * 
 */
	//
	
	

	static void print(boolean[][] arr) {
		for(boolean[] i:arr) {
			for(boolean j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
		System.out.println("-----------");
	}
}
