import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
    union_find
    : make,(list연결된 리스트 저장, par:부모 저장, rank: 깊이 나누기)
    : find (부모 찾기)
    : union (부모와 연결)

    A, B가 있으면
    A의 부모와 B의 부모가 같으면 사이클임
 */
public class Main {
    private static int[] par;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //make
        par = new int[N];
        for (int i = 0; i < N; i++){
            par[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(find(A) == find(B)){
                ans = i+1;
                break;
            }
            
            union(A, B);
        }

        System.out.println(ans);
    }

    private static int find(int num) {
        if(num == par[num])  return num;

        return par[num] = find(par[num]);
    }

    private static void union(int A, int B) {
        int pa = find(A);
        int pb = find(B);

        if(pa == pb) return;

        par[pb] = pa;
    }

}