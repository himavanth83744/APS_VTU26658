import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : dir) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];

                if (r >= 0 && c >= 0 && r < rows && c < cols && dist[r][c] == -1) {
                    dist[r][c] = dist[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        return dist;
    }
}