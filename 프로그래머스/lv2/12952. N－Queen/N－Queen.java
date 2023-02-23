import java.util.*;
/*
현재 위치, 현재 위치+-깊이위치를 저장하여 올 수 있는지 없는지 체크
이 상황이 n까지 가능해지면 ans+1
n크기의 배열에 갈 수 없는 위치를 계속 체크
*/
class Solution {
    private int ans = 0;
    public int solution(int n) {
        
        int[] queenArray = new int[n];
        
        for(int i=0; i<n; i++) { //시작 위치
            queenArray[i] = 1;
            
            isOk(queenArray, n, 1);
            
            queenArray[i] = 0;
        }
        
        return ans;
    }
    
    public void isOk(int[] queenArray, int n, int depth) {
        if(depth == n){
            ans++;
            return;
        }
        
        boolean[] chkArray = new boolean[n];
        for(int i=0; i<n; i++) { //현재까지 배치된 퀸의 위치와 언제 배치 됐는지 비교해서 갈 수 없는 곳 표시.
            if(queenArray[i] > 0){
                int qd = depth - queenArray[i]+1;
                if(i+qd < n) chkArray[i+qd] = true;
                if(i-qd > -1) chkArray[i-qd] = true;
                chkArray[i] = true;
            }
        }
        
        for(int i=0; i<n; i++){ //check배열 만큼.
            if(chkArray[i]) continue; //퀸이 갈 수 없는 위치면 continue;
            
            queenArray[i] = depth+1;
          
            isOk(queenArray, n, depth+1);
                 
            queenArray[i] = 0;
        }
    }
}