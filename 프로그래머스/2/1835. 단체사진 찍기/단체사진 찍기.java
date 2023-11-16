import java.util.*;

/*
    n은 data의 수
    data는 조건
    
    백트래킹으로 조건에 맞는 횟수만 구하기
    
    1. data를 char 형태로 분해
    2. 백트래킹을 하면서 분해된 data의 조건을 체크
    2-1. 불가능하면 다음 글자로 비교
    2-2. 가능하면 추가 탐색
    
    !!! -> 간격이 1보다 크고 싶다는 것은 둘의 차이가 2초과여야 함 7-5는 차이가 1이라 안됨
*/

class Solution {
    private int count;
    private char[] kfChar = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private Map<Character, Integer> map = new HashMap<>();
    
    public int solution(int n, String[] data) {
        int answer = 0;
        
        char[][] dataList = new char[n][5];
        
        for(int i=0; i<n; i++) {
            dataList[i] = data[i].toCharArray();
        }
        
        count = 0;
        
        for(int i=0; i<8; i++) {
            map = new HashMap<>();
            
            boolean[] v = new boolean[8]; //방문 처리
            v[i] = true;
            
            map.put(kfChar[i], 0);
            
            getPhotoCount(1, n, dataList, v); //depth, n, l, visited
        }
        
        return count;
    }
    
    public void getPhotoCount(int d, int n, char[][] dataList, boolean[] v) {
        if(d == 8) { //end
            count++;
            
            return;
        }
        
        for(int i=0; i<8; i++) { //총 8개의 캐릭터
            if(v[i]) continue;
            
            if(!canCapturePhoto(i, d, n, dataList)) continue; //사진을 찍을 수 있는지 확인
            
            map.put(kfChar[i], d);
            v[i] = true;
            
            getPhotoCount(d+1, n, dataList, v);
            
            v[i] = false;
            map.remove(kfChar[i]);
            
        }
    }
    
    //주어진 data에서 현재 값이 조건 중에 하나로 포함 되어 있으면 조건 확인하기
    public boolean canCapturePhoto(int idx, int d, int n, char[][] dataList) {
        char cur = kfChar[idx];
        
        for(int i=0; i<n; i++) {
            char[] condition = dataList[i];
            
            if(condition[0] == cur) {
                if(!isOk(d, 2, condition)) return false;
            } else if (condition[2] == cur) {
                if(!isOk(d, 0, condition)) return false;
            }
        }
        
        return true;
    }
    
    //조건이 일치하는 지 확인
    public boolean isOk(int d, int comp, char[] condition) {
        if(map.get(condition[comp]) == null) return true; //이전에 배치한 적이 없는 경우
        
        int compPos = map.get(condition[comp]);
        int num = condition[4]-'0';
        int diff = d - compPos - 1;
        
        if(condition[3] == '=') {
            if(diff == num) { //차이가 조건에 맞는 위치면
                return true;
            }
        } else if (condition[3] == '>') {
            if(diff > num) { //차이가 조건에 맞는 위치면 num이 2면 
                return true;
            }
        } else {
            if(diff < num) { //차이가 조건에 맞는 위치면
                return true;
            }
        }
        
        
        return false;
    }
}