package Programmes.LV2_완전범죄;

import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        final int INF = 3 * 40;
        int size = info.length;

        // dp[i][j] -> i번째 물건까지 훔쳤을 때, B의 흔적이 j일 때의 A의 흔적
        int[][] dp = new int[size + 1][m];
        for (int i = 0; i <= size; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        // 물건을 순회
        for (int i = 1; i <= size; i++) {
            int a = info[i - 1][0];
            int b = info[i - 1][1];

            for (int j = 0; j < m; j++) {
                // 각기 a, b를 선택
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                if (j + b < m) dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
            }
        }

        // 모든 물건을 훔친 뒤, B의 흔적이 m 미만일 때의 A의 흔적 최솟값 구하기
        int min = INF;
        for (int j = 0; j < m; j++) min = Math.min(dp[size][j], min);

        return min >= n ? -1 : min;
    }
}
