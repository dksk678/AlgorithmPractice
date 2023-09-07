import java.util.*;
/**
    조건
    1. 플러스 가입자를 최대한 (가장 중요)
    2. 판매액 최대로
    
    일정 비율이상 할인하는 이모티콘 모두 구매
    구매비용 합이 일정이상 된다면 플러스로 가입
    
    emoticons 마다 각 다른 할인율을 적용 -> 순서o,중복o = 순열
    
    Logic
    1. 순열로 이모티콘 할인율 저장
    2. 할인율에 따른 가입여부와 판매금액 구하기
    3. answer와 비교
*/

class Solution {
    public static int[] discounts;
    public static int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        //setting 
        int emoticonsLen = emoticons.length;
        discounts = new int[emoticonsLen];
        answer = new int[2];
        
        setDiscountsForPerm(0, emoticonsLen, users, emoticons);
        
        return answer;
    }
    
    public void setDiscountsForPerm(int dep, int emoticonsLen, int[][] users, int[] emoticons) {
        
        if(dep == emoticonsLen) {
            // 1. calculatePlusAndPrice
            int[] curPlusAndPrice = calculatePlusAndPrice(emoticonsLen, users, emoticons);
            
            //System.out.println(Arrays.toString(discounts) + ""+ Arrays.toString(curPlusAndPrice));
            // 2. compareCurrentToResult
            if(compareCurrentToResult(curPlusAndPrice)) {
                answer = curPlusAndPrice.clone();
            }
            
            return;
        }
        
        for(int i=10; i<=40; i+=10) { //할인율 10~40
            discounts[dep] = i;
            setDiscountsForPerm(dep+1, emoticonsLen, users, emoticons);
            // discounts[dep] = 0; // 생략 가능
        }
        
    }
    
    public int[] calculatePlusAndPrice(int emoticonsLen, int[][] users, int[] emoticons) {
        int totPrice = 0;
        int totPlus = 0;
        
        for(int[] user: users) {
            int rate = user[0];
            int userPrice = user[1];
            int sumPrice = 0;
            
            for(int i=0; i<emoticonsLen; i++) {
                if(rate <= discounts[i]){
                    
                    sumPrice += calculateDiscount(emoticons[i], discounts[i]);
                }
            }
            
            //총 구매비용이 플러스가격이상이면 plus로 추가
            if(userPrice <= sumPrice) {
                totPlus++;
            } else {
                totPrice += sumPrice;
            }
        }
        
        return new int[] {totPlus, totPrice};
    }
    
    //할인 금액 계산
    public int calculateDiscount(int originPrice, int discountRate) {
        return originPrice - (int)(originPrice * (discountRate/100.0));
    }
    
    //현재 가입자와 판매액을 Result와 비교
    public boolean compareCurrentToResult(int[] curPlusAndPrice) {
        if(answer[0] < curPlusAndPrice[0]) { // isCurPlusGreaterThanAnswer
            return true;
        } else if (answer[0] == curPlusAndPrice[0] 
                   && answer[1] < curPlusAndPrice[1]) { // isCurPriceGreaterThanAnswer
            return true;
        }
        
        return false;
    }
    
}