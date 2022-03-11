import java.io.*;
import java.util.*;

class Main{
	/* 터렛
	 * 
	 */
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        
        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            
            if (x1==x2&&y1==y2&&r1==r2) System.out.println(-1); //일치
            else System.out.println(func(x1,y1,r1,x2,y2,r2));
        }
    }
    static int func(int x1, int y1, int r1, int x2, int y2, int r2) {
    	double d = Math.sqrt(Math.abs(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)));
    	if(d>r1+r2 || Math.abs(r1-r2)>d) {  //떨어져있을 때, 내부에 있을 때
    		return 0;
    	} else if(r1+r2==d || d==Math.abs(r1-r2)){ //외접,  내접
    		return 1;
    	} else {//나머지
    		return 2;
    	}
    }
}