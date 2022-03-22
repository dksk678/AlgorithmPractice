import java.io.*;
import java.util.*;

/*	나무 조각
 *  1~2비교 후 바꾸면 출력
 *  2~3비교 후 바꾸면 출력
 */ 
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] arr = new int[5]; //입력 받을 배열
        //배열에 들어갈 숫자를 입력 받음
        for(int i=0; i<5; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        int tmp = 0; // 비교후 값을 바꾸기 위한 변수
        int[] ans = {1,2,3,4,5}; //12345가 맞는지 비교하기위한 배열
        int i=1;
        while(true) {
        	if (Arrays.equals(ans, arr)) {
        		System.out.println(sb);
        		return;
        	}
        	if (i==5) { //배열을 한번 돌았으면 초기화 왜냐하면 한번돌고 12345가 되지않는 경우가 있기 때문에
        		i=1;
        	}
        	if(arr[i-1]>arr[i]) {
        		tmp = arr[i];
        		arr[i] = arr[i-1];
        		arr[i-1] = tmp;
        		for(int j=0; j<5; j++) {
        			sb.append(arr[j]).append(" ");
        		}
        		sb.append("\n");
        	}
        	i++;
        }    
    }
}