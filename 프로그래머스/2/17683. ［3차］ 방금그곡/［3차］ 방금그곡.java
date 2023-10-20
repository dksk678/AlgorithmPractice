import java.util.*;

/*
제목, 시작끝난시간, 악보

조건 일치 음악은 재생시간이 가장 긴 음악, 같으면 먼저 입력된 음악
*/

class Solution {
    public String solution(String m, String[] musicinfos) {
        int ml = m.length();
        int mis = musicinfos.length;

        String[] time = new String[mis];
        String[] name = new String[mis];
        String[] mi = new String[mis];

        m = m.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");

        int answerDiff = -1;
        String answer = "(None)";

        for(int i=0; i<mis; i++) {
            String[] musicinfo = musicinfos[i].split(",");

            int diff = getTimeDiff(musicinfo[0], musicinfo[1]);


            musicinfo[3] = musicinfo[3].replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");

            int ml2 = musicinfo[3].length();

            StringBuilder sb = new StringBuilder();
            for(int j=0; j<diff; j++) {
                // System.out.println(j % (ml2));
                sb.append(musicinfo[3].charAt(j % (ml2)));
            }

            if(sb.toString().contains(m)) {
                if(answerDiff == -1 || answerDiff < diff) {
                    answerDiff = diff;
                    answer = musicinfo[2];
                }
            }
        }

        return answer;
    }

    public int getTimeDiff(String st, String et) {
        String[] stToSec = st.split(":");
        String[] etToSec = et.split(":");

        int sec = 0;
        sec += toInt(etToSec[0]) * 60;
        sec += toInt(etToSec[1]);

        sec -= toInt(stToSec[0]) * 60;
        sec -= toInt(stToSec[1]);

        return sec;
    }

    public int toInt(String str) {
        return Integer.parseInt(str);
    }
}