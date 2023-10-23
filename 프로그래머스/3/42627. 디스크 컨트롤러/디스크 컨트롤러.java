import java.util.*;

/*
최대 n*2 미만임. 1~500 총합 즉 12만 -> remove를 사용하면 *2 25만?
1. 끝나는 시간이 가장 빠른 순으로 정렬
2. 시작시간이 가장 빠른 순으로 정렬

끝나는 시간이 가장 빠른 순으로 찾다가 시작 시간이 현재 시간이 되는 경우가 되면 다음 으로 넘김

*/
class Solution {
    
    public int solution(int[][] jobs) {
        int jlen = jobs.length;
        //1. 나는 시간이 가장 빠르게, 2. 같을 경우 시작시간이 가장 빠르게 
        Arrays.sort(jobs, ((o1, o2) -> {
            int ediff = o1[1] - o2[1];
            if(ediff == 0) {
                return o1[0] - o2[0];
            }
            return ediff;
        }));
        
        ArrayList<int[]> jobList = new ArrayList<>();
        int curTime = 0; //처음 시작하는 시간 저장
        
        for(int i=0; i<jlen; i++ ) {
            curTime = jobs[i][0] < curTime ? jobs[i][0] : curTime;
            jobList.add(new int[] {jobs[i][0], jobs[i][1]});
        }
        
        int totTime = 0;
        while(!jobList.isEmpty()) {
            int start = 1001;
            int snum = 0;
            
            for(int i=0; i<jobList.size(); i++) {
                if(curTime >= jobList.get(i)[0]) { //종료시간이 가장 빠른 것 중에 이미 시작시간인 경우 바로 break;
                    snum = i;
                    break;
                } else if(jobList.get(i)[0] < start){ //아니면 가장 빠른 시작시간 찾기
                    start = jobList.get(i)[0];
                    snum = i;
                }
            }
            
            int[] curJob = jobList.get(snum);
            if(curTime < curJob[0]) { //가장 빠른 시작시간이 현재시간보다 긴 경우
                curTime = curJob[0]; //현재시간을 가장 빠른시작시간으로 변경
            }
            
            curTime += curJob[1];
            totTime += curTime - curJob[0];
            
            jobList.remove(snum);
        }
        
        return totTime/jlen;
    }
}