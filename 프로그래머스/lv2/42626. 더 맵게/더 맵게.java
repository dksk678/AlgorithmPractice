import java.util.*;

/*
*   PQ를 사용하면 항상 최소숫자를 확인할 수 있음.
    1. PQ에 스코빌 배열을 새로 저장
    2. PQ배열 pop과 K를 비교
    3. PQ길이가 1이 됐는데 K보다 작으면 불가능.
*/

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        //setScovillePQ
        for(int s : scoville){
            pq.offer(s);
        }
        
        //startMixScoville
        while(pq.size() > 1){
            
            // 가장 작은 숫자가 K이상이면 멈춤.
            if(pq.peek() >= K) {
                break;
            }
            
            answer++; //섞는 횟수 증가
            
            //가장 작은 두 수 저장.
            int min1 = pq.poll();
            int min2 = pq.poll();
            
            // 스코빌 섞기
            int newScoville = min1 + min2*2;
            
            pq.offer(newScoville); //새로운 스코빌 값 저장
        }
        
        if(pq.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}