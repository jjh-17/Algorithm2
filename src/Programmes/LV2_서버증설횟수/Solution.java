package Programmes.LV2_서버증설횟수;

public class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] mpph = new int[24]; // 시간대별 수용가능한 최대 플레이어 수

        // 시간대별 수용 가능한 최대 플레이서 수를 m-1로 초기화
        for (int i = 0; i < 24; i++) mpph[i] = m - 1;

        // 23시 까지 반복
        for (int i = 0; i < 24; i++) {
            // 현재 서버로 모두 수용 가능하면 넘어감
            if (players[i] <= mpph[i]) continue;

            // 각 시간대별 수용 가능한 최대 플레이어 수 증가
            int newServerCnt = players[i] / m - mpph[i] / m;
            for (int j = i; j < Integer.min(24, i + k); j++) mpph[j] += m * newServerCnt;
            answer += newServerCnt;
        }

        return answer;
    }
}