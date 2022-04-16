import java.io.*;
import java.util.*;

/*  리모컨 1107
 * 
 *  숫자:0~9
 *  +, -
 *  
 *  원하는 숫자 일때 버튼 누른 개수
 *  
 *  
 *  5457 -> 0 1 2 3 4 5 9
 *  
 */

class Baekjoon1107 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	
	static int ch;   //목표 채널
	static int N;    //고장난 번호
	static int chl;  //채널 길이
	
	static int[] arr = new int[10000002]; //번호만으로 갈 수 있는 배열 저장
	
	static boolean[] button; //리모컨 번호
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        //set
        button = new boolean[10];
        Arrays.fill(button, true);
        
        String strCh = bf.readLine();
        chl = strCh.length();
        ch = Integer.parseInt(strCh);
        
        N = Integer.parseInt(bf.readLine());
        
        int min = (Math.abs(ch-100));
   
        //N=0일때 예외 처리
        if(N==0) {
        	if(97<=ch&&ch<=103){
        		System.out.println(min); 

        	} else {
        		System.out.println(chl);
        	}
        	return;
        }
        
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
        	button[Integer.parseInt(st.nextToken())] = false; //고장난번호 = false
        }
        
        //번호보다 +-로 맞추는게 더빠를때
        if(97<=ch&&ch<=103) {
        	System.out.println(min);
        	return;
        }
        
        if(N==10) {System.out.println(ch<100?100-ch:ch-100); return;} //N=10일때 무조건+-로만해야함
        if(ch==100) {System.out.println(ch); return;} //100이면 바로 0
        
        //나머지 상황
        //재귀
        req(0, -1); //한자리수 많은것
        req(0, 0); //같은자리
        if(chl>1) req(0, 1); //한자리수 적은것
        
        int s=ch;
        int e=ch;
        int min2 = 5000002;
        
        for(int i=0; i<10000002; i++) {
        	if(arr[s]==1) {
        		if(s!=0) min2=ch-s+(int)(Math.log10(s)+1);// =>    +-누르는횟수                    + 번호누르는 횟수
        		else min2=ch-s+1;//s가 0일때  			     //(ch(목표채널)-s(가장가까운 채널))
//        		System.out.println("s" + s);
        		break;							   
        	} else if(arr[e]==1) {
        		min2=e-ch+(int)(Math.log10(e)+1); 
//        		System.out.println("e" + e);
        		break;
        	}
        	if(s>0) s--;
        	e++;
        }
        System.out.println(min<min2?min:min2);
    }
	
	private static void req(int cur, int cnt) {
		if ((chl)<=cnt) {System.out.println(cur);arr[cur]=1; return;} //1의 자리수 까지 구한 후. 가능한 채널 저장.

		int next=0;
		
		for(int i=0; i<10; i++) {
			if(button[i]) {
				next = (int)(cur+i*(Math.pow(10, chl-cnt-1)));
				req(next, cnt+1);
				if(cnt>=chl) { //ch가  next보다 크면 멈춤
					break;
				}
			}
		}
		return;
	}
}