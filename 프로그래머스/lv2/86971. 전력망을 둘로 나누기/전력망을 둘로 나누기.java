import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> list;
    public int solution(int n, int[][] wires) {
        int answer = 99999;

        list = new ArrayList<>();
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<>());
        }

        for(int[] i:wires){
            list.get(i[0]).add(i[1]);
            list.get(i[1]).add(i[0]);
        }

        for(int i=1; i<=n; i++){
            int cnt = BFS(n, i); //1 , 0
            answer = Math.min(Math.abs(cnt*2-n), answer);
        }
        return answer;
    }

    public int BFS(int n, int i){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[n+1];
        q.offer(1);
        v[1] = true;

        int cnt = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next:list.get(cur)){
                if(v[next] || next==i) continue;

                v[next] = true;
                q.offer(next);
                cnt++;
            }
        }
        
        // System.out.println(i+" "+cnt);
        return cnt;
    }
}