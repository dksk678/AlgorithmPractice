import java.io.*;
import java.util.*;

/*  이장님 초대 9237 17:50~18:20
 *  묘목 n개 구입, 심는데 1일, 모든 나무가 다 자란 다음날 이장님을 초대. 며칠에 가능한가?
 *  이장님을 최대한 빨리 초대하려고 함 -> 정렬
 *  4 3 3 2
 *  5 5 6 6 -> 6+1 = 7일
 *  39 39 38 35 20 9
 *  40 41 41 39 25 15 -> 가장빨리 가능한 시간은 41일, +1 => 42일날 보여줌
 *  
 *  1. 자라는데 가장 오래걸리는 나무 순 대로 먼저 심음
 *  2. 심는데 걸리는 시간(cnt)+다자라는데 걸리는 시간, 중 가장 오래걸리는 것 이 정답
 */

class Baekjoon9237{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());
        int[] tree = new int[1000001];
        int max = 0; //심는데 가장 오래걸리는 시간값 저장
        int cnt = 0;
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++){ 
            tree[Integer.parseInt(st.nextToken())] += 1;
        }
        //마지막.이후 x개수 계산
        for(int i=tree.length-1; i>=1; i--){
        	if(tree[i]>=1) {
        		cnt+=tree[i]; //몇번째 나무인가.
        		max = Math.max(max, i+cnt); //i=심는데 거리는 시간, cnt=이전 나무들 심는데 걸린 시간.
        	}
        }
        System.out.println(max+1);
    }
}