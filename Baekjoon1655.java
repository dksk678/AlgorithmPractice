import java.io.*;
import java.util.*;
/*   가운데를 말해요 1655 60
 * 
 *   priority queue를 사용.
 *   중앙 값을 기준으로 작은 쪽과 큰 쪽을 pq로 만듬
 *   중앙보다 크고 작은 쪽과 사이즈가 같으면 작은 쪽으로 poll 아니면 add 
 *   반대로 중앙보다 작고 사이즈가 더 많으면 맥스로 poll 아니면 add
 *  //중앙을 알기 위해 작은쪽과 큰쪽 사이즈를 계속 비교 
 *  //중앙은 작은 쪽의 가장 큰 수 => 짝수 일 때는 중앙 중 작은 수 이기 때문에
 */
public class Baekjoon1655 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minpq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> maxpq = new PriorityQueue<>();
		
		minpq.offer(Integer.parseInt(br.readLine()));
		sb.append(minpq.peek()+"\n");
		for (int i = 1; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(minpq.peek()<num) {//중앙값보다 num이 크면
				if(maxpq.size()==minpq.size()) {//작은 쪽이 항상 1개 더 많아야 하므로 같으면 min에다가 넣어 준다
					int n = maxpq.peek();
					if(n<num) {
						minpq.offer(maxpq.poll());
						maxpq.offer(num);
					} else {
						minpq.offer(num);
					}
				} else { //큰쪽이 더 적으면 큰쪽에 넣어준다
					maxpq.offer(num);
				}
			} else { //아니면 min에 넣기
				minpq.offer(num);
				if(minpq.size()>maxpq.size()+1) { //min쪽이 2개 더 많으면 중앙값 옮기기.
					 maxpq.offer(minpq.poll());
				}
			}
//			System.out.println(minpq.peek()+" "+(minpq.toString())+ " " +maxpq.toString());
			sb.append(minpq.peek()+"\n");
		}
		
		System.out.println(sb);
	}

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
