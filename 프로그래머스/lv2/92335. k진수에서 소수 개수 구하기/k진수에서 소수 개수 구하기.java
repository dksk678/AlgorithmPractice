import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = -1;
        char[] primeNum = Integer.toString(n, k).toCharArray();
        
        answer = getPrimeCount(primeNum);
        //System.out.println(primeNum);
        return answer;
    }
    
    public int getPrimeCount(char[] primeNum) {
        long num = 0;
        int count = 0;
        
        for(char c: primeNum) {
            if(c == '0') { //0이 되는 경우 앞에서 저장된 숫자가 소수인지
                if(isPrime(num)) {
                    count++;
                }
                num = 0;
            } else {
                num = num*10 + (c-'0');
            }
        }
        
        // 마지막 숫자
        if(isPrime(num)) {
            count++;
        }
        
        return count;
    }
    
    public boolean isPrime(long num) {
        
        //System.out.println(num);
        if(num < 2) return false;
        
        for(int i=2, s=((int)Math.sqrt(num)+1); i<s; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}