import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        int[] itemIn = new int[n];
        int[] groupIn = new int[m];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIn[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIn[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topo(itemGraph, itemIn, n);
        List<Integer> groupOrder = topo(groupGraph, groupIn, m);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int item : itemOrder) {
            map.putIfAbsent(group[item], new ArrayList<>());
            map.get(group[item]).add(item);
        }

        List<Integer> ans = new ArrayList<>();

        for (int g : groupOrder) {
            ans.addAll(map.getOrDefault(g, new ArrayList<>()));
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topo(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return order.size() == size ? order : new ArrayList<>();
    }
}