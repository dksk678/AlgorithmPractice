import java.io.*;
import java.util.*;

/*  스위치 켜고 끄기  1244 20:10~21:00
 *  남학생 = 해당번호 배수의 스위치 상태 바꿈
 *  여학생 = 번호 좌우가 같을때의 구간 전부 상태 바꿈.
 *  
 *  남학생; num의 배수를 구하면됨
 *  여학생; 
 *    1. 좌우 크기 먼저 비교 -> 배열 크기보다 작으면 계속
 *     -> 2. 좌우 번호가 같으면  상태 바꾸고 다음칸 비교위해 cnt++
 *       -> 1번에서 배열크기를 벗어나기 전까지 반복
 *       
 *  스위치는 한줄에 20개씩 20개마다 줄바꿈 추가
 */

class Baekjoon1244{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
//        String a = bf.readLine();
        int N = Integer.parseInt(bf.readLine());
        int[] switch_arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
        	switch_arr[i] = Integer.parseInt(st.nextToken());
        }
        N = Integer.parseInt(bf.readLine());
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	if(st.nextToken().equals("1")) { //남학생이면
        		switch_arr = manf(switch_arr, Integer.parseInt(st.nextToken()));
        	} else {//여학생이면
        		switch_arr = womanf(switch_arr, Integer.parseInt(st.nextToken()));
        	}
        }
        for(int i=0; i<switch_arr.length; i++) {
        	sb.append(switch_arr[i]).append(" ");
        	if((i+1)%20==0) {
        		sb.append("\n");
        	}
        }
        System.out.println(sb);
    }
    //남학생 = 해당번호 배수의 스위치 상태 바꿈
    private static int[] manf(int[] arr, int num) {
    	int num2 = num;
    	while(num<=arr.length) {
    		comp(arr, num);
    		num += num2;
    	}
    	return arr;
    }
    //여학생 = 주어진번호의 좌우가 같을 때 구간 전부 상태 바꿈.
	private static int[] womanf(int[] arr, int num) {
			int cnt = 1;
			comp(arr, num);     //0<=2     8>4,    0<=1&&8>5
			while(0<num-cnt&&arr.length>=num+cnt) {
				if(arr[num-cnt-1]==arr[num+cnt-1]) {
					comp(arr, num-cnt);
					comp(arr, num+cnt);
				} else {
					break;
				}				
				cnt++;
			}
	    	return arr;
	}
	//0인지 1인지 비교해서 변경된 값 넣기
	private static int[] comp(int[] arr, int num) {
		if(arr[num-1]==1) {
			arr[num-1]=0;
		} else {
			arr[num-1]=1;
		}
		return arr;
	}
}