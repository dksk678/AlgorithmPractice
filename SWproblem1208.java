import java.io.*;
import java.util.*;
/*  Flatten 1208 30
 *  평탄화
 *  횟수만큼 평탄화 진행 최고점과 최저점 차이 반환
 */
class Solution {
    static int end;
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
//        int T = Integer.parseInt(bf.readLine());
 
        for(int test_case = 1; test_case <=10 ; test_case++)
        {   //set
            int N = Integer.parseInt(bf.readLine());
            int[] arr = new int[101];
            st = new StringTokenizer(bf.readLine());
            for(int i=0; i<100; i++) {
                arr[Integer.parseInt(st.nextToken())]++;
                 
            }
 
            int min = 1;
            int max = 100;
            int tmp = 100;
            while(N>0) {
                if(arr[min]==0) { min++; continue;} //최소
                if(arr[min]==1) {
                    arr[min]--;
                    min++;
                    arr[min]++;             
                } else {
                    arr[min]--;
                    arr[min+1]++;
                }
                for(int i=max; i>=min;) { //최대 
                    if(arr[i]==0) {i--; continue;}
                    tmp = i;
                    N--;
                    if(arr[i]==1) { 
                        arr[i]--; 
                        arr[i-1]++; 
                        tmp--; 
                    } else {
                        arr[i]--;
                        arr[i-1]++;
                    }
                    break;
                }
                max = tmp;
                if(max-min<=0) {
                    break;
                }
            }
            sb.append("#"+test_case+" ");
            if(N==0) {
                sb.append(max-min);
            } else {
                sb.append(N%2==0?0:1);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}