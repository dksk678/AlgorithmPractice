import java.util.*;
import java.io.*;
/*
survey 만큼 반복문 실행
처음나온 단어가 비동의 쪽임.
8개의 유형마다 점수를 저장.
점수가 높은 유형 순서대로 같으면 사전순으로
*/

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int len = survey.length;
        
        int[] point = new int[4];
        String[] surv = {"RT", "CF", "JM", "AN"};
        
        for(int i=0; i<len; i++){
            char disagree = survey[i].charAt(0);
            if(disagree == 'R'){
                if(choices[i] < 4) {
                    point[0] -= 4-choices[i];
                } else {
                    point[0] += choices[i]-4;
                }
            } else if (disagree == 'T'){
                if(choices[i] < 4) {
                    point[0] += 4-choices[i];
                } else {
                    point[0] -= choices[i]-4;
                }
            } else if (disagree == 'C'){
                if(choices[i] < 4) {
                    point[1] -= 4-choices[i];
                } else {
                    point[1] += choices[i]-4;
                }
            } else if (disagree == 'F'){
                if(choices[i] < 4) {
                    point[1] += 4-choices[i];
                } else {
                    point[1] -= choices[i]-4;
                }
            } else if (disagree == 'J'){
                if(choices[i] < 4) {
                    point[2] -= 4-choices[i];
                } else {
                    point[2] += choices[i]-4;
                }
            } else if (disagree == 'M'){
                if(choices[i] < 4) {
                    point[2] += 4-choices[i];
                } else {
                    point[2] -= choices[i]-4;
                }
            } else if (disagree == 'A'){
                if(choices[i] < 4) {
                    point[3] -= 4-choices[i];
                } else {
                    point[3] += choices[i]-4;
                }
            } else if (disagree == 'N'){
                if(choices[i] < 4) {
                    point[3] += 4-choices[i];
                } else {
                    point[3] -= choices[i]-4;
                }
            }
        }
        
        for(int i=0; i<4; i++){
            if(point[i] <= 0){
                sb.append(surv[i].charAt(0));
            } else {
                sb.append(surv[i].charAt(1));
            }
        }
        
        return sb.toString();
    }
}