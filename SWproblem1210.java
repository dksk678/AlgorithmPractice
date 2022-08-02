package algo_0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 사다리, 골 지점에서 부터 0까지 역추적 하기
 * 좌,우갈 수 있으면 좌,우 먼저 가기
 * 
 */
public class SWproblem1210 {
    static int[][] map;
    static boolean[][] visited;
    static int ans;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            map = new int[100][100];
            visited = new boolean[100][100];
            int endr = 0;
            int endc = 0;
            ans = 0;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) { //시작 위치 21012
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if(num==2) {
                        endr = i;
                        endc = j;
                    }
                }
            }
            DFS(endr, endc);
            sb.append("#"+t+" ");
            sb.append(ans+"\n");
        }
         
        System.out.println(sb);
    }
     
    static int[] dr = {0, 0, -1};
    static int[] dc = {1, -1, 0};
     
    private static void DFS(int curr, int curc) {
        if(curr==0) { //첫 줄로 오면 원하는 목표를 찾은것
            ans = curc;
            return;
        }
         
        for(int i=0; i<3; i++) {
            int nr = curr+dr[i];
            int nc = curc+dc[i];
             
            if(nc<0||nc>=100||visited[nr][nc]) continue;
             
            if(map[nr][nc]==1) {
                visited[nr][nc] = true;
                DFS(nr, nc);
                break;
            }
        }
    }
}