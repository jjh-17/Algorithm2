package Baekjoon.G5_15989_123더하기4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(Files.newInputStream(Path.of("src/Baekjoon/G5_15989_123더하기4/input.txt")));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N + 1][4]; // dp[a][b] = a까지의 합 방법 중 b 이하의 수만 이용하면서 마지막에 b로 끝나는 경우의 수

            if (N >= 1) {
                dp[1][1] = 1;
                dp[1][2] = 0;
                dp[1][3] = 0;
            }
            if (N >= 2) {
                dp[2][1] = 1;
                dp[2][2] = 1;
                dp[2][3] = 0;
            }
            if (N >= 3) {
                dp[3][1] = 1;
                dp[3][2] = 1;
                dp[3][3] = 1;
            }
            for (int n = 4; n <= N; n++) {
                dp[n][1] = dp[n - 1][1];
                dp[n][2] = dp[n - 2][1] + dp[n - 2][2];
                dp[n][3] = dp[n - 3][1] + dp[n - 3][2] + dp[n - 3][3];
            }
            sb.append(dp[N][1] + dp[N][2] + dp[N][3]).append("\n");
        }

        // 출력
        System.out.println(sb.toString());
        br.close();
    }

}
