package Baekjoon.G5_2493_탑;

import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] heights;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        heights = new int[N + 1];
        for (int i = 1; i <= N; i++) heights[i] = Integer.parseInt(st.nextToken());
        heights[0] = Integer.MAX_VALUE;

        // solution
        solution();

        // output
        System.out.println(sb.toString().trim());
        br.close();
    }

    public static void solution() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.offerLast(0);
        for (int i = 1; i < heights.length; i++) {
            if (heights[i - 1] < heights[i]) {
                while (true) {
                    if (heights[q.peekLast()] > heights[i]) {
                        sb.append(q.peekLast()).append(" ");
                        q.offerLast(i);
                        break;
                    }
                    q.pollLast();
                }
            } else {
                q.offerLast(i);
                sb.append(i - 1).append(" ");
            }
        }
    }

}
