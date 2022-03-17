import java.io.*;
import java.util.*;

/*  재귀함수가 뭔가요?  17478 14:40
 *  n만큼  ____ 언더바 4개 + 재귀함수가 뭔가요? \n "잘들어보게.~" 출력
 *  
 */

class Baekjoon17478{
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        String a = bf.readLine();
        int N = Integer.parseInt(bf.readLine());
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
        recursive(N, 1);
        sb.append("라고 답변하였지.");
        System.out.println(sb);
    }
	//0인지 1인지 비교해서 변경된 값 넣기
	private static void recursive (int num, int cnt) {
		String st1 = "\"재귀함수가 뭔가요?\"\n";
        String st2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
        String st3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
        String st4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
        String st5 = "라고 답변하였지.\n";
        String st6 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
        String ub = "";
        
        for(int i=1; i<cnt; i++) { //처음은 x 두번째 부터 언더바 추가
        	ub += "____";
        }
        sb.append(ub).append(st1);
        sb.append(ub).append(st2);
        sb.append(ub).append(st3);
        sb.append(ub).append(st4);
        
        if(num>cnt++) { //N전까지는 반복
        	recursive(num, cnt); //
        	sb.append(ub+"____").append(st5); //답변
        } else { //N번째면 함수라네."~ 답변
        	ub += "____";
        	sb.append(ub).append(st1); //st1
            sb.append(ub).append(st6);
            sb.append(ub).append(st5);
        	return;
        }
        return;
	}
}