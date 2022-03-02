import java.io.*;
import java.util.*;
/*	빈공간 규칙 = n은 3의x제곱, x~x+x-1 까지가 빈공간임  ex)9면 3~5까지가 빈공간  3이면 1~1까지빈공간
 *  -> 가장큰 빈공간인 처음 x부터 구한후 x를 한개씩 줄여가면서 배열에 빈공간을 저장 x를 줄여나가는 형태를 재귀로 돌림
    -> 시간이 어떻게 될지 모름 굳이 제귀 필요없음
 */ 

class Baekjoon2447{
	static String[][] arr; //
	static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(bf.readLine());
        arr = new String[n][n];
        for(int i=0; i<arr.length; i++) {
        	Arrays.fill(arr[i], "*");
        }
        rfunc((int)(Math.log(n)/(int)Math.log(3))); //제곱값 구하기 위한 식  
        										//log 를 사용하면 소숫점 사용으로 나중에 버림을 해버림, 그래서 int필수!!
        //출력
        for(int i=0; i<arr.length; i++) {
        	for(int j=0; j<arr.length; j++) {
        		sb.append(arr[i][j]);
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
    
    static void rfunc(int sq) {
    	int mid=(int)Math.pow(3, sq-1); // 9, 17
    	int midM = mid+mid-1;
    	if(sq==0) {
    		return;
    	}
    	for(int i=mid; i<=n; i++) {
    		for(int j=mid; j<=n; j++) { //가장 큰 빈공간 부터 배열에 저장, 
			if((i%(3*mid)>=mid&&i%(3*mid)<=midM) && (j%(3*mid)>=mid&&j%(3*mid)<=midM)) {
				arr[i][j]=" ";
				}
    		}
    	} 
    	rfunc(--sq); //345678
    }
}
