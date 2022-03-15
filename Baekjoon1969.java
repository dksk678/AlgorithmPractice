import java.io.*;
import java.util.*;

/*  DNA  1969 21:40~22:10
 *  문자열을 우선 저장.
 *  N개의 문자열중 각 자리수마다 가장 많이 쓴 문자 별로 저장.
 *  가장많이쓴 문자가 아닌 숫자들 카운트 => 해밍거리 측정하기 위함.
 *  
 */

class Baekjoon1969{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        String a = bf.readLine();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] aToz = new int[26];
        int N = Integer.parseInt(st.nextToken());   
        int M = Integer.parseInt(st.nextToken());
        
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        String[] str_arr = new String[N];
//        int[] arr = new int[M];
        int max = 0; //가장많이 쓴 문자 개수
        char ch = 0; //가장많이 쓴 문자 
        
        String res = ""; //가장많이쓴 문자들
        int cnt = 0; //해밍거리 측정
        
        for(int i=0; i<N; i++) {
        	str_arr[i] = bf.readLine();
        }
        
        for(int i=0; i<M; i++) {
        	aToz = new int[26];
        	max = 0;
        	for(int j=0; j<N; j++) {
        		aToz[str_arr[j].charAt(i)-65] += 1;
        	}
        	//max값 구하기.
        	for(int j=0; j<26; j++) {
        		if(aToz[j]>max) {
        			max = aToz[j];
        			ch = (char)(j+65);
        		} 
        	}
        	res += ch;
        	//max값 이랑 비교해서 해밍거리 구하기.
        	for(int j=0; j<26; j++) {
        		if(aToz[j]>=1 && (char)(j+65)!=ch) {
        			cnt += aToz[j];
//        			System.out.println(i+" "+(char)(j+65)+" "+ch);
        		}
        	}
        }
        System.out.println(res);
        System.out.println(cnt);
        
    }

}