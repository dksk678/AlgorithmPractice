import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int len = queries.length;
        int[] answer = new int[len];
        int[][] map = new int[rows][columns];
        int cnt = 1;
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = cnt++;
            }   
        }
        
        for(int i=0; i<len; i++){
            answer[i] = rotationMap(map, queries[i]);
        }
        
        return answer;
    }
    
    public int rotationMap (int[][] map, int[] querie){
        int sr = querie[0]-1; //시작, 끝 위치
        int sc = querie[1]-1;
        int er = querie[2]-1;
        int ec = querie[3]-1;

        int cur = map[sr][sc]; //현재 저장된 숫자
        int next = 0; //변경될 다음 숫자 저장
        
        int min = cur; //가장 작은 수
        
        //4방향 
        for(int i=1; i<=ec-sc; i++){ //우
            next = map[sr][sc+i];
            map[sr][sc+i] = cur;
            cur = next;
            min = min>cur?cur:min; //가장 작은 수 저장
        }
        for(int i=1; i<=er-sr; i++){ //하
            next = map[sr+i][ec];
            map[sr+i][ec] = cur;
            cur = next;
            min = min>cur?cur:min;
        }
        for(int i=1; i<=ec-sc; i++){ //좌
            next = map[er][ec-i];
            map[er][ec-i] = cur;
            cur = next;
            min = min>cur?cur:min;
        }
        for(int i=1; i<=er-sr; i++){ //상
            next = map[er-i][sc];
            map[er-i][sc] = cur;
            cur = next;
            min = min>cur?cur:min;
        }
        
        return min;
    }
}