import java.io.*;
import java.util.*;
/* 초기 배열과 임시 배열을 생성
 * 임시배열을 정렬함
 * 정렬된 임시 배열을 통해 hashMap을 사용하여 좌표와 카운트 저장
 * hashMap에 키 값이 없으면 카운트++
 * -> hashMap과 초기 배열을 값을 비교하여 결과 출력
 * 
 */

class Baekjoon18870{
	static int[] arr2; //
	static int[] tmp;  //mergeSort할때 사용할 임시 배열
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        int[] arr1 = new int[n];
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        for(int i=0; i<n; i++) {
        	String s = st.nextToken();
        	arr1[i] = Integer.parseInt(s);//처음 주어진 배열
        }
        arr2 = arr1.clone();

        //합병정렬
        tmp = new int[arr2.length];
        if(arr2.length==1) {
        	tmp[0] = arr2[0];
        }
        merge(0, arr2.length-1);
        
        //좌표수 저장
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(); //해쉬맵 이용, 좌표와 카운트 같이 저장
        for(int i=0; i<n; i++) {
        	if(!hmap.containsKey(tmp[i])) { //해쉬맵에 키가 없으면  cnt++
        		hmap.put(tmp[i], cnt++);
        	}
        }
        //처음제공된 배열을 돌려서 해쉬맵에 있는 값을 가져옴. ex 1값이면  key가 1인 해쉬맵의 cnt값을 가져옴.
        for(int i=0; i<n; i++) {
        	sb.append(hmap.get(arr1[i])).append(" ");
        }
        
        //출력
        System.out.println(sb);
    }
    
    static void merge(int left, int right) {
    	if (left<right) {
    		int mid = (left+right)/2;
    		merge(left, mid);
    		merge(mid+1, right);
    		
    		int s = left;
    		int e = mid+1;
    		int cnt = s;
    		
    		while(s<=mid||e<=right) {
    			if(e>right || (s<=mid && arr2[e]>arr2[s])) { //배열을 다 돌기 전 비교
    				tmp[cnt++] = arr2[s++];
    			} else  { //반대
    				tmp[cnt++] = arr2[e++];
    			}
    		}
    		for(int i=left; i<=right; i++) {
    			arr2[i] = tmp[i];
    		}
    	} 
    }
}

