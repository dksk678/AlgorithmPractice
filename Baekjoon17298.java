import java.io.*;
import java.util.*;
/*  오큰수 17298 120
 *   
 *  스택활용.
 *  1. 스택에 현재 값 넣기
 *  2. 스택top값 보다 다음 수가 더 크면 pop 후 값 저장
 *     -> 스택top 값이 다음 수보다 커지는 수가 나올 때 까지 1번 반복
 *  
 *  N번 까지 1~2 반복
 */
class Baekjoon17298 { 
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(0);
        
        int[] ans = new int[N];
        ans[N-1] = -1;
        
        for(int i=1;i<N; i++) {
        	if(arr[stack.peek()]<arr[i]) {
        		while(stack.size()!=0) {
        			if(arr[stack.peek()]<arr[i]) ans[stack.pop()] = arr[i]; //다음 수가 더크면 출력할 값 저장
        			else break;
        		}
        	}
        	stack.add(i);
        }
        for(int i:ans) {
        	bw.write((i==0?-1:i)+" ");
        }

        bw.flush();
        bw.close();
        br.close();
	}
	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}