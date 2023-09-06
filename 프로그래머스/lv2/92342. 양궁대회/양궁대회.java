import java.util.*;

/*
    info : 어피치 배열
    info, result : 10점부터 ~ 0점 순으로
    n : 사용된 화살 수
    완탐으로 n만큼 반복
    -> 마지막까지 반복해서 가장 큰 값을 구하게 되면 자동적으로 가장 낮은 점수를 많이 맞히게 됨
*/

class Solution {
    public static int[] answer = new int[11];
    public static int answerDiff = 0;
    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        
        getAnswer(0, 0, n, info, lion);
        
        //answer가 변경된 경우가 있으면 그대로 없으면 [-1] 출력
        if(answerDiff == 0) return new int[] {-1};
        
        return answer;
    }
    
    public void getAnswer(int cnt, int dep, int n, int[] info, int[] lion) {
        if(cnt == n) {
            //Lion 배열이 더 큰 경우
            if(isBigLionThanOther(n, info, lion)) { 
                //System.out.println(Arrays.toString(lion));
                answer = lion.clone();
            }
            
            return;
        }
        
        for(int i=dep; i<11; i++) {
            //0점이 아닌 상황에서 이미 lion이 점수가 많은 경우
            if(i<10 && info[i] < lion[i]) continue;
            
            lion[i]++;
            getAnswer(cnt+1, i, n, info, lion);
            lion[i]--;
        }
    }
    
        
    public boolean isBigLionThanOther(int n, int[] info, int[] lion) {
        int infoSum = 0;
        int lionSum = 0;
        
        for(int i=0; i<11; i++) {
            if(lion[i] > info[i]) {
                lionSum += 10 - i;
            } else if (info[i] != 0){
                infoSum += 10 - i;
            }
        }
        
        //새로운 라이언 점수가 더 클 경우 true.
        //System.out.println(infoSum+" "+lionSum+" "+answerSum);
        if(infoSum < lionSum) {
            return isBigThanAnswer(lionSum - infoSum, lion) ? true : false;
        }
        
        return false;
    }
        
    public boolean isBigThanAnswer(int diff, int[] lion) {
        //새로운 라이언 점수가 더 클 경우 true.
        if(diff > answerDiff || 
           (diff == answerDiff && compareMinPoint(lion))) { //같은경우에는 더작은 수 비교
            answerDiff = diff;
            
            return true;
        }
        
        return false;
    }
    
    public boolean compareMinPoint(int[] lion) {
        for(int i=10; i>=0; i--){
            if(lion[i] > answer[i]) return true; //여기서 틀린 느낌?
            
            else if(lion[i] < answer[i]) return false;
        }
        
        return false;
    }
    
}