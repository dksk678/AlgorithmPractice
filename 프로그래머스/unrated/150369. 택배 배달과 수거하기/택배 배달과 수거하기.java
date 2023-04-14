import java.util.ArrayList;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0; // 거리
		
		ArrayList<int[]> dv = new ArrayList<>();
		ArrayList<int[]> pick = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			if(deliveries[i]==0) continue;
			dv.add(new int[] {i+1, deliveries[i]});
		}
		for (int i = 0; i < n; i++) {
			if(pickups[i]==0) continue;
			pick.add(new int[] {i+1, pickups[i]});
		}
		
		while(true) {
			//check box
			
//			for (int[] i: dv) {
//				System.out.print(Arrays.toString(i)+" ");
//			}
//			System.out.println("");
//			
//			for (int[] i: pick) {
//				System.out.print(Arrays.toString(i)+" ");
//			}
//			System.out.println("");
			
			
			int dsize = dv.size()-1;
			int psize = pick.size()-1;
			
			if(dsize<0 && psize<0) break;
			
			int dlast = 0;
			if(dsize>=0) {
				dlast = dv.get(dsize)[0];
			}
			int plast = 0;
			if(psize>=0) {
				plast = pick.get(psize)[0];
			}
			
			int last = Math.max(dlast, plast); //현재 가장 먼 집
			
			int dc = cap; // deliveries 상자 개수
			for (int i = dsize; i >= 0; i--) {
				int[] dbox = dv.get(i);
				
				dv.remove(i);
				if((dbox[1]-dc)>=1) dv.add(new int[] {dbox[0], (dbox[1]-dc)});//박스가 남았으면 ㄷ시 넣기
				
				dc -= dbox[1]; //실을 수 있는 박스 개수 
				if(dc<=0) break;
			}
			
			int pc = cap; // pickups 상자 개수
			for (int i = psize; i >= 0; i--) {
				int[] pbox = pick.get(i);
				
				pick.remove(i);
				if((pbox[1]-pc)>=1) pick.add(new int[] {pbox[0], (pbox[1]-pc)});//박스가 남았으면 ㄷ시 넣기
				
				pc -= pbox[1]; //실을 수 있는 박스 개수 
				if(pc<=0) break;
			}
			
			answer += last*2;
			
//			
//			System.out.println("");
			
		}
		
		// System.out.println(answer);
        
        return answer;
	}
}