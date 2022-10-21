import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14003_가장긴증가하는부분수열5{
	static int[] lis;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
		lis = new int[N+1];
		int[] tmp = new int[N+1];
		int max = 0;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			if(arr[i]>lis[max]) {
				max+=1;
				lis[max] = arr[i];
				tmp[i] = max;
			} else {
				idx = binarySearch(0, max, arr[i]);
				lis[idx] = arr[i];
				tmp[i] = idx;
			}
			
		}
		
		Stack<Integer> stack = new Stack<>();
		int m = max;
		for(int i=N-1; i>=0; i--) {
			if(tmp[i] == m) {
				m--;
				stack.add(arr[i]);
			}
		}
		System.out.println(stack.size());
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
	}

	private static int binarySearch(int left, int right, int num) {
		int mid = 0;
		while(left<right) {
			mid = (left+right)/2;
			if(lis[mid]<num) {
				left = mid+1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}
}