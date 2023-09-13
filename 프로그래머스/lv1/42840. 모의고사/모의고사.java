import java.util.*;

class Solution {
    private static final int[][] student = {{1, 2, 3, 4, 5},
                                          {2, 1, 2, 3, 2, 4, 2, 5},
                                          {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    
    public int[] solution(int[] answers) {
        int[] answerCounts = new int[3];
        int answersLen = answers.length;
        int len1 = student[0].length;
        int len2 = student[1].length;
        int len3 = student[2].length;
        
        //getAnswerCounts
        for(int i=0; i<answersLen; i++) {
            if(isAnswer(0, i, len1, answers[i])) { //for으로 더하는 로직으로 메소드화 해도됨
                answerCounts[0]++;
            }
            if(isAnswer(1, i, len2, answers[i])) {
                answerCounts[1]++;
            }
            if(isAnswer(2, i, len3, answers[i])) {
                answerCounts[2]++;
            }
        }
        
        int max = answerCounts[0];
        if(max < answerCounts[1]) {
            max = answerCounts[1];
        }
        if (max < answerCounts[2]) {
            max = answerCounts[2];
        }
        
        System.out.println(max);
        
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(answerCounts[i] == max) {
                answer.add(i+1);
            }
        }
        
        
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    public boolean isAnswer(int curStu, int curIdx, int len, int answer) {
        return student[curStu][curIdx%len] == answer;
    }
}