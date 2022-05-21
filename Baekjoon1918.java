import java.io.*;
import java.util.*;
/*  후위 표기식 1918 90
 *  
 *  후위 표기.
 *  괄호안 -> *,/ -> +-
 *  1. 문자면 바로 출력
 *  2. 기호면 스택에 넣기.
 *  3. )가 오면 ( 나올 때까지 계속 pop
 *  
 *  예외 1.a*b+c -> 스택에 *에 있음 ()없이 +가 오면 pop후 push
 *  예외 2. *,/가 연속적으로 나올 때 fifo순서임 
 */
class Baekjoon1918 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static String str;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        str = br.readLine();
        Stack<Character> stack = new Stack<Character>();
        
        for(int i=0; i<str.length(); i++) {
        	if(str.charAt(i)-65>=0) { //문자면 바로 출력
        		bw.write(str.charAt(i));
        	} 
        	else if(str.charAt(i)=='(') {
        		stack.push(str.charAt(i));
        	} 
        	else if (str.charAt(i)=='*'||str.charAt(i)=='/') { 
        		if(!stack.isEmpty()) {
        			if(stack.peek()=='*'||stack.peek()=='/') { //이 전에 *,/가 있으면 먼저 출력
        				bw.write(stack.pop());
        			}
        		}
        		stack.push(str.charAt(i));
        	} 
        	else if (str.charAt(i)=='+'||str.charAt(i)=='-') {
        		while(!stack.isEmpty()) { // +가 나오면 이전에 있던 기호들 전부 출력
        			if(stack.peek()=='(') break; //괄호 안에있는 +면 괄호 안 까지만 출력
                	bw.write(stack.pop());
                }
        		stack.push(str.charAt(i));
        	} 
        	else if (str.charAt(i)==')') {
        		while(stack.peek()!='(') { //괄호 안에있는 기호들 출력
        			bw.write(stack.pop());
        		}
        		stack.pop(); //여는 괄호는 출력 x
        	}
        }
        
        while(!stack.isEmpty()) {
        	bw.write(stack.pop());
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
}