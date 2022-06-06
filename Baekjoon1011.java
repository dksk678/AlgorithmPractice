import java.io.*;
import java.util.*;
/*  Fly me to the Alpha Centauri 1011 120
 *  
 *  거리 형태는 12321 처럼 처음과 끝이 1이 될 수 있어야함.
 *  M = y-x
 *  sr = M의 제곱근
 *  M == sr^2 => ans = 제곱근*2-1
 *  else => ans = 제곱근*2-1 + (M-sr^2<=sr 성립하면 +1, 아니면 +2) 
 *  
 *  ex)
 *  12321 이면
 *  최소길이 형태는 제곱근*2-1형태에서 + @
 *  12211 이 최소 형태, 5번의 최소 길이는 7임. 
 *  7의 제곱근 = 2
 *  7-4(2의제곱)<=2 -> X = (2*2-1)+2 = 5
 *  
 *  6의 제곱근 = 2
 *  6-4<=2 -> O = (2*2-1)+1 = 4
 *  
 */
class Baekjoon1011 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static long x, y;
	static int[] parents;
	static int[] rank;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	x = Integer.parseInt(st.nextToken());
        	y = Integer.parseInt(st.nextToken());
        	int dis = (int)(y-x);
        	int srDis = (int)Math.sqrt(dis);
        	int sqaureNum = (int)Math.pow(srDis, 2);
        	if(dis==sqaureNum) { //가장 기본적인 형태, 거리가 제곱 수 인 경우
        		ans = (srDis*2-1);
        	} else {//제곱 수가 아닌 경우 + @
        		ans = (srDis*2-1)+(dis-sqaureNum<=srDis?1:2);
        	}
        	bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
	}
}