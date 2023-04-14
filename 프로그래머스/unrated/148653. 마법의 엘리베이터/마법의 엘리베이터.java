import java.util.*;
/*
절대값이 10의 제곱 형태의 버튼
현재 층 수에 버튼에 적혀있는 값을 더한 층으로 이동
위치 + 버튼이 0이하면 안 움직임
0층이 가장 아래
0층을 가기 위한 최소 이동 횟수

BFS로 가장 최단인 경우 가면 됨.
storey의 자리수를 구해서 올라가는건 자리수 만큼
내려가는 건 자리수+1 만큼
*/

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        int storeyLength = 1;
        int temp = storey;
        while(temp > 9) {
            temp /= 10;
            storeyLength++;
        }
        
        
        for(int i=1; i<=storeyLength; i++) {
            int sm = storey%10;
            int next = storey/10;
            
            
            if(sm == 0) {
                storey = next;
                continue;
            }
            
            if(sm < 5 || (sm==5 && next%10 < 5)) {
                answer += sm;
                storey = next;
            } else {
                answer += 10 - sm;
                storey = next+1;
            }
            
            System.out.println(answer+" "+storey);
        }
        
        System.out.println(answer+" "+storey);
        
        if(storey > 0) {
            if(storey <= 5) {
                answer += storey;
            } else {
                answer += 11 - storey;
            }
        }
        
        return answer;
    }
    
}