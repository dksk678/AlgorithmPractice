import java.io.*;
import java.util.*;

/*	요세푸스 문제 0
 *  N K가 주어지고  N명이 원으로 앉아있음 
 *  K번째를 제거 모두 제거될때까지 반복
 * 
 */ 
class Main{
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt=1;
        sb.append("<");
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
        	queue.add(i);
        }
        while(queue.size()>1) { //6-7+k
        	if(cnt%K==0) {
//        		System.out.println(cnt);
        		sb.append(queue.poll()).append(", ");
        	} else {
	        	queue.add(queue.remove());
        	}
        	cnt++;
        }
        
        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }
}