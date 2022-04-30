import java.io.*;
import java.util.*;
/*  치킨배달 15686 90
 *  NxN 크기
 *  1=집, 2=치킨집
 *  치킨집에서 모든 집까지 거리를 저장.
 *  M만큼 집까지의 최소값들을 더하기.
 */
class Baekjoon15686 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int M;
	static ArrayList<int[]> chicken;
	static ArrayList<int[]> house;
	static int[][] dis;
	static int ans=2100000000;
	static int tmp=0;

	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //set
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        house = new ArrayList<int[]>();
        chicken = new ArrayList<int[]>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<N; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		if(num==1) house.add(new int[] {i, j});
        		if(num==2) chicken.add(new int[] {i, j});
        	}
        }
        //치킨집에서 각집마다의 거리 구하기
        dis = new int[chicken.size()][house.size()];
        for(int i=0; i<chicken.size(); i++) {
        	int cx = chicken.get(i)[0];
        	int cy = chicken.get(i)[1];
        	for(int j=0; j<house.size(); j++) {
        		int hx = house.get(j)[0];
            	int hy = house.get(j)[1];
        		dis[i][j] = Math.abs(cx-hx)+Math.abs(cy-hy);
        	}
        }
        //치킨집M개에서 모든집의 최소거리 구하기
        for(int i=0; i<=chicken.size()-M; i++) {
        	req(1, dis[i], i+1);
        }
        System.out.println(ans);
	}
	
	static void req(int cnt, int[] a, int k) {
		if(cnt==M) {//치킨집이 M개가 되면 멈춤
			for(int i=0; i<house.size(); i++) {
				tmp += a[i]; //선택된 치킨집들에서 집과의 거리 합
			}
			ans = tmp<ans?tmp:ans;
			tmp = 0;
			return;
		}
		int[] a1 = new int[house.size()];
		for(int i=k; i<chicken.size(); i++) {
	        for(int j=0; j<house.size(); j++) {
		        a1[j] = Math.min(a[j], dis[i][j]);//치킨집[i]에서 집[j]까지의 최소거리 구하기.
			}
	        req(cnt+1, a1, i+1);
		}
	}
}