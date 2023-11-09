import java.util.*;

/*
    Commands의 길이 만큼 반복.
    새로운 배열 Temp에 command에 해당하는 번호 넣기
*/
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int cnt = 0; //answer 배열에 넣기 위해 현재 command count
        
        for(int[] command : commands) {
            int s = command[0];
            int e = command[1];
            int k = command[2];
            int len = e-s+1; //길이
            
            int[] tmp = new int[len];
            for(int i=0; i<len; i++) {
                tmp[i] = array[s+i-1]; //tmp에 command에 해당하는 번호 넣기
            }
            
            Arrays.sort(tmp);
            
            answer[cnt++] = tmp[k-1];
        }
        
        return answer;
    }
}