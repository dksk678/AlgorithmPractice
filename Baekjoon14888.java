import java.io.*;
import java.util.*;
/*  연산자 끼워넣기 14888 30
 *  
 *  백트래킹, 
 *  사용 가능한 연산자가 있으면 해당 연사자를 계산한 후 다음으로 넘김
 *  이 때 연산자 개수줄여서 넘김
 *  cnt가 주어진 수의 개수이므로 cnt가 N이 되면 return
 *  * max가 음수 일 수 있음
 */
class Baekjoon14888 { 
	static int[] arr;
	static int[] o;
	static int N, MAX, MIN;
	static int ans;
	
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        //set
        N = Integer.parseInt(br.readLine());
    	arr = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	o = new int[4];
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<4; i++) {
    		o[i] = Integer.parseInt(st.nextToken());
    	}
    	MAX = -123456789;
    	MIN = 123456789;
    	
    	DFS(0, arr[0]);
    	
        bw.write(MAX+"\n");
        bw.write(MIN+"");

        bw.flush();
        bw.close();
        br.close();
	}
	
	static void DFS(int cnt, int num) {
		if(cnt==N-1) {
			MIN = Math.min(num, MIN);
			MAX = Math.max(num, MAX);
			
			return;
		}
		
		for(int i=0; i<4; i++) {	
			if(o[i]==0) continue;
			
			o[i]--;
			cnt++;
			switch (i) {
			case 0: {
				DFS(cnt, num+arr[cnt]);
				break;
			}
			case 1: {
				DFS(cnt, num-arr[cnt]);
				break;
			}
			case 2: {
				DFS(cnt, num*arr[cnt]);
				break;
			}
			default:
				DFS(cnt, num/arr[cnt]);
			}
			cnt--;
			o[i]++;
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
}