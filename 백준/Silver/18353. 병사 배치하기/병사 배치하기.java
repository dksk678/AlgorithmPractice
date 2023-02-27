import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];

        dp[0] = arr[0];

        for(int i=1; i<N; i++){
            for(int j=0; j<=i; j++) {
                if(dp[j] <= arr[i]) {
                    dp[j] = arr[i];
                    break;
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            if(dp[i] > 0) {
                cnt++;
            }
        }

        System.out.println(N-cnt);
    }
}