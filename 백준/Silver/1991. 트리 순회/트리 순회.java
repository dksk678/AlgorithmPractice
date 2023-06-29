import java.io.*;
import java.util.*;
/*  트리 순회  1991  90
 *  pre: par->left->right
 *  in: left->par->right
 *  post: left->right->par
 */
class Main {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T;

	static String preans="";
	static String inans="";
	static String postans="";
	static HashMap<String, Node> tree;
	
	static class Node{
		String left;
		String right;
		
		public Node(String left, String right) {
			this.left = left;
			this.right = right;
		}
	}
	public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(bf.readLine());
        tree = new HashMap<String, Node>();

        for(int i=0; i<T; i++) {
        	st = new StringTokenizer(bf.readLine());
        	tree.put(st.nextToken(), new Node(st.nextToken(), st.nextToken()));
        }
        order("A");

        sb.append(preans+"\n").append(inans+"\n").append(postans+"\n");
        System.out.println(sb);
	}
	static void order(String cur) {
		if (cur.equals(".")) return;
		
		preans += cur; 
		order(tree.get(cur).left);
		inans += cur;
		order(tree.get(cur).right);
		postans += cur;
	}
}