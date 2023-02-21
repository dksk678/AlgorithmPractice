import java.util.*;
import java.io.*;
/*
X 또는 1~9의 숫자
각 칸의 숫자는 식량. 연결된 칸의 합이 머물을 수 있는 최대 일 수
각 섬에서 머무를 수 있는 최대 값을 오름차순으로 정렬

*/
class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] visited;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        int row = maps.length;
        int col = maps[0].length();
            
        visited = new boolean[row][col];
        
        List<Integer> days = new ArrayList<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(visited[i][j] || maps[i].charAt(j) == 'X') continue;
                
                visited[i][j] = true;
                days.add(getDays(maps, row, col, i, j, maps[i].charAt(j)-'0'));
            }   
        }
        
        if(days.size() == 0) {
            days.add(-1);
        }
        
        Collections.sort(days);
        
        return days.stream().mapToInt(i->i).toArray();
    }
    
    
    private int getDays(String[] maps, int maxr, int maxc, int curr, int curc, int days){
        for(int i=0; i<4; i++){
            int nextr = curr + dr[i];
            int nextc = curc + dc[i];
            
            if(nextr < 0 || nextr >= maxr || nextc < 0 || nextc >= maxc
               || visited[nextr][nextc] || maps[nextr].charAt(nextc) == 'X') continue;
            
            visited[nextr][nextc] = true;
            days = getDays(maps, maxr, maxc, nextr, nextc, days + maps[nextr].charAt(nextc) - '0');
        }
        
        return days;
    }
}