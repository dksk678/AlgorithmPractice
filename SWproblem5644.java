package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWproblem5644{
    static int[] dr = { 0, -1, 0, 1, 0 };
    static int[] dc = { 0, 0, 1, 0, -1 };
    static int[][] map1;
    static int[][] map2;
    static int[] chargeP;
    static int[] Apos;
    static int[] Bpos;
    static int sum;
    static int cnt;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            map1 = new int[10][10]; // 작은값만
            map2 = new int[10][10]; // 큰 값만, 혼자만 있을 경우는 무조건 큰 값.
 
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 이동 수
            int a = Integer.parseInt(st.nextToken()); // BC 개수
 
            int[] A = new int[m];
            int[] B = new int[m];
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
             
            chargeP = new int[a + 1];
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());// 위치
                int r = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());// 범위
                int P = Integer.parseInt(st.nextToken());// 량
 
                chargeP[i + 1] = P;
                setBC(r - 1, c - 1, C, P, i + 1);
            }
             
            Apos = new int[2];
            Bpos = new int[2];
            Apos[0] = 0;
            Apos[1] = 0;
            Bpos[0] = 9;
            Bpos[1] = 9;
 
            // 처음일때
            sum = 0;
            cnt = 0;
            int Ap = map1[Apos[0]][Apos[1]];
            int Bp = map1[Bpos[0]][Bpos[1]];
            if (Ap == Bp) {
                sum += chargeP[Ap];
                sum += Math.max(chargeP[map2[Apos[0]][Apos[1]]], chargeP[map2[Bpos[0]][Bpos[1]]]);
            } else {
                sum += chargeP[Ap];
                sum += chargeP[Bp];
            }
            //이동 시작
            for (int i = 0; i < m; i++) {
                move(A[i], B[i]);
            }
            sb.append("#").append(tc + " ").append(sum).append("\n");
        }
 
        System.out.println(sb);
    }
 
    private static void move(int ad, int bd) {
        Apos[0] = Apos[0] + dr[ad];
        Apos[1] = Apos[1] + dc[ad];
        Bpos[0] = Bpos[0] + dr[bd];
        Bpos[1] = Bpos[1] + dc[bd];
 
        int Ap = map1[Apos[0]][Apos[1]];
        int Bp = map1[Bpos[0]][Bpos[1]];
 
        if (Ap == Bp) {
            sum += chargeP[Ap];
            sum += Math.max(chargeP[map2[Apos[0]][Apos[1]]], chargeP[map2[Bpos[0]][Bpos[1]]]);
        } else {
            sum += chargeP[Ap];
            sum += chargeP[Bp];
        }
    }
     
    //충전 범위 지정
    private static void setBC(int r, int c, int C, int p, int i2) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[10][10];
        v[r][c] = true;
 
        q.offer(new int[] { r, c, 0 });
 
        int max = chargeP[map1[r][c]];
        if (max > 0) {
            if (max > p) {
                if (chargeP[map2[r][c]] < p) {
                    map2[r][c] = i2;
                }
            } else {
                map2[r][c] = map1[r][c];
                map1[r][c] = i2;
            }
        } else {
            map1[r][c] = i2;
        }
 
        while (!q.isEmpty()) {
            int[] cur = q.poll();
 
            if (cur[2] == C)
                return;
 
            for (int k = 1; k < 5; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];
 
                if (nr < 0 || nr >= 10 || nc < 0 || nc >= 10 || v[nr][nc]) continue;
 
                max = chargeP[map1[nr][nc]];
                if (max > 0) {
                    if (max > p) {
                        if (chargeP[map2[nr][nc]] < p) {
                            map2[nr][nc] = i2;
                        }
                    } else {
                        map2[nr][nc] = map1[nr][nc];
                        map1[nr][nc] = i2;
                    }
                } else {
                    map1[nr][nc] = i2;
                }
 
                v[nr][nc] = true;
                q.offer(new int[] { nr, nc, cur[2] + 1 });
 
            }
        }
    }
 
}