import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int plus = 0;
        int minus = 0;
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            if(num > 0) plus++;
            else if (num < 0) minus++;
        }
        
        Arrays.sort(arr); //정렬
        
        if(plus == 0) {
            System.out.println(arr[N - 2] + " " + arr[N - 1]);
            return;
        }
        if(minus == 0) {
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }

        int start = 0;
        int end = N-1;
        int min = Math.abs(arr[start] + arr[end]);
        int ansl = arr[start];
        int ansr = arr[end];

        while(start < end) {
            int sum = arr[start]+arr[end];

            if(Math.abs(sum) < min){ //다음 덧셈이 현재값보다 작은경우 end를 mid로
                min = Math.abs(sum);
                ansl = arr[start];
                ansr = arr[end];
            }
            if(sum < 0) { // 음수면 end ++
                start++;
            } else if (sum > 0){
                end--;
            } else {
                System.out.println(ansl +" "+ansr);
                return;
            }
        }

        System.out.println(ansl+" "+ansr);
    }
}