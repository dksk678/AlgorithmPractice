import java.io.*;
import java.util.*;

/*	스택 구현
 *  push(X): 정수 X를 스택에 넣는 연산
 *  pop: 정수를 빼고 그 수를 출력
 *  size: 스택에 들어있는 정수의 개수를 출력   
 *  empty: 비어있으면 1, 아니면 0 출력
 *  front: 제일 앞에있는 정수 출력 없으면 -1
 *  back 제일 뒤에있는 정수 출력 없으면 -1
 *  !!Linkedlist를 이용하여 Arraylist remove의 시간복잡도인 O(n)을 O(1)로 해결.
 */ 
class Baeckjoon18258{
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        LinkedList<Integer> arr = new LinkedList<>();
        StringTokenizer st;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	switch (st.nextToken()) {
        		case "push": 
        			arr.add(Integer.parseInt(st.nextToken()));
					break;
        		case "pop": 
        			if(arr.isEmpty()) {
				  	sb.append("-1").append("\n");
			    	} else {
					  sb.append(arr.get(0)).append("\n");
					  arr.remove(0);
			    	}
				    break;
        		case "back": 
        			if(arr.isEmpty()) {
					  sb.append("-1").append("\n");
				    } else {
					  sb.append(arr.get(arr.size()-1)).append("\n");
				    }
				    break;
        		case "size": 
        			sb.append(arr.size()).append("\n");
					break;
        		case "empty": 
        			if(arr.isEmpty()) {
				  	  sb.append("1").append("\n");
				    } else {
					  sb.append("0").append("\n");
				    }
				    break;
        		case "front": 
        			if(arr.isEmpty()) {
        				sb.append("-1").append("\n");
				    } else {
					  sb.append(arr.get(0)).append("\n");
				    }
        			break;
        		default:
				  break;
        	}
        }
        System.out.println(sb);
    }
}