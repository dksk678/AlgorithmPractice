import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
0부터 시작.
장애물크기 +1
0~H까지 숫자가 가장 적은 곳 개수.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] suk = new int[H+1];
        int[] jong = new int[H+1];

        for (int i = 0; i < N; i++) {
            if(i%2==0)
                suk[Integer.parseInt(br.readLine())]++;
            else
                jong[Integer.parseInt(br.readLine())]++;

        }
        
        int[] h1 = new int[H+1];
        h1[H] = suk[H];
        int[] h2 = new int[H+1];
        h2[H] = jong[H];

        for (int i = H; i > 0; i--) {
            h1[i-1] += h1[i]+suk[i];
            h2[i-1] += h2[i]+jong[i];
        }

        int min = 21000000;
        int mincnt = 0;
        for (int i = 0; i < H; i++) {
            int breakcnt = h1[i] + h2[H-i-1];
            if(breakcnt < min){
                mincnt = 1;
                min = breakcnt;
            } else if(breakcnt == min){
                mincnt++;
            }
        }

        System.out.println(min+" "+mincnt);
    }
}