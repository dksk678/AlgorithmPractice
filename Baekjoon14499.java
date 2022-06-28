import java.io.*;
import java.math.BigInteger;
import java.util.*;
/*  주사위 굴리기 14499  55
 * 
 *  동서남북으로 굴릴 때 주사위 값만 잘 변경해주면됨
 *  
 *  동 = 2,5 빼고  6->4,  4->1,  1->3,  3->6 
	서 = 2,5 빼고  4->6,  6->3,  3->1,  1->4
	북 = 4,3 빼고  1->2,  2->6,  6->5,  5->1
	남 = 4,3 빼고  2->1,  1->5,  5->6,  6->2
 *  
 */
class Baekjoon14499 { 
	static int[][] map;
	static int[] dice;
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //set
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int move = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        dice = new int[7]; //1부터 시작, 1=위
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
	        for(int j=0; j<M; j++) {
	        	map[i][j] = Integer.parseInt(st.nextToken());
	        }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<move; i++) {
        	int d = Integer.parseInt(st.nextToken());
        	switch (d) {
			case 1: { //동
				if(y+1>=M) break;
				
				int tmp = dice[6];
				dice[6] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[4];
				dice[4] = tmp;
				
				y+=1;
				change(x,y);
				bw.write(dice[1]+"\n");
				break;
			}
			case 2: { //서
				if(y-1<0) break;
				
				int tmp = dice[6];
				dice[6] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[3];
				dice[3] = tmp;
				
				y-=1;
				change(x,y);
				bw.write(dice[1]+"\n");
				break;
			}
			case 3: { //북
				if(x-1<0) break;
				
				int tmp = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[6];
				dice[6] = tmp;
				
				x-=1;
				change(x,y);
				bw.write(dice[1]+"\n");
				break;
			}
			default: //남
				if(x+1>=N) break;
				
				int tmp = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[5];
				dice[5] = tmp;
				
				x+=1;
				change(x,y);
				bw.write(dice[1]+"\n");
			}
//        	System.out.print(x+" "+y+" : ");
//        	print(dice);
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void change(int x, int y) {
		if(map[x][y]==0) {
    		map[x][y] = dice[6];
    	} else {
    		dice[6] = map[x][y];
    		map[x][y] = 0; 
    	}
	}
	
	static void print(int[][] arr) {
		for(int[] i:arr) {
			for(int j:i) {
				System.out.print(j +" ");
			}
			System.out.println("");
		}
	}
	static void print(int[] arr) {
		for(int i:arr) {
			System.out.print(i +" ");

		}
		System.out.println("");
	}
}