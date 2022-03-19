import java.io.*;
import java.util.*;

/*  기상 캐스터 10709 18:30~18:50
 *  H,W 크기의 배열 중 c(구름)를 몇번째에 만날 수 있는가 
 *  
 *  1. 처음 c가 주어지는 곳은 0 번째
 *  2. c는 오른쪽으로 진행.(cnt++) -> c가아닌 다음 곳은 1번째
 *  3. 오른쪽으로 가다가 새로운 c가 발견되면 다시 0번 째로 바뀜  == 1. 다시 시작
 *  -> 1. 2. 3. 반복   
 *  
 *  # 구름이 올 수 없는 곳들은 -1  ex)구름이 두번째부터 오면 첫번째는 영원히 올 수 없음. 오른쪽으로만 가기 때문에
 */

class Baekjoon10709{
//	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st= new StringTokenizer(bf.readLine());
        int h = Integer.parseInt(st.nextToken());
    	int w = Integer.parseInt(st.nextToken());
    	int[][] arr = new int[h][w];
    	int chk = 0;
    	for(int i=0; i<h; i++) {
    		Arrays.fill(arr[i], -1); //-1로 초기화
    	}
        int cnt=0;
        String str;
        
        for(int i=0; i<h; i++) {
        	str =  bf.readLine();  
        	cnt = 0;
        	chk = 0;
        	for(int j=0; j<w; j++) {
        		if(str.charAt(j) == 'c') { // 1.
        			cnt = 0;
        			arr[i][j]=cnt;
        			chk = 1; // #구름이 올 수 있는 지 체크
        		} else if (chk==1){ //구름이 올 수 있으면 2. 
        			cnt++;
        			arr[i][j]=cnt;
        		}
        	}
        }
        for(int i=0; i<h; i++) {
        	for(int j=0; j<w; j++) {
        		sb.append(arr[i][j]).append(" ");
        	}
        	sb.append("\n");
        }  
        System.out.println(sb);
    }
}