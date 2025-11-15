package Programmes.LV2_3N타일링;

class Solution {
    public int solution(int n) {
        // n이 홀수면 채울 방법이 없음
        if (n % 2 == 1) return 0;
        if (n == 2) return 3;

        // n이 짝수인 경우만 고려
        final int MOD = 1_000_000_007;

        long[] dp = new long[n + 1];
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            /**
             * f2 = 3
             * f4 = f2 * f2 + 2 = 11
             * f6 = f4 * f2 + f2 * 2 + 2
             * f8 = f6 * f2 + f4 * 2 + f2 * 2 + 2
             * 모듈러 법칙 적용
             */
            dp[i] += (dp[i - 2] * dp[2]) % MOD;
            for (int j = i - 4; j >= 2; j -= 2) {
                dp[i] = (dp[i] + (2 * dp[j]) % MOD) % MOD;
            }
            dp[i] = (dp[i] + 2) % MOD;
        }

        return (int) dp[n];
    }
}
