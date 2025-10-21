package Programmes.LV2_택배상자;

import java.util.*;

class Solution {
    public int solution(int[] order) {
        final ArrayDeque<Integer> stack = new ArrayDeque<>(); // 보조 컨테이너 벨트

        int num = 1;
        for (int o=1;o<=order.length;o++) {
            if (num == order[num-1]) num++;
            else stack.offerLast(o);

            // 보조 컨테이너에서 트럭으로 적재
            while (!stack.isEmpty() && stack.peekLast() == order[num-1]) {
                stack.pollLast();
                num++;
            }

            if (num > order.length) break;
        }

        return num-1;
    }
}