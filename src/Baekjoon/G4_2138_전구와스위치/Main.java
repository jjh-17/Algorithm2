package Baekjoon.G4_2138_전구와스위치;

import java.util.*;
import java.io.*;

public class Main {

    private static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        N = Integer.parseInt(br.readLine());
        final String GIVEN = br.readLine();
        final String TARGET = br.readLine();

        // 1번째 버튼을 누르지 않은 경우
        int answer = solution(0, new StringBuilder(GIVEN), TARGET);

        // 1번째 버튼을 누른 경우
        if (answer == -1) {
            StringBuilder changed = new StringBuilder(GIVEN);
            changed.setCharAt(0, GIVEN.charAt(0) == '0' ? '1' : '0');
            changed.setCharAt(1, GIVEN.charAt(1) == '0' ? '1' : '0');
            answer = solution(1, changed, TARGET);
        }

        // output
        System.out.println(answer);
        br.close();
    }

    public static int solution(int cnt, StringBuilder given, String TARGET) {
        for (int i = 0; i < N - 2; i++) {
            char g = given.charAt(i);
            if (g != TARGET.charAt(i)) {
                ++cnt;
                for (int j = 0; j < 3; j++) given.setCharAt(i + j, given.charAt(i + j) == '0' ? '1' : '0');
            }
        }

        if (given.charAt(N - 2) != TARGET.charAt(N - 2) && given.charAt(N - 1) != TARGET.charAt(N - 1)) {
            ++cnt;
            given.setCharAt(N - 2, given.charAt(N - 2) == '0' ? '1' : '0');
            given.setCharAt(N - 1, given.charAt(N - 1) == '0' ? '1' : '0');
        }

        return given.toString().equals(TARGET) ? cnt : -1;
    }

}
