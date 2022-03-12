import java.io.*;
import java.util.*;

/*  신입사원 1946 18:40 ~ 19:20
 *  성적 비교, A와 B랑 비교했을 때 두개다 적으면 무조건 탈락!
 *  
 *  1. 서류심사 성적순으로 정렬.
 *  2. 현재사원과  다음사원을 비교.
 *   2-1. 현재사원점수보다 다음사원점수가 둘다 낮으면 그 다음 사원과 비교
 *   2-2. 다음사원점수가 높점수가 있으면 현재 사원으로 변경.
 *  3. 변경될때마다 카운트+1  
 *  
 *   
 */

class Baekjoon1946{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        int[][] arr;
        int res;
        int cur;
        
        for(int i=0; i<T; i++) {
        	int N = Integer.parseInt(bf.readLine()); //지원자 수
        	arr = new int[N][2];
        	res = 1; //서류심사성적 1등은 무조건뽑힘.
        	cur = 0;
        	for(int j=0; j<N; j++) {
        		st = new StringTokenizer(bf.readLine());
        		arr[j][0] = Integer.parseInt(st.nextToken());
        		arr[j][1] = Integer.parseInt(st.nextToken());
        	}
        	Arrays.sort(arr, Comparator.comparingInt(o1->o1[0])); //서류심사 순으로 정렬
        	for(int j=1; j<N; j++) {
        		if(arr[cur][1]>arr[j][1]) { //cur가  j보다 면접성적이 낮으면
        			res+=1; //결과+1
        			cur = j; //현재사원으로 변경
        		}
        	}
        	sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
    
}