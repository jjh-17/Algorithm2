package Baekjoon.G5_5972_택배배송;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static Node[] G;
    private static int[] dist;

    static class Node {
        int vertex;
        int weight;
        Node link;

        public Node(int vertex, int weight, Node link) {
            this.vertex = vertex;
            this.weight = weight;
            this.link = link;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = new Node[N + 1];
        dist = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            G[A] = new Node(B, C, G[A]);
            G[B] = new Node(A, C, G[B]);
        }

        // algorithm
        solution();

        // output
        System.out.println(dist[N]);
        br.close();
    }

    private static void solution() {
        final boolean[] v = new boolean[N + 1];
        final PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int cnt = 0;

        for (int i = 1; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;

        pq.offer(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int minWeight = cur[1];

            if (v[minVertex]) continue;
            v[minVertex] = true;

            if (cnt++ == N - 1) break;

            for (Node node = G[minVertex]; node != null; node = node.link) {
                if (!v[node.vertex] && dist[node.vertex] > (minWeight + node.weight)) {
                    dist[node.vertex] = minWeight + node.weight;
                    pq.offer(new int[]{node.vertex, dist[node.vertex]});
                }
            }
        }
    }
}
