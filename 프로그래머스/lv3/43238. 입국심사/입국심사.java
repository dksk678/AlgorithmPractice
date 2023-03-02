import java.util.*;
/*
가장 앞에 있는 사람은 비어있는 심사대 갈 수 있음.
하지만, 기다렸다가 더 먼저 끝나는 심사대가 있으면 기다렸다가 가능



*/
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = 0;
        long right = (long) n * times[times.length - 1]; //가장 최악의 경우의(오래걸리는) 시간
        
        while (left <= right) {
            long mid = (left + right) / 2; //최고와 최악의 시간의 절반 구하기.
            long sum = 0; // 총 심사한 인원
            
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i]; //평균 시간으로 구했을 때 최대 입국 심사 가능한 인원 수
            }
            
            // System.out.println(mid+" "+sum);
            
            if (sum < n) { //입국심사 가능한 인원수가 n보다 작으면 더 시간을 늘려야 함
                left = mid + 1;
            } else { //입국심사 가능한 인원수가 n보다 크면 시간을 줄여야 함.
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}