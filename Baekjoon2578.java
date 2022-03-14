import java.io.*;
import java.util.*;

/*  빙고  2578 18:40~20:00
 *  가장빠른 경우의 수 => 12번째 부터 가능
 *  
 *  가로세로대각선 비교.
 */

class Baekjoon2578{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        String a = bf.readLine();
//        int N = Integer.parseInt(bf.readLine());   
        StringTokenizer st;
        int[][] arr = new int[5][5]; // 숫자 적힌 arr
        int[][] arr2 = new int[26][2]; //숫자의 좌표값 저장
        int cnt=0;
        int num;
        
        for(int i=0; i<5; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<5; j++) {
        		num = Integer.parseInt(st.nextToken());
            	arr[i][j] = num; //빙고판 굳이 안써도됨
            	
            	arr2[num][0] = i; //num에 관한 좌표값 저장 x축
            	arr2[num][1] = j; //num에 관한 좌표값 저장 y축 
            	cnt++;
            }
        }
        cnt = 1;
        for(int i=0; i<5; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<5; j++) {
        		num = Integer.parseInt(st.nextToken());//빙고판에서 제거할 비교할 숫자 읽어옴 = num에 저장
    			arr[arr2[num][0]][arr2[num][1]] = 0; //num의 좌표를 가져와서 해당 좌표에 숫자를 0으로 변경 == 제거
    			if(cnt>=12) { //빙고 최소 숫자는 12부터 
            		if(bingo(arr)>=3) { //3줄이상이면 빙고
            			System.out.println(cnt);
            			return;
            		}   		
    			
        		}
        		cnt++;
            }
        }
    }
    //빙고인지 확인하기 위한 메소드
    private static int bingo(int[][] arr) {
    	int cntrow=0;
    	int cntcol=0;
    	int cnt1=0;
    	int cnt2=0;
    	int res = 0;
    	for(int i=0; i<5; i++) {
    		cntrow=0;
	    	cntcol=0;
    		for(int j=0; j<5; j++) {
    			//가로
    			if(arr[i][j]==0) {
    				cntrow++;
    				if(cntrow==5) {
        				res += 1;
        			}
    			}
    			//세로
    			if(arr[j][i]==0) {
    				cntcol++;
    				if(cntcol==5) {
        				res += 1;
        			}
    			}
//    			System.out.print(arr[i][j]+" ");
    		}
//    		System.out.println(" ");
			//대각선
			if(arr[i][i]==0) {
				cnt1++;
				if(cnt1==5) {
    				res += 1;
    			}
			}
			//역대각
			if(arr[i][4-i]==0) {
				cnt2++;
				if(cnt2==5) {
    				res += 1;
    			}
			}
		}
//    	System.out.println(" "+res);
    	return res;
    }
}