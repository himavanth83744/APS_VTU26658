import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) red[e[0]].add(e[1]);
        for (int[] e : blueEdges) blue[e[0]].add(e[1]);

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        boolean[][] visited = new boolean[n][2];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;

        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];

                if (ans[node] == -1) ans[node] = dist;

                List<Integer>[] nextGraph = (color == 0) ? blue : red;
                int nextColor = 1 - color;

                for (int next : nextGraph[node]) {
                    if (!visited[next][nextColor]) {
                        visited[next][nextColor] = true;
                        queue.offer(new int[]{next, nextColor});
                    }
                }
            }

            dist++;
        }

        return ans;
    }
}