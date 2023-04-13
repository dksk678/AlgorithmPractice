import java.util.*;
/*
진행 중 과제
잠시 멈춘 과제는 Stack


*/

class Solution {
    class Plan {
        String name;
        int time, ptime;
        
        public Plan(String name, int time, int ptime) {
            this.name = name;
            this.time = time;
            this.ptime = ptime;
        }
        
        @Override
		public String toString() {
			return "Plan [n=" + name + ", t=" + time + ", p=" + ptime + "]";
		}
    }
    
    public ArrayList<String> solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        
        Stack<Plan> stack = new Stack<>();
        
        int min = 999999;
        int max = 0;
        
        // Plan curp = new Plan(plan[0], getIntTime[1], Integer.parseInt(plan[2]));
        PriorityQueue<Plan> pQ = new PriorityQueue<>((o1, o2) -> {
            return o1.time - o2.time;
        });
        
        for(String[] plan : plans) {
            pQ.offer(new Plan(plan[0], getIntTime(plan[1]), Integer.parseInt(plan[2])));
        }
        
        int curtime = 0;
        while(!pQ.isEmpty()) {
            
            Plan cur = pQ.peek();
            
            if(!stack.isEmpty()) { // stack에 있으면
                Plan splan = stack.peek();
                
                if(cur.time > curtime) { //현재 과제 시간과 다음 과제 시간
                    pQ.add(new Plan(splan.name, curtime, splan.ptime)); //멈춘 과제 시간 먼저       
                    stack.pop();
                    continue;
                }
            } 
            
            pQ.poll();
            Plan next = cur;
            if(!pQ.isEmpty()) {
                next = pQ.peek();
            }
            
            curtime = cur.time + cur.ptime;
            
            if(curtime <= next.time) { //걸린 시간이 다음 시간보다 적으면 과제 완료
                answer.add(cur.name);
            } else {
                curtime = next.time;
                stack.add(new Plan(cur.name, cur.time, cur.ptime - (next.time-cur.time)));
                // System.out.println("s " +stack.peek());
            }
        }
        
        while(!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }
        
        return answer;
    }
    
    private int getIntTime(String strTime) {
        return Integer.parseInt(strTime.substring(0, 2)) * 60 + Integer.parseInt(strTime.substring(3, 5));
    }
}