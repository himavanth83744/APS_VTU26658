import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            red[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blue[e[0]].add(e[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<>();

        // node, color (0 = red, 1 = blue), distance
        queue.offer(new int[]{0, 0, 0});
        queue.offer(new int[]{0, 1, 0});
        visited[0][0] = true;
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int color = curr[1];
            int dist = curr[2];

            if (answer[node] == -1) {
                answer[node] = dist;
            }

            if (color == 0) {
                for (int next : blue[node]) {
                    if (!visited[next][1]) {
                        visited[next][1] = true;
                        queue.offer(new int[]{next, 1, dist + 1});
                    }
                }
            } else {
                for (int next : red[node]) {
                    if (!visited[next][0]) {
                        visited[next][0] = true;
                        queue.offer(new int[]{next, 0, dist + 1});
                    }
                }
            }
        }

        return answer;
    }
}