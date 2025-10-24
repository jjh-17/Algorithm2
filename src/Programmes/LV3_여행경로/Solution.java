package Programmes.LV3_여행경로;

import java.util.*;

public class Solution {
    int length = 0;
    boolean[] v;
    List<String> list = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        length = tickets.length;
        v = new boolean[length];

        dfs("ICN", "ICN", tickets, 0);

        Collections.sort(list);
        return list.get(0).split(" ");
    }

    public void dfs(String start, String route, String[][] tickets, int count){
        if (count == length){
            list.add(route);
            return;
        }

        for (int i=0; i<length; i++){
            if(!v[i] && start.equals(tickets[i][0])){
                v[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, count + 1);
                v[i] = false;
            }
        }
    }
}
