package Programmes.LV3_보석쇼핑;

import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] gems) {
        int kind = new HashSet<>(Arrays.asList(gems)).size();
        int[] answer = new int[2];
        int minLength = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();

        int start = 0;
        for (int end = 0; end < gems.length; end++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            while (map.getOrDefault(gems[start], 0) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }

            if (map.size() == kind && minLength > end - start) {
                minLength = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }

        return answer;
    }
}