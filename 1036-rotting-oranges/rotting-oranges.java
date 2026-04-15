import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Find rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0;

        int minutes = 0;
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean changed = false;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int[] d : dir) {
                    int r = curr[0] + d[0];
                    int c = curr[1] + d[1];

                    if (r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        queue.offer(new int[]{r, c});
                        changed = true;
                    }
                }
            }

            if (changed) minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}