package Baekjoon.G4_13913_숨바꼭질4;

import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();
    private static final int LIMIT = 100_000;
    private static final int[] time = new int[LIMIT + 1];
    private static final int[] parent = new int[LIMIT + 1];
    private static int N, K;

    private static class Node {
        private final int vertex;
        private final int time;

        public Node(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        algorithm();

        System.out.println(sb.toString().trim());
        br.close();
    }

    private static void algorithm() {
        final ArrayDeque<Node> q = new ArrayDeque<>();

        IntStream.range(0, LIMIT + 1).forEach(i -> time[i] = Integer.MAX_VALUE);
        time[N] = 0;
        q.offerLast(new Node(N, 0));

        while (!q.isEmpty()) {
            // 탈출 여부 확인
            Node cur = q.pollFirst();
            if (cur.vertex == K) {
                sb.append(cur.time).append("\n");

                int p = K;
                List<Integer> list = new ArrayList<>();
                list.add(p);
                while (p != N) {
                    p = parent[p];
                    list.add(p);
                }
                Collections.reverse(list);
                sb.append(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
                return;
            }

            // 이동
            // 1칸 뒤
            if (0 <= cur.vertex - 1 && time[cur.vertex - 1] > cur.time + 1) {
                parent[cur.vertex - 1] = cur.vertex;
                time[cur.vertex - 1] = cur.time + 1;
                q.offerLast(new Node(cur.vertex - 1, cur.time + 1));
            }

            // 1칸 앞
            if (cur.vertex + 1 <= LIMIT && time[cur.vertex + 1] > cur.time + 1) {
                parent[cur.vertex + 1] = cur.vertex;
                time[cur.vertex + 1] = cur.time + 1;
                q.offerLast(new Node(cur.vertex + 1, cur.time + 1));
            }

            // 순간이동
            if (cur.vertex * 2 <= LIMIT && time[cur.vertex * 2] > cur.time + 1) {
                parent[cur.vertex * 2] = cur.vertex;
                time[cur.vertex * 2] = cur.time + 1;
                q.offerLast(new Node(cur.vertex * 2, cur.time + 1));
            }
        }
    }

}
