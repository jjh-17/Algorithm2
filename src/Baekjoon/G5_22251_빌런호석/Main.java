package Baekjoon.G5_22251_빌런호석;

import java.util.*;
import java.io.*;

public class Main {

    private static int N, K, P, X;
    private static int answer = 0;
    final static int[][] LED_REVERSE_NEEDED = new int[10][10];

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 층
        K = Integer.parseInt(st.nextToken()); // 디스플레이에 나타나는 자릿수
        P = Integer.parseInt(st.nextToken()); // LED 반전 가능 최대 횟수
        X = Integer.parseInt(st.nextToken()); // 엘리베이터 실제 층

        // solution
        solution();

        // output
        System.out.println(answer);
        br.close();
    }

    private static void solution() {
        // 각 숫자별 각 자리의 LED 켜짐 여부
        final boolean[][] NUMBERS = {
                {true, true, true, false, true, true, true},
                {false, false, true, false, false, true, false},
                {true, false, true, true, true, false, true},
                {true, false, true, true, false, true, true},
                {false, true, true, true, false, true, false},
                {true, true, false, true, false, true, true},
                {true, true, false, true, true, true, true},
                {true, false, true, false, false, true, false},
                {true, true, true, true, true, true, true},
                {true, true, true, true, false, true, true},
        };

        // 다른 숫자로 변경할 때 반전시켜야 하는 LED 수
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                int diff = 0;
                for (int k = 0; k < 7; k++) {
                    if (NUMBERS[i][k] != NUMBERS[j][k]) ++diff;
                }
                LED_REVERSE_NEEDED[i][j] = diff;
                LED_REVERSE_NEEDED[j][i] = diff;
            }
        }

        // 앞에 0을 포함하여 문자열로 나타내기
        dfs(0, 0, X);
    }

    // 현재 옮긴 횟수: 변경 횟수, 현재 자릿수, 현재 층
    private static void dfs(int cnt, int idx, int floor) {
        // 자릿수가 K를 넘어가면 종료 여부 확인
        if (idx == K + 1) {
            if (1 <= cnt && 1 <= floor && floor <= N) ++answer;
            return;
        }

        // idx 자릿수의 숫자
        int idxNum = (floor % (int) Math.pow(10, idx + 1)) / (int) Math.pow(10, idx);

        // idx 자리를 0 ~ 9로 변경
        for (int i = 0; i < 10; i++) {
            if (cnt + LED_REVERSE_NEEDED[idxNum][i] <= P) {
                int newFloor = floor + (i - idxNum) * (int) Math.pow(10, idx);
                dfs(cnt + LED_REVERSE_NEEDED[idxNum][i], idx + 1, newFloor);
            }
        }
    }

}
