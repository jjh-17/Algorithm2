package Baekjoon.G5_1041_주사위;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[] dice = new long[6];

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) dice[i] = Integer.parseInt(st.nextToken());

        // solution
        long answer = solution();

        // output
        br.close();
        System.out.println(answer);
    }

    public static long solution() {
        long[] pair2 = {
                dice[0] + dice[1], dice[0] + dice[2], dice[0] + dice[3], dice[0] + dice[4],
                dice[5] + dice[1], dice[5] + dice[2], dice[5] + dice[3], dice[5] + dice[4],
                dice[1] + dice[2], dice[2] + dice[4], dice[4] + dice[3], dice[3] + dice[1],
        };
        long[] pair3 = {
                dice[0] + dice[1] + dice[2], dice[0] + dice[1] + dice[3], dice[0] + dice[2] + dice[4], dice[0] + dice[3] + dice[4],
                dice[5] + dice[1] + dice[2], dice[5] + dice[2] + dice[4], dice[5] + dice[4] + dice[3], dice[5] + dice[3] + dice[1],
        };

        long min;
        switch (N) {
            case 1:
                min = Arrays.stream(dice).sum() - Arrays.stream(dice).max().getAsLong();
                break;
            case 2:
                min = 4L * Arrays.stream(pair2).min().getAsLong() + 4L * Arrays.stream(pair3).min().getAsLong();
                break;
            default:
                min = (5L * (N - 2) * (N - 2) + 4L * (N - 2)) * (Arrays.stream(dice).min().getAsLong()) +
                        (4L * (N - 1) + 4L * (N - 2)) * (Arrays.stream(pair2).min().getAsLong()) +
                        4L * Arrays.stream(pair3).min().getAsLong();
        }
        return min;
    }

}
