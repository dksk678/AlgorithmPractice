import java.io.*;
import java.util.*;

/*	잃어버린 괄호 1541번 18:10~20:00
 *  식 한줄이 입력됨
 *  0. 주어진 식이 끝날때까지 반복
 *  1. 첫 숫자 저장.
 *  2. -가 나오기 전까지는 값들 다 저장. -가 나오면 남은 값들 다 더함 -> +도 -로 바꿔주면됨
 *   2-1. * -나오기전까지는 +이기 때문에 계속 값 더하기
 */ 
class Baeckjoon1541{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(bf.readLine()); 
        int res = 0; //결과
        int chk = 0; //첫번째 값인지 체크
        int cnt = 0; 
        
        int sum = 0; //-가 나오면 뒤에 숫자들은 다 더해서 -해주면됨 뒤에숫자들을 더해주기 위한 변수
        int minus = 1;
        String stnum = "";
        char s;
       
        String st = bf.readLine();
        
        while(st.length()>cnt) {      // -가 나오면 sum해서 더한값을 뺀다.   55-50+40-30+20 
        	s = st.charAt(cnt);
        	if(!(s=='-'||s=='+')) { //기호가 아니면 숫자로 만들기 위해 문자를 붙여준다.
        		stnum+=s;
        	} else {
        		sum += Integer.parseInt(stnum); //기호가 되면 stnum에서 붙여진 문자들을 숫자로 변환해줌.
        		if(chk==0) {  //첫번째값은 무조건 +로 넣고
        			res +=  sum;
        			sum=0;
        		}
        		if (minus==1) { //-기호가 나오기전까지는 다 더함.
        			res += sum;
        			sum=0;
        		}
        		if(s=='-') { //-가 있으면 체크, -뒤에 +가 오는것들은 전부 -취급해도 되기 때문.
            		minus = -1;
            	}
        		chk=1; 
        		stnum="";
        	}
        	cnt++;
        }
        
        sum += Integer.parseInt(stnum);
        res += sum*minus;
        System.out.println(res);
    }
    //55-50+40-30 -가 나오면 -1곱하고 +가 나오면 그냥 1곱하고,   55-50-40-30 이거야함.
}