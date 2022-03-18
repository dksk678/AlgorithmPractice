import java.io.*;
import java.util.*;

/*  병든 나이트  1783 16:00
 *  세로(N), 가로(M)
 *  | 2,1 | 1,2 | -1,2 | -2,1 | 
 *  이동 방법 4개가 있음. -> 이동횟수가 5이상이 되면 무조건 가는 방법 4개를 다사용해야함.
 *  
 *  가로는 어떻게 가던 무조건 +1개는 증가함.
 *  
 *  1.가로 세로가 1이면 무조건 1
 *  2.가로가 2면 무조건 2
 *  3.세로가 2고 7이상이면 무조건 4임, 조건 때문에 세로가2면 가는 방법 2개를 사용할 수가 없음.
 *  4.세로가 3이상이고 가로가 7이상이면 모든 조건이 성립이 되기때문에 최대 가로길이 -2만 하면 됨
 *   -> -2하는 경우는 조건을 만족하기 위해 가로+2를 두번 사용하기 때문.
 */

class Baekjoon1783{
//	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st =  new StringTokenizer(bf.readLine());  
//        String a = bf.readLine();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        //1.
        if(N==1||M==1) {
        	System.out.println(1);
    		return;
        }
        //3-1
        if(N==2){ //M=3,4
        	if (M==2) {
    			System.out.println(1);
        		return;
    		} else if(M<=4) {
        		System.out.println(2);
        		return;
    		} else if (M<=6) { //M=5,6
    			System.out.println(3);
        		return;
    		}
        }
        //2.
        if(M==2) {
        	System.out.println(2);
        	return;
        }
        //N이 3이상이고 4이하면 무조건 M까지만 가능.
        if(M<=4) {
        	System.out.println(M);
    		return;
        }
        //3-2
        if(N==2) {
        	System.out.println(4);
        	return;
        }
        //7이상부터 계산 되므로 
        if(M<=6) {
        	System.out.println(4);
        	return;
        }
        //4.
        System.out.println(M-2);
    }
}