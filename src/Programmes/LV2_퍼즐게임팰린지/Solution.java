package Programmes.LV2_퍼즐게임팰린지;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int levelL = 1;
        int levelR = 0;
        final int L = diffs.length;

        // 이진 탐색을 위한 숙련도 L, R 구하기
        for (int d : diffs) {
            levelR = Integer.max(levelR, d);
        }

        // 최소 레벨 구하기 루프 시작
        while (levelL < levelR) {
            int levelM = (levelL + levelR) / 2;
            long time = 0L; // 문제를 푼 누적 시간
            boolean complete = true;

            // 첫 문제
            time += (Integer.max(diffs[0] - levelM, 0) + 1L) * times[0];
            if (time > limit) {
                levelL = levelM + 1;
                continue;
            }

            // 두 번째 이후 문제
            for (int i = 1; i < L; i++) {
                time += (long) Integer.max(diffs[i] - levelM, 0) * (times[i - 1] + times[i]) + times[i];
                if (time > limit) {
                    complete = false;
                    break;
                }
            }

            if (complete) {
                levelR = levelM;
            } else {
                levelL = levelM + 1;
            }
        }

        return levelL;
    }
}
