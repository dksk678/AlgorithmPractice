import java.io.*;
import java.util.*;
/*  미로1  1226 D4  14:45
 *  
 *  BFS 기본
 */
class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] arr;
	static boolean ans;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
//        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <=10 ; test_case++)
        {   //set
        	arr = new int[16][16];
        	ans = false;
            int N = Integer.parseInt(br.readLine());
            int startx = 0;
            int starty = 0;
            //최댓값 구하기
            for(int i=0; i<16; i++) {
            	String str = br.readLine();
            	for(int j=0; j<16; j++) {
            		int num = str.charAt(j)-48;
	            	arr[i][j] = num;
	            	if(num==2) {
	            		startx = i;
	            		starty = j;
	            	}
	            }
            }
            
            bw.write("#"+test_case+" ");
            bw.write(BFS(startx, starty)+"");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int BFS(int sx, int sy) {
    	Queue<Node> q = new LinkedList<Node>();
    	boolean[][] v = new boolean[16][16];
    	
    	q.offer(new Node(sx, sy));
    	v[sx][sy] = true;
    	
    	while(!q.isEmpty()) {
    		int x = q.peek().x;
    		int y = q.poll().y;
    		
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(arr[nx][ny] == 3) {
					return 1; //범위 초과
				}
				if(arr[nx][ny]==1 || v[nx][ny]) continue;
	
				v[nx][ny] = true;
				q.offer(new Node(nx, ny));
	    	}
    	}
    	
    	return 0;
    }
    
    static class Node{
    	int x, y;
    	public Node(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}