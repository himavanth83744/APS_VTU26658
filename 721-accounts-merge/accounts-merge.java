import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.putIfAbsent(email, email);
                owner.put(email, name);
                union(parent, account.get(1), email);
            }
        }

        Map<String, TreeSet<String>> groups = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(parent, email);
            groups.putIfAbsent(root, new TreeSet<>());
            groups.get(root).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (String root : groups.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(owner.get(root));
            merged.addAll(groups.get(root));
            result.add(merged);
        }

        return result;
    }

    private String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(Map<String, String> parent, String a, String b) {
        String rootA = find(parent, a);
        String rootB = find(parent, b);

        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
        }
    }
}