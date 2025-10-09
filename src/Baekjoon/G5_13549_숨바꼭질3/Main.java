package Baekjoon.G5_13549_숨바꼭질3;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {

    private static final int LIMIT = 100_000;
    private static int N, K;
    private static final int[] time = new int[LIMIT + 1];
    private static final PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int vertex;
        int time;

        public Node(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.time, n.time);
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

        System.out.println(time[K]);
        br.close();
    }

    private static void algorithm() {
        IntStream.range(0, LIMIT + 1).forEach(i -> time[i] = Integer.MAX_VALUE);
        time[N] = 0;
        pq.offer(new Node(N, time[N]));

        while (!pq.isEmpty()) {
            // 미방문 정점 중 가장 짧은 시간 이동한 정점을 경유지로 선택.
            Node cur = pq.poll();
            int minVertex = cur.vertex;
            int minTime = cur.time;

            // K 도착 시 종료
            if (minVertex == K) return;

            // 이동
            // 1칸 뒤
            if (0 <= minVertex - 1 && time[minVertex - 1] > minTime + 1) {
                time[minVertex - 1] = minTime + 1;
                pq.offer(new Node(minVertex - 1, minTime + 1));
            }

            // 1칸 앞
            if (minVertex + 1 <= LIMIT && time[minVertex + 1] > minTime + 1) {
                time[minVertex + 1] = minTime + 1;
                pq.offer(new Node(minVertex + 1, minTime + 1));
            }

            // 순간이동
            if (minVertex * 2 <= LIMIT && time[minVertex * 2] > minTime) {
                time[minVertex * 2] = minTime;
                pq.offer(new Node(minVertex * 2, minTime));
            }
        }
    }

}
