import java.io.*;
import java.util.*;
/*  최대 상금 1244 180~
 *  
 */
class SWproblem1244 {
    static int end;
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(bf.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {   //set
            st = new StringTokenizer(bf.readLine());
            String num = st.nextToken();
            end = Integer.parseInt(st.nextToken());
            int l = num.length();
            int[] nArr = new int[l];
            for(int i=0; i<l; i++) {
                nArr[i] = (num.charAt(i)-48);
            }
 
            boolean samechk = false;
            for(int i=0; i<l-1; i++) {
                for(int j=i+1; j<l; j++) {
                    if(nArr[i]==nArr[j]) {
                        samechk = true;
                    }
                }
            }
            sb.append("#"+test_case+" ").append(BFS(nArr, samechk)).append("\n");
        }
        System.out.println(sb);
    }
     
    static int BFS(int[] arr, boolean sc) {
        Queue<prize> q = new LinkedList<prize>();
        int[] tmpArr = new int[arr.length];
        int[] maxArr = new int[arr.length];
        int max=0;
        boolean[] v = new boolean[1000001];
         
        prize cur = new prize(maxArr, 0);
        q.offer(new prize(arr, 0));
         
        while(!q.isEmpty()) {
            cur = q.poll();
            if(cur.cnt>=end) { //횟수 까지 진행한 경우
                max = Math.max(arrToNum(cur.arr), max);
                 
                continue;
            }
            for(int i=0; i<arr.length; i++) {
                for(int j=0; j<arr.length; j++) {
                    tmpArr = cur.arr.clone();
                    if(i==j) continue;
                     
                    tmpArr[i] = cur.arr[j];
                    tmpArr[j] = cur.arr[i];
                    if(v[arrToNum(tmpArr)]) { //이미 발견된 숫자인 경우
                        if(arrToNum(tmpArr)>max) {
                            max = arrToNum(tmpArr);
                            maxArr = tmpArr.clone();
                        }
                        continue;
                    }
                    v[arrToNum(tmpArr)]=true;
                    q.offer(new prize(tmpArr, cur.cnt+1));
                }
            }
        }
 
        if((end-cur.cnt)%2!=0&&!sc) { //같은 수 없고 최고점이 이미 발견된 경우 마지막만 변경
            int tmp = maxArr[arr.length-1];
            maxArr[arr.length-1] = maxArr[arr.length-2];
            maxArr[arr.length-2] = tmp;
        }
        return arrToNum(maxArr);
    }
     
    static class prize {
        int[] arr;
        int cnt;
        public prize(int[]arr, int cnt) {
            this.arr = arr.clone();
            this.cnt = cnt;
        }
    }
     
    static int arrToNum(int[] arr) {
        int num = 0;
        for(int i=0; i<arr.length; i++) {
            num += arr[i]*Math.pow(10, arr.length-i-1);
        }
        return num;
    }
}