import java.io.*;
import java.util.*;

/*	수 이어 쓰기1, 
 *  123456789101112131415 --> 10은 11번째자리에서 끝남 11은 13번째자리에서 끝남
 *  N의 자리수 부터 N의 몫까지 값 구하고, N의 자리수로 나눈 나머지를 더해서 계산
 *  
 *  그 밑에있는 개수 구하는 법은 밑에 방법으로 더해줌
 *  일의 자리는 총9
 *  십의 자리는 총90 => 자릿수*9
 */ 
class Baeckjoon1748{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(bf.readLine());
        int tmp = N;
        int cnt = 0;
        int sum=0;
        if(N<10) {
        	System.out.println(N);
        	return;
        }
        while(tmp>=10) { //자리수 구하기
        	cnt+=1;
        	tmp/=10;
        }
        
        sum += ((int)(N/Math.pow(10, cnt))-1)*Math.pow(10, cnt)*(cnt+1);//N의 첫자릿수~몫까지 값. ex) 220 => 100~200까지 개수
        
        sum += ((N%Math.pow(10, cnt)+1))*(cnt+1);  //첫자리를 제외한 나머지 자리수 값 ex)220 => 20까지의 개수
        
        System.out.println(sum);
        while(cnt>0) {
        	cnt--;
        	sum += 9*Math.pow(10, cnt)*(cnt+1);  //N보다 밑의 자리수들 값 더하기 1*9, 2*90, 3*900
        }
        System.out.println(sum);
    }
}