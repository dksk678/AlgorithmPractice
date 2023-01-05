/*
최대 절반만큼의 크기가 가능.
제일 처음 문자열 부터 n개의 단위로 잘라야 하기 때문에.

*/
class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;
        
        StringBuilder tempString = new StringBuilder(); // n개단위의 문자열을 저장할 변수
        StringBuilder compressedString = new StringBuilder(); // 압축된 문자열 저장될 변수
        
        int half = len/2;
        
        for(int i=1; i<=half; i++){
            compressedString.setLength(0);
            tempString.setLength(0);
            tempString.append(s.substring(0, i)); //초기값 넣어주기
            
            int cnt = 1;
            
            for(int j=i+i; j<=len; j+=i){
                if(tempString.toString().equals(s.substring(j-i, j))) {//다음 문자열도 같으면 압축횟수 +1
                    cnt++;
                } else {
                    if(cnt > 1){ //압축 횟수가 2개가 넘으면 압축문자열에 횟수도 저장
                        compressedString.append(cnt);
                    }
                    compressedString.append(tempString.toString());
                        
                    tempString.setLength(0); //압축할 문자열과 다음 문자열이 다르므로, 압축할 문자열 바꿔주기.
                    tempString.append(s.substring(j-i, j));
                    
                    cnt = 1;
                }
                
                if(j+i > len){ //마지막에 남는 문자열 붙여주기
                    if(cnt > 1){
                        compressedString.append(cnt);
                    }
                    compressedString.append(tempString.toString()).append(s.substring(j, len));
                }
            }
            
            int cslen = compressedString.length();
            answer = answer > cslen ? cslen : answer;
        }
        
        return answer;
    }
}