import java.io.*;
import java.util.*;
/*  수 묶기 1744 60
 *  
 *  pos, ngt 개수 따로 구함
 *  1. 음수면 가장 낮은 수들 곱하기
 *  2. 양수면 가장 높은 수들 곱하기
 *  3. 1이면 곱하지말고 더하기
 */
class Baekjoon1744 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> nNum = new ArrayList<Integer>();
        ArrayList<Integer> pNum = new ArrayList<Integer>();
        int num = 0;
        for(int i=0; i<N; i++) {
        	num = Integer.parseInt(br.readLine());
        	if(num>0) pNum.add(num);
        	else nNum.add(num);
        }
        Collections.sort(nNum);
        Collections.sort(pNum, Collections.reverseOrder());
        
        int sum = 0; 
        for(int i=0; i<nNum.size(); i++) {
        	if(i%2==0) {
        		num = nNum.get(i);
        	}
        	if(i+1<nNum.size()) {
        		num *= nNum.get(i+1);
        		i++;
        	}
        	sum += num;
        }
        
        for(int i=0; i<pNum.size(); i++) {
        	if(i%2==0) {
        		num = pNum.get(i);
        	}
        	if((i+1)<pNum.size()) {
        		if(pNum.get(i+1)==1) num += 1;
        		else num *= pNum.get(i+1);	
        		i++;
        	}
        	sum += num;
        }

        bw.write(sum+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
}