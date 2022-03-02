import java.io.*;
import java.util.*;
/* 골드바흐의 추측, 소수+수학+정수론
 * 소수 구하기 -> 에라토스테네스의 체로 구현
 * 두 소수 더한 값이 일치하고 차이가 제일 적을 경우 출력

 */

class Baekjoon9020{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        boolean[] arr = new boolean[10001];
    	for(int i=2; i*i<10001; i++) {
    		int cnt=i+i;
			while(cnt<10001) {//
				arr[cnt]=true;
				cnt += i;
			}
    	}
    	int n;
    	int f;
    	int s;
        for(int i=0; i<T; i++){
        	n = Integer.parseInt(bf.readLine());
        	f = n/2;
        	s = n/2;
        	while(true) {
        		if(!arr[f] && !arr[s]) {
        			sb.append(f).append(" ").append(s).append("\n");
        			break;
        		}
        		f -= 1;
        		s += 1;
        	}
        }
        System.out.println(sb);
    }
}
