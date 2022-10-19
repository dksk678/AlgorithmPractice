import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12015_가장긴증가하는부분수열2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] lis = new int[N];
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			int idx = Arrays.binarySearch(lis, 0, max, arr[i]);
			
			if(idx>=0) continue; //0이상이 되면 찾는 값이 이미 있는 경우.
			//현재 값이 lis배열에 없으면 -(삽입위치+1) 값을 반환함.
			idx = Math.abs(idx)-1;
			
			lis[idx] = arr[i];
			if(idx==max) { //위치가 max위치면 max 증가
				max++;
			}
		}
		
		System.out.println(max);
	}

}