import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21608_상어초등학교_G5 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int pn = N*N;
		ArrayList<int[]> stu = new ArrayList<>();
		for (int i = 0; i <= pn; i++) {
			stu.add(new int[4]);
		}
		int[] nums = new int[pn];
		for (int i = 0; i < pn; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int s3 = Integer.parseInt(st.nextToken());
			int s4 = Integer.parseInt(st.nextToken());
			
			stu.get(c)[0] = s1;
			stu.get(c)[1] = s2;
			stu.get(c)[2] = s3;
			stu.get(c)[3] = s4;
			nums[i] = c;
		}
		
		System.out.println(getSum(N, nums, stu));
	}

	private static int getSum(int n, int[] nums, ArrayList<int[]> stu) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0 ,-1, 0};
		
		PriorityQueue<student> pq = new PriorityQueue<>();
		
		int[][] arr = new int[n][n];
		int cur = 0;
		int pn = n*n;
		while(true) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j]!=0) continue;
					int blank = 0;
					int like = 0;
					for (int j2 = 0; j2 < 4; j2++) {
						int nr = i+dr[j2];
						int nc = j+dc[j2];
						if(nr<0||nr>=n||nc<0||nc>=n) continue;
						
						int adj = arr[nr][nc];
						
						if(adj==0) {
							blank++;
						} else {
							like += findLike(adj, stu.get(nums[cur]));
						}
					}
					
					pq.offer(new student(cur, i, j, like, blank));
				}
			}
			student sc = pq.poll();
			arr[sc.r][sc.c] = nums[cur];
			
			pq.clear();
			cur++;
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println("");
			
			if(cur==pn) break;
		}
		
		int sum = 0;
		int like = 0;
		cur = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				like = 0;
				for (int j2 = 0; j2 < 4; j2++) {
					int nr = i+dr[j2];
					int nc = j+dc[j2];
					if(nr<0||nr>=n||nc<0||nc>=n) continue;
					
					like += findLike(arr[nr][nc], stu.get(arr[i][j]));
//					System.out.println(arr[i][j]+" "+arr[nr][nc]+" "+like);
				}
				if(like==0) continue;
				
				sum += Math.pow(10, like-1);
			}
		}
		
		return sum;
	}
	
	
	private static int findLike(int adj, int[] stu) {
		for(int i: stu) {
			if(i==adj) {
				return 1;
			}
//			System.out.println("#####"+adj+" "+i);
		}
		return 0;
	}


	static class student implements Comparable<student>{
		int cur, r, c, like, blank;

		public student(int cur, int r, int c, int like, int blank) {
			super();
			this.cur = cur;
			this.r = r;
			this.c = c;
			this.like = like;
			this.blank = blank;
		}

		@Override
		public int compareTo(student o) {
			int ld = o.like - this.like; //like가 많은 순으로
			if(ld==0) {
				int bd = o.blank - this.blank; //블랭크가 많은 순
				if(bd==0) {
					int rd = this.r - o.r; //행번호가 낮은 순으로
					if(rd==0) {
						return this.c - o.c;
					}
					return rd;
				}
				return bd;
			}
			return ld;
		}
		
	}
}
