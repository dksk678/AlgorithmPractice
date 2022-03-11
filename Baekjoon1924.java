import java.io.*;
import java.util.*;

/*	
 */ 
class Baeckjoon1924{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] arr = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int M = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());;
        for(int i=0; i<M; i++) {
        	day+=arr[i]; 
        }
        System.out.println(week[day%7]);
        

//        System.out.println(sb);
    }

}