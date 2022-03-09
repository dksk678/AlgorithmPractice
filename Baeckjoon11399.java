import java.io.*;
import java.util.*;

/*	ATM 
 *  가장빨리 돈을 뽑을 수 있는 방법은 오름차순 정렬을 해서 가장 작은 시간 부터 계산해나아가야함.
 *  
 *  규칙 = 1, 1+2, 1+2+3 => 변수로 직전값 저장
 *  
 */ 
class Baeckjoon11399{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine()); //N명 
        int[] P = new int[N]; //시간 저장할 배열
        //배열에 들어갈 숫자를 입력 받음
        StringTokenizer st = new StringTokenizer(bf.readLine()); //2번째 줄 한번에 입력 받음
        for(int i=0; i<N; i++) {
        	P[i] = Integer.parseInt(st.nextToken()); //2번째줄 한글자씩 넣어줌
        }
        Arrays.sort(P);
        
        int sum=0; //직전값 저장하기 위한 변수 
        int res=0; //result
        for(int i=0; i<N; i++) {
        	sum += P[i];
        	res += sum; //각 sum값을 더해서 최종 시간을 구함
        }
        System.out.println(res);
         
    }
    // 12334
    // 1 3 6 9 13    28 32
}