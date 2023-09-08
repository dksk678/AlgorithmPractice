import java.util.*;

class Solution {
    private static int MONTH_DAY = 28;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int privaciesLen = privacies.length;
        int termsLen = terms.length;
        
        Map<String, Integer> termsMap = new HashMap<>();
        String[][] splitPrivacies = new String[privaciesLen][4];
        
        //유효기간 처리
        for(int i=0; i<termsLen; i++) {
            String[] tempTerms = terms[i].split(" ");
            termsMap.put(tempTerms[0], Integer.parseInt(tempTerms[1]));
        }
    
        //문자열 쪼개기
        for(int i=0; i<privaciesLen; i++) {
            splitPrivacies[i] = privacies[i].split(" |\\.");
        }
        
        //Today 총 일 수 구하기
        int[] splitToday = Arrays.stream(today.split("\\."))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        
        int intToday = (splitToday[0]*MONTH_DAY*12) + splitToday[1]*MONTH_DAY + splitToday[2];
        
        //terms와 비교
        for(int i=0; i<privaciesLen; i++) {
            String[] privacy = splitPrivacies[i];
            int term = termsMap.get(privacy[3])*MONTH_DAY - 1;
            
            int month = Integer.parseInt(privacy[1])*MONTH_DAY;
            
            int year = Integer.parseInt(privacy[0])*MONTH_DAY*12;
            
            int day = Integer.parseInt(privacy[2]);
            
            //총 일수
            int totalDays = year+month+day+term;
            
            // System.out.println(totalDays+" "+intToday);
            
            //today와 비교
            if(totalDays < intToday) {
                answer.add(i+1);
            }
        }
        
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}