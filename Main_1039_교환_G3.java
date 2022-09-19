import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1039_교환_G3{
	static int end;
	public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
    	st = new StringTokenizer(bf.readLine());
    	String num = st.nextToken();
    	end = Integer.parseInt(st.nextToken());
    	int l = num.length();
    	int[] nArr = new int[l];
    	boolean zerochk = false;
    	for(int i=0; i<l; i++) {
    		nArr[i] = (num.charAt(i)-48);
    		if(nArr[i]==0) zerochk = true;
    	}
    	
    	boolean samechk = false;
    	for(int i=0; i<l-1; i++) {
    		for(int j=i+1; j<l; j++) {
    			if(nArr[i]==nArr[j]) {
    				samechk = true;
    			}
    		}
		}
    	
    	if(l==1 || (zerochk&&l==2)) { //1이거나 0이 앞으로 무조건 오는 경우
    		System.out.println(-1);
    	} else {
    		System.out.println(BFS(nArr, samechk));
    	}
    }
	
	static int BFS(int[] arr, boolean sc) {
		Queue<prize> q = new LinkedList<prize>();
		int[] tmpArr = new int[arr.length];
		int[] maxArr = new int[arr.length];
		int max=0;
		boolean[] v = new boolean[1000001];
		
		prize cur = new prize(maxArr, 0);
		q.offer(new prize(arr, 0));
		
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.cnt>=end) { //횟수 까지 진행한 경우
				if(arrToNum(cur.arr)>max) {
					max = arrToNum(cur.arr);
					maxArr = cur.arr.clone();
				}
				continue;
			}
			
			for(int i=0; i<arr.length; i++) {
				for(int j=i+1; j<arr.length; j++) {
					tmpArr = cur.arr.clone();
					if(i==0&&cur.arr[j]==0) continue;
					
					tmpArr[i] = cur.arr[j];
					tmpArr[j] = cur.arr[i];
					if(v[arrToNum(tmpArr)]) { //이미 발견된 숫자인 경우
						if((end-cur.cnt)%2==0) {
//							System.out.println(Arrays.toString(tmpArr)+cur.cnt);
							if(arrToNum(cur.arr)>max) {
								max = arrToNum(cur.arr);
							}
						} else {
//							System.out.println(Arrays.toString(tmpArr)+cur.cnt);
							if(arrToNum(tmpArr)>max) {
								max = arrToNum(tmpArr);
							}
						}
						continue;
					}
					
					v[arrToNum(tmpArr)]=true;
					q.offer(new prize(tmpArr, cur.cnt+1));
				}
			}
		}
		
		return max;
	}
	
	static class prize {
		int[] arr;
		int cnt;
		public prize(int[]arr, int cnt) {
			this.arr = arr.clone();
			this.cnt = cnt;
		}
	}
	
	static int arrToNum(int[] arr) {
		int num = 0;
		for(int i=0; i<arr.length; i++) {
			num += arr[i]*Math.pow(10, arr.length-i-1);
		}
		return num;
	}
}