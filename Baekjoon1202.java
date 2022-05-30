import java.io.*;
import java.util.*;
/*  보석 도둑 1202 80
 *  보석, 배낭 무게 순으로 정렬
 *  
 *  1.가장 적은 무게의 배낭에 담길 수 있는 보석 찾기
 *  -> 다음에 올 배낭은 1보다 항상 무거운 걸 담을 수 있기 때문에 1에서 저장된 보석 사용 가능
 *  
 *  **우선순위 큐 내림차순으로 정렬해야함..
 */
class Baekjoon1202 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        int[][] j = new int[N][2];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	j[i][0] = Integer.parseInt(st.nextToken());
        	j[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] bag = new int[K];
        for(int i=0; i<K; i++) {
        	bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);
        Arrays.sort(j, (o1,o2)->{
        	return o1[0]-o2[0];//무게가 낮은 순
        });

        long sum = 0;
        int idx = 0;
    	for(int i=0; i<K; i++) { //가방무게가 낮은 것 부터 높은 순으로
    		while(idx<N && bag[i]>=j[idx][0]) {//가방에 담길 수 잇는 모든 보석 저장(가치 순) -> 다음에 오는 건 항상 담길 수 있음
    			pq.offer(j[idx++][1]);
    		}
    		if(!pq.isEmpty()) sum += pq.poll(); //가방에 담길 수 있는 보석 중 가장 가치가 높은 것 더해줌
        }

        bw.write(sum+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
}