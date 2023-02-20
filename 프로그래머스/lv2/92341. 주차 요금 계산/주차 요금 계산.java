import java.util.*;
import java.io.*;

/*
입차된 후에 출차된 내역 없으면. 23:59
누적 시간이 기본시간 이하라면 기본 요금
기본 시간 초과하면 기본 요금 + 단위 시간마다 단위 요금
    - 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면 올림.

### 차량 번호가 작은 자동차 부터 청구할 요금을 RETURN    

fees[0] = 기본 시간, fees[1] = 기본 요금, fees[2] = 단위 시간, fees[3] = 단위 요금
*/

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        Map<String, Integer> totalTime = new HashMap<>();
        Map<String, String> inCar = new HashMap<>();
        
        StringTokenizer st;
        
        String time = "";
        String carNumber = "";
        String recordType = "";
        
        for(String str: records){
            st = new StringTokenizer(str);
            time = st.nextToken();
            carNumber = st.nextToken();
            recordType = st.nextToken();
            
            if(recordType.equals("IN")) {
                inCar.put(carNumber, time);
            } else {
                String inTimeString = inCar.get(carNumber);
                inCar.remove(carNumber);
                //inTime 과 outTime 시간을 구하기 
                int inTimeInt = getIntTime(inTimeString);
                int outTime = getIntTime(time);
                
                totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + (outTime - inTimeInt));
            }
        }
        
        //출차 하지 않은 차량 추가.
        for(String num : inCar.keySet()){
            int inTimeInt = getIntTime("23:59");
            int outTime = getIntTime(inCar.get(num));
            
            totalTime.put(num, totalTime.getOrDefault(num, 0) + (inTimeInt - outTime));
        }
        
        //차량 번호 순 정렬
        List<String> list = new ArrayList<>(totalTime.keySet());
        Collections.sort(list);
        
        List<Integer> result2 = new ArrayList<>();
        
        // 정렬된 번호 순으로 최종 요금 구하기
        for(String num : list) {
            result2.add(getMoney(fees, totalTime.get(num)));
        }
        
        return result2.stream().mapToInt(i -> i).toArray();
    }
    
    //시간을 Int 형으로 
    private int getIntTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        
        return Integer.parseInt(st.nextToken())*60 + Integer.parseInt(st.nextToken());
    }
    
    //최종 금액 계산
    private int getMoney(int[] fees, int totalTime){    
        int feeMoney = fees[1];
        
        if(totalTime <= fees[0]) {
            return feeMoney;
        }
        
        feeMoney += Math.ceil((totalTime-fees[0]+0.0)/fees[2]) * fees[3];
        
        return feeMoney;
    }
}