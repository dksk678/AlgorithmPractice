import java.util.*;

class Solution {
    int[] dr = {1, 0, -1}; //아래, 오른쪽, 왼쪽위대각선 방향으로 달팽이 채워짐
    int[] dc = {0, 1, -1};
    
    public int[] solution(int n) {
        int[][] map = new int[n][n];

        int cnt = 1; //현재 번호
        int end = n*n/2 + (n%2==0?n/2:n/2+1); //마지막 숫자
        int r = 0;
        int c = 0;
        int dir = 0;
        
        while(cnt <= end){
            map[r][c] = cnt++;
            //맵을 벗어나거나 이미 채워진 경우면 방향 바꿈
            if((r+dr[dir] >= n || c+dc[dir] >= n) || map[r+dr[dir]][c+dc[dir]] >= 1) {
                dir = (dir+1)%3;
            } 
            r += dr[dir];
            c += dc[dir];
        }
        
        int[] answer = new int[end];
        cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                answer[cnt++] = map[i][j];
            }
        }
        
        return answer;
    }
}