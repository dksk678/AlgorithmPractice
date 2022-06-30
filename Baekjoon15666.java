import java.io.*;
import java.util.*;
/*  N과N 12 15666 90
 * 
 *  백트래킹
 */
class Baekjoon15666 { 
	static int N,M;
	static int[] arr;
	static int[] num;
	static StringBuilder sb;
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        //set
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        num = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        DFS(0, 0);
        
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void DFS(int cnt, int idx) {
		if(cnt==M) {
			for(int i:num) {
				sb.append(i+" ");
			}
			sb.append("\n");
			return;
		}
		int last = 0;
		for(int i=idx; i<N; i++) {
			if(arr[i]==last) continue;
			
			num[cnt] = arr[i];
			last = num[cnt];
			DFS(cnt+1, i);
		}
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