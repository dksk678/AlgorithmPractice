import java.util.*;
import java.util.stream.Collectors;

/*
    원소의합을 같게 만들어야 함 => 각 배열의 합을 더한후 / 2
        -> 합이 홀수가 되면 불가능
        
    무조건 pop을 사용해서 숫자 교환을 해야함.
    최소의 pop을 사용해서 합이 같은 경우를 찾아야함.
    -> 완탐, 부분집합
*/
class Solution {
    private static int answer = -1;  // min Count
    
    public int solution(int[] queue1, int[] queue2) {
        Queue<Double> q1 = new ArrayDeque<>();
        Queue<Double> q2 = new ArrayDeque<>();
        
        Double q1Sum = 0d;
        Double q2Sum = 0d;
        
        for(int i=0,len=queue1.length; i<len; i++){
            q1.offer((double)queue1[i]);
            q2.offer((double)queue2[i]);
            
            q1Sum += (double)queue1[i];
            q2Sum += (double)queue2[i];
        }
        
        // Long q1Sum = q1.stream().reduce(0L, (a, b) -> a + b);
        // Long q2Sum = q2.stream().reduce(0L, (a, b) -> a + b);

        
        if((q1Sum + q2Sum) % 2 == 1) return answer; //합이 홀수인 경우 각 큐 합을 동일하게 만들 수 없음.
        
        findAnswer(q1Sum, q2Sum, q1, q2); //sum, Queue
        
        return answer;
    }
    
    /**
    * 최소 횟수인 answer 구하기
    */
    public void findAnswer(double q1Sum, double q2Sum, Queue<Double> q1, Queue<Double> q2) {
        int qSize = q1.size();
        int end = qSize * 4;
        int popCount = 0;
        
        while(q1Sum != q2Sum) {
            
            if(q1Sum > q2Sum)  {
                Double peek = q1.poll();
                q2.offer(peek);
                q1Sum -= peek;
                q2Sum += peek;
            } else if (q1Sum < q2Sum){
                Double peek = q2.poll();
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