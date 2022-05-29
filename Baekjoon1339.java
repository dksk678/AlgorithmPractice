import java.io.*;
import java.util.*;
/*  단어 수학 1339 180
 *  
 *  길이가 더 긴 수부터 무조건 -> 큰수
 *  길이가 같으면 더 많이 사용 되는 수 ->  그다음수
 *  
 *  1. 알파벳 자리별로 수를 구함
 *  2. 같은 알파벳이면 수 더하기
 *  3. 자리수의 합이 가장 큰 것부터 9임
 *  ** 알파벳은 랜덤임 A~Z;
 */
class Baekjoon1339 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];
        int[] arr2 = new int[26];
        
        for(int i=0; i<N; i++) {
        	strArr[i] = br.readLine();
        }

        for(int i=0; i<N; i++) { //알파벳 자리별 값 저장
        	int num = 0;
        	String str = strArr[i];
        	for(int j=0; j<str.length(); j++) {
            	num = str.charAt(j)-'A';
            	arr2[num]+=Math.pow(10, str.length()-j-1);
            }
        }
        
        Arrays.sort(arr2); //자리 합이 가장 큰거 부터
        
        int sum = 0;
        int cnt = 9;
        for(int i=25; i>0; i--) {
        	if(i==0) {
        		continue;
        	}
        	sum += arr2[i]*cnt--;
        }
    
        bw.write(sum+"");
        
        bw.flush();
        bw.close();
        br.close();
	}
}