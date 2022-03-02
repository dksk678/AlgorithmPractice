import java.io.*;
import java.util.*;

/*	스택 구현
 *  push(X): 정수 X를 스택에 넣는 연산
 *  pop: 정수를 빼고 그 수를 출력
 *  size: 스택에 들어있는 정수의 개수를 출력   
 *  empty: 비어있으면 1, 아니면 0 출력
 *  top: 제일 위에있는 정수를 출력, 정수가 없으면 -1
 */ 

class Main{
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int cnt = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<N; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	switch (st.nextToken()) {
        		case "push": arr.add(Integer.parseInt(st.nextToken()));
					cnt++;
					break;
        		case "pop": if(cnt-1<0) {
					  sb.append("-1").append("\n");
				    } else {
					  sb.append(arr.get(--cnt)).append("\n");
					  arr.remove(cnt);
				    }
				    break;
        		case "size": sb.append(arr.size()).append("\n");
					break;
        		case "empty": if(arr.size()==0) {
				  	  sb.append("1").append("\n");
				    } else {
					  sb.append("0").append("\n");
				    }
				    break;
        		case "top": if(arr.size()==0) {
				  	sb.append("-1").append("\n");
				    } else {
					  sb.append(arr.get(cnt-1)).append("\n");
				    }
        			break;
        		default:
				  break;
        	}
        }
        System.out.println(sb);
    }
}

