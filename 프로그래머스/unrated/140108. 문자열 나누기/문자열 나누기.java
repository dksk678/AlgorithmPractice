class Solution {
    public int solution(String s) {
        int answer = 0;
        int[] xlist = new int[2]; //x는 0, 나머지는 1
        
        char[] charlist = s.toCharArray();
        char x = ' ';
        
        for(int i=0, size=charlist.length; i<size; i++){
            if(xlist[0]==0){ //x 리셋
                x = charlist[i];
            }
            
            if(charlist[i] == x) { //x인지 아닌지
                xlist[0]++;
            } else {
                xlist[1]++;
            }
            
            if(xlist[0] == xlist[1]) { //x와 같은 개수가되면 ans++ 하고 기존 개수 초기화
                xlist[0] = 0;
                xlist[1] = 0;
                answer++;
            }
        }
        
        if(xlist[0]+xlist[1]>0) answer++; //마지막 남아있는 경우
        
        return answer;
    }
}