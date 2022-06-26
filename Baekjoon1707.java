import java.io.*;
import java.util.*;
/*  이분 그래프 1707 120
 *  
 *  이어진 노드에서 가지고 있는 값이 서로 다른 경우 이분 그래프라고함 
 */
class Baekjoon1707 { 
	static int N, M, R;
	static int[] v;
	static ArrayList<ArrayList<Integer>> arr;
	static String ans = "";
	
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
        	arr = new ArrayList<ArrayList<Integer>>();
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	for(int i=0; i<=N; i++) {
        		arr.add(new ArrayList<Integer>());
        	}
        	
	        for(int i=0; i<M; i++) {
	        	st = new StringTokenizer(br.readLine());
	        	int a = Integer.parseInt(st.nextToken());
	        	int b = Integer.parseInt(st.nextToken());
	        	arr.get(a).add(b);
	        	arr.get(b).add(a);
	        }
	        
	        ans = "YES";
	        v = new int[N+1];
	        for(int i=1; i<=N; i++) {
	        	if(v[i]==0) {
	        		if(!BFS(i)) {
	        			ans = "NO";
	        			break;
	        		}
	        	}
	        }
	        bw.write(ans+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
	}
	
	static boolean BFS(int start) {
		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(start); //1, -1
		v[start] = 1;
		
		while(!q.isEmpty()) {
			int num =  q.poll();
			System.out.println(num);
			for(int i:arr.get(num)) {
        		if(v[i]==0) {
        			q.offer(i);
        			v[i] = v[num]*-1;
        		} else if(v[i]==v[num]) {
        			return false;
        		}
			}
		}
		return true; 
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