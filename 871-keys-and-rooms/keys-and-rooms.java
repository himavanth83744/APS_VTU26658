import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);

        for (boolean room : visited) {
            if (!room) return false;
        }

        return true;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, int room) {
        if (visited[room]) return;

        visited[room] = true;

        for (int key : rooms.get(room)) {
            dfs(rooms, visited, key);
        }
    }
}