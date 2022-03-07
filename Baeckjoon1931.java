import java.io.*;
import java.util.*;

/*	회의실 배정 1931번 16:25~
 *  1개의 회의실에서 N개의 회의가 주어지고 주어진 회의I 마다 주어진 시작 시간과 끝나는 시간을 입력해줌.
 *  겹치지 않고 회의실을 사용할 수 있는 최대 개수. *시작하자마자 끝날 수 있음.
 *  X""""""""""""""""""""""""""" 16:25~17:00
 *  1. 시작시간과 끝시간 차이가 적은 것부터 정렬. 
 *     -> 2차원 배열로 만듬. [회의시간, 시작시간, 끝나는시간], 회의시간 순서대로 정렬
 *  2. 가장 짧은 시간 부터 카운트 해주고, 끝나는 시간과 다음 짧은 시간의 시작 시간을 비교
 *   ->시간순서대로 구해야 나중에 비교를 할 필요가 없음. 매우 복잡하게 생각된 규칙
 *  """"""""""""""""""""""""""""X
 *    17:15~18:00
 *  1. 끝나는 시간을 정렬
 *  2. 다음 시작시간과 이번 끝나는 시간이 같거나 이상일 때 카운트 
 */ 
class Baeckjoon1931{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine()); //동전의 종류 개수
        int[][] arr2 = new int[N][2]; //I값과 회의시간을 저장할 배열
        int s; //시작시간 저장할 변수
        int e; //종료시간 저장할 변수
        int res=1; //최소 한번은 함 N이 1부터이기때문에

        for(int i=0; i<N; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	s = Integer.parseInt(st.nextToken());
        	e = Integer.parseInt(st.nextToken());
        	arr2[i][0] = e;//끝나는 시간 순으로 정렬하려고 
        	arr2[i][1] = s;
        }
        					//리턴 값이 양수면 오름차순으로 바꿔라
        Arrays.sort(arr2, (o1,o2) -> { // 2차원 배열 sort,  람다 표현식 == ->
        	if(o1[0] == o2[0]){ //끝나는 시간이 같으면  시작시간 비교
                return Integer.compare(o1[1], o2[1]);//시작시간이 짧은 순 대로 정렬
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        
        int now=0;//첫번째 회의는 무조건 시작 끝나는 시간기준으로 정렬 됐기때문에.
        for(int i=1; i<N; i++) {
        	//이미 정렬된 데이터 이기때문에 바로 비교하면 됨
        	//현재회의시간(arr2[now])이 끝나는 시간과 다음회의시간의 시작시간(arr2[i][1])이 같거나 이상이면 cnt+1; 
        	if(arr2[i][1]>=arr2[now][0]) {
        		res+=1; 
        		now=i; //현재회의가 i번째 회의로 바꼈으므로 
        	}
        }
        System.out.println(res);
    }
}