import java.io.*;
import java.util.*;

/*  AC 5430
 *  정수 배열 연산
 *  R 뒤집기 순서 뒤집기
 *  D 버리기 첫 수 버리기
 */

class Baekjoon5430 {
	static StringBuilder sb = new StringBuilder();
//	static StringBuilder sb;
	static StringTokenizer st;
	static int T;
	
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(bf.readLine());
        while(T-->0) {
	       String p = bf.readLine();
	       int len = Integer.parseInt(bf.readLine());
	       boolean isZero = false;
	       boolean isReverse = false;
	       Deque<String> arr = new ArrayDeque<String>();
	       
	       st = new StringTokenizer(bf.readLine(), "[],");

	       for(int i=0; i<len; i++) {
	    	   arr.add(st.nextToken());
	       }  
	       
	       for(int i=0; i<p.length(); i++) {
	    	   if(p.charAt(i)=='D') {
	    		   if(arr.size()==0) {isZero = true; break;}
	    		   if(isReverse) arr.pollLast();
	    		   else arr.pollFirst();
	    	   } else {
	    		   isReverse = !isReverse;
//	    		   arr = R(arr);
	    	   }
	       }
	       
	       if(isZero) {sb.append("error").append("\n"); continue;}
	       if(arr.size()==0) {sb.append("[]").append("\n"); continue;}
	       
	       sb.append("[");
	       int al = arr.size()-1;
	       
	       if(isReverse) {
	    	   for(int i=0; i<al; i++) {
	    		   sb.append(arr.pollLast()).append(",");
		       }
	       } else {
	    	   for(int i=0; i<al; i++) {
	    		   sb.append(arr.pollFirst()).append(",");
		       }
	       }
	       sb.append(arr.poll()).append("]").append("\n");
        }
        
	    System.out.println(sb);
	    
    }
}