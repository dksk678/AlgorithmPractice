import java.io.*;
import java.util.*;
/* 체스판 다시 칠하기 - 브루트포스
 * 처음부터 체스판 -8크기 까지 비교 
 * 왼쪽위 첫번째 색과 비교해서 다르면 카운트++
 * !첫번째 색을 비교했을 때 바뀌어야하는 수가 전체의 절반 이상이면 반대로 색칠.
 * 
 */

class Baekjoon1018{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int min = 999;
        char color;
        char start;
        String[] arr = new String[n];
        for(int i=0; i<n; i++){
        	arr[i] = bf.readLine();
        }
        for(int i=0; i<=n-8; i++){
        	for(int j=0; j<=m-8; j++) {
        		cnt = 0;
        		start=arr[i].charAt(j);
	        		for(int x=i; x<i+8; x++) {
	        			for(int y=j; y<j+8; y++) {
	        				color = arr[x].charAt(y);
        					if((x+y)%2==(i+j)%2&&color!=start) { //처음색과 같아야함, 그러나 다르면+1
	        					cnt += 1;
	        				} else if((x+y)%2!=(i+j)%2&&color==start) { //처음과 달라야하는데 같으면 +1
	        					cnt += 1;
	        				}
	        			}
	        		}
	        	
        		if(min>cnt) min = cnt;
        		if(min>64-cnt) min = 64-cnt;
        	}
        }
        System.out.print(min);
    }
}
