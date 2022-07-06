import java.io.*;
import java.util.*;
/*  거짓말 1043 150
 * 
 *   지민이 이야기할 때 진실과, 과장. 되도록이면 과장
 *   진실을 아는 사람이 있는 곳에서는 반드시 진실. 
 *   진실얘기한 파티에 한명이 다른 파티에서 거짓을 들어도 안됨.
 *   지민이가 모든 파티에 참가하여 거짓말 쟁이로 알려지지 않고, 최대 과장된 이야기를 할 수 있는 파티의 수
 *   
 *   union-find를 활용 진실을 아는 사람들(교집합) 형태로 만듬 **
 *   묶여있는 집합 중 하나라도 진실을 알고 있으면 집합 전체가 진실을 알게됨.
 *   
 *   **각 파티의 루트 노드가 진실을 알고 있는 지 체크
 */
class Baekjoon1043 { 
	static int N,M;
	static StringBuilder sb;
	static ArrayList<ArrayList<Integer>> party;
	static boolean[] truth;
	static int[] parents;
	static int[] rank;
	

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        
        
        //set
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//사람 수
        M = Integer.parseInt(st.nextToken());//파티 수0
        truth = new boolean[N+1]; //진실을 아는 가?
        
        //진실아는 사람 세팅
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int i=1; i<=t; i++) {
        	int n = Integer.parseInt(st.nextToken());
        	truth[n] = true;
        }
        //파티 정보 초기화
        party = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<M; i++) {
        	party.add(new ArrayList<Integer>());
        }
        
        parents = new int[N+1];
        rank = new int[N+1];
        for(int i=0; i<=N; i++) {
        	parents[i] = i;
        }
        for(int i=0; i<=N; i++) {
        	rank[i] = 1;
        }
        
        //파티 정보 저장
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int nop = Integer.parseInt(st.nextToken());//인원수
        	int num = Integer.parseInt(st.nextToken());
        	party.get(i).add(num);
        	for(int j=1; j<nop; j++) {
        		int num2 = Integer.parseInt(st.nextToken());
        		union(num, num2);
        		party.get(i).add(num2);
        		num = num2;
            }
        }
        //진실파티 사람들 true
        for(int i=1; i<N+1; i++) {
        	if(truth[i]) {
        		truth[find(i)] = true;
        	}
        }
        print(parents);
        //진실을 안사람들이 없는 파티개수 구하기
        int ans = 0;
        for(int i=0; i<M; i++) {
        	int p = find(party.get(i).get(0));
        	if(!truth[p]) ans++;
        }
        
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void union(int a, int b) {
		if(truth[a]||truth[b]) {
			truth[a] = true;
			truth[b] = true;
		}
		a = find(a);
		b = find(b);
		if(a==b) return;
		
		int tmp = 0;
		if(rank[a]>rank[b]) {
			tmp = b;
			b = a;
			a = tmp;
		}
		parents[a] = b;
		
		if(rank[a]==rank[b]) rank[a]++;
	}
	
	static int find(int a) {
		if(parents[a]==a) return a;
		
		return parents[a] = find(parents[a]);
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