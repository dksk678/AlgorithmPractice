import java.io.*;
import java.util.*;
/*  집합의 표현 1717 80+40(시간 줄이기)
 *  
 *  M개의 입력
 *  0 a b = 합집합 형태 a+b
 *  1 a b = 같은 집합에 포함되어 있는 지
 *  
 *  -> 분리 집합
 *  union: 집합들을 합침
 *  find: 부모 노드를 찾음
 *  rank를 통해 트리 크기를 구해서 합칠 때 더 작은 노드쪽으로 붙음 (120ms 더 빠름)
 *  bw에 전부 저장이 더 빠름
 */
class Main {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, M;
	static int[] parents;
	static int[] rank;
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //set
        st = new StringTokenizer(br.readLine());  
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        rank = new int[N+1];
        
        for(int i=0; i<=N; i++) {
        	parents[i] = i;
        }
        for(int i=0; i<=N; i++) {
        	rank[i] = 1;
        }
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int chk = Integer.parseInt(st.nextToken());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	if(chk==1) {
    			if(find(a)==find(b)) bw.write("YES\n");
    			else bw.write("NO\n");
        	} else {
        		union(a, b);
        	}
        }
        bw.flush();
        bw.close();
        br.close();
	}
	
	static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a]=find(parents[a]); //부모를 찾을 때마다 업데이트 해주면 다음 탐색때 시간 단축 가능
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return;
		
		int tmp = 0;
		//트리가 작은 쪽과 합침
		if(rank[a]>rank[b]) {
			tmp = b;
			b = a;
			a = tmp;
		}
		parents[a] = b;	
		
		//트리 크기가 같으면 다음 a트리 크기 +1, a가 항상 큰 쪽
		if(rank[a]==rank[b]) rank[a]++;
	}
}