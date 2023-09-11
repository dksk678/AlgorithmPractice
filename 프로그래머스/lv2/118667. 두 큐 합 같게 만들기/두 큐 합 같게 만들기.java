import java.util.*;

/*
    원소의합을 같게 만들어야 함 => 각 배열의 합을 더한후 / 2
        -> 합이 홀수가 되면 불가능
        
    무조건 pop을 사용해서 숫자 교환을 해야함.
    최소의 pop을 사용해서 합이 같은 경우를 찾아야함.
    -> 완탐
*/
class Solution {
    private static int answer = -1;  // min Count
    
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        Long q1Sum = 0L;
        Long q2Sum = 0L;
        
        for(int i=0,len=queue1.length; i<len; i++){
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            
            q1Sum += queue1[i];
            q2Sum += queue2[i];
        }
        
        if((q1Sum + q2Sum) % 2 == 1) return answer; //합이 홀수인 경우 각 큐 합을 동일하게 만들 수 없음.
        
        findAnswer(q1Sum, q2Sum, q1, q2); //sum12, Queue
        
        return answer;
    }
    
    /**
    * 최소 횟수인 answer 구하기
    */
    public void findAnswer(long q1Sum, long q2Sum, Queue<Integer> q1, Queue<Integer> q2) {
        int qSize = q1.size();
        int end = (qSize * 2) * 2;
        int popCount = 0;
        
        while(q1Sum != q2Sum) {
            if(q1Sum > q2Sum)  {
                int peek = q1.poll();
                q2.offer(peek);
                q1Sum -= peek;
                q2Sum += peek;
            } else if (q1Sum < q2Sum){
                int peek = q2.poll();
                q1.offer(peek);
                q1Sum += peek;
                q2Sum -= peek;
            }
        
            popCount++;
            
            if(popCount > end) {
                return;
            }
        }
        
        answer = popCount;
    }
}