import java.io.*;
import java.util.*;
/*  크게 만들기 2812 60+30
 *  
 *  N만큼 stack에 저장
 *  다음 숫자가 현재 저장된 숫자들 보다 크면 저장된 숫자들 pop
 *   -> pop횟수가 K번 될 때까지 반복. 
 *  **cnt비교 실수
 */
class Main {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, K, max=0;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        st = new StringTokenizer(br.readLine());  
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        String str = br.readLine();
        for(int i=0; i<N; i++) {
        	arr[i] = str.charAt(i)-'0';
        }
        Stack<Integer> stack = new Stack<Integer>();
        
        int cnt = 0; //뺀 숫자 개수
        
        for(int i=0; i<N; i++) {
        	//뺀 숫자 개수가 k보다 적고, 현재 저장된 수들이 다음 수보다 작을 때
        	while(!stack.isEmpty()&&stack.peek()<arr[i]&&cnt<K) {
        		stack.pop();
        		cnt++;
        	}
        	stack.add(arr[i]);
        }
        //동일한 수가 여러개있어서 k개만큼 빠지지 않았을 때
        while(cnt<K) {
        	stack.pop();
        	cnt++;
        }
        //print
        for(int i:stack) {
        	bw.write(i+"");
        }
        bw.flush();
        bw.close();
        br.close();
	}
}