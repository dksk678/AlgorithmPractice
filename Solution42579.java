import java.util.*;
/*
HashMap1 = 장르 별 재생 수
HashMap2 = 모든 노래 고유 번호와 각 재생 수 
HashMap2를 재생 수로 sort
HashMap1을 sort 후 키값이 일치하는 노래를 HashMap2에서 찾기
*/
class Solution42579 {
    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        Map<String, Integer> genreCnt = new HashMap<>(); //장르별 재생 수를 저장할 맵
        Map<String, ArrayList<int[]>> songCnt = new HashMap<>(); //장르별 번호와 각 재생수를 저장할 맵
        
        int len = plays.length;
        
        for(int i=0; i<len; i++){ //장르별 총 재생 수 저장
            genreCnt.put(genres[i], genreCnt.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        ArrayList<int[]> list;
        for(int i=0; i<len; i++){ // 같은 장르의 번호와 재생 수 저장
            list = new ArrayList<>();
            if(songCnt.containsKey(genres[i])){ //같은 장르가 이미 있으면
                list = songCnt.get(genres[i]); //기존에 저장된 리스트에 추가
            } 
            list.add(new int[] {i, plays[i]});
            songCnt.put(genres[i], list);
        }
        
        //장르별 재생 수 정렬
        List<String> genreSorted = new ArrayList<>(genreCnt.keySet());
        genreSorted.sort((o1, o2) -> genreCnt.get(o2) - genreCnt.get(o1));
  
        //정렬된 장르 순서대로
        for(String str: genreSorted){
            list = new ArrayList<>();
            list = songCnt.get(str);
            
            Collections.sort(list, (o1, o2)->{ //같은 장르 중에서 재생 수 정렬
                return o2[1] - o1[1];
            });
            
            //최대 2개 출력
            int cnt = 0;
            for(int[] il: list){
                if(++cnt == 3) break;
                answer.add(il[0]);
            }
        }
        
        return answer;
    }
}
