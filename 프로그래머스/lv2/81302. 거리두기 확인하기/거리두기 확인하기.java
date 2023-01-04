import java.util.*;

class Solution {
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        
        int cnt = 0;
        
        for(int i=0; i<5; i++){
            loop: for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    if(places[i][j].charAt(k) == 'P' && BFS(j, k, places[i])){
                        
                        answer[cnt] = 0;
                        break loop;
                    }
                }
            }
            cnt++;
        }
        
        return answer;
    }
    
    public boolean BFS(int j, int k, String[] place){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[5][5];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int cnt = 1;
        
        q.offer(new int[] {j, k});
        v[j][k] = true;
        
        while(!q.isEmpty()){
            int s = q.size();
            for(int s1=0; s1<s; s1++){
                int[] cur = q.poll();
                
                for(int d=0; d<4; d++){
                    int nr = cur[0]+dr[d];
                    int nc = cur[1]+dc[d];

                    if(nr<0||nr>4||nc<0||nc>4||v[nr][nc]||place[nr].charAt(nc) == 'X') continue; //불가능한 곳 
                    
                    if(place[nr].charAt(nc) == 'P') return true; //가능한 곳에서 P를 만나면 지키고 있지 않음
                    
                    q.offer(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
            
            if(cnt++ == 2) break;
        }
        
        return false;
    }
}