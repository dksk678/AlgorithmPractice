import java.io.*;
import java.util.*;
/*  강의실 배정  11000 30
 *  
 *  S에 시작 T에 끝나느 N개의 수업, 최소 강의실 모든 수업 가능하게
 *  
 *  1. 끝나는 시간을 q에 저장
 *  2. q에 저장된 시간보다 i+1번째의 강의 시작 시간이 적으면 강의실 cnt+1
 *  2-2. 아니면 q.pop (이미 사용된 시간 이므로)
 *  4. 1번으로
 */
class Baekjoon11000 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        int N = Integer.parseInt(br.readLine());

        int[][] time = new int[N][2];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	time[i][0] = Integer.parseInt(st.nextToken());
        	time[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(time, (o1,o2)->{//시간 순 오름차순 정렬
        	if(o1[0]==o2[0]) 
        		return o1[1]-o2[1];
        	return o1[0]-o2[0];
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(time[0][1]);
        int ans = 1;
        for(int i=1; i<N; i++) {
        	if(time[i][0]<pq.peek()) { //끝나는 시간보다 빨리 시작하면
        		ans++; //카운트
        	} else { 
        		pq.poll();
        	}
        	pq.offer(time[i][1]); //다음강의 끝나는 시간 저장
        }

        bw.write(ans+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
}