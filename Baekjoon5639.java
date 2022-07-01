import java.io.*;
import java.util.*;
/*  이진 검색 트리 5639 120
 *  
 *  이진 트리 구현.
 *  루트 노드 부터 자식이 있는 지 확인.
 *  자식 없으면 새로운 노드 저장
 *  자식 있으면 다음 자식 탐색
 *  
 */
class Baekjoon5639 { 
	static int N,M;
	static int[] arr;
	static int[] num;
	static StringBuilder sb;
	//예외처리 해주는 이유 예기치 못한 예외의 발생에 대해 미리 대처하기 위함
	public static void main(String[] args) throws IOException{ //throws IOException 하는 이유, 입출력 예외처리를 해줘야해서
		//Buffer를 쓴 이유는 입력된 데이터가 바로 전달되지 않고 버퍼를 통해 전달되서 데이터 처리 효율성을 높임.
		//buffer에 저장하여 한번에 내용 전송하여 훨씬 빠르고 효율적임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        
        //set
        String num;
        Tree t = new Tree();
        
        while((num=br.readLine()) != null) {
        	if(num.equals("")) break;
        	t.insert(t.root, Integer.parseInt(num));
        }
        
        postorder(t.root);
        
        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static class Node {
		int par;
		Node left;
		Node right;
		
		public Node(int par){
			this.par = par;
		}
	}
	
	static class Tree{
		Node root;

		void insert(Node node, int num) {
			if(node==null) {
				root = new Node(num);
				return;
			}
			
			//자식 노드가 있으면 다음 자식 탐색
			if(num < node.par) {
				if(node.left == null) { 
					node.left = new Node(num);
				} else {
					insert(node.left, num);
				}
			} else {
				if(node.right == null) {
					node.right = new Node(num);
				} else {
					insert(node.right, num);
				}
			}
		}
	}
	
	static void postorder(Node node) {
		if(node==null) return;
		
		postorder(node.left);
		postorder(node.right);
		sb.append(node.par+"\n");
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