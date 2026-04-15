import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.putIfAbsent(email, email);
                nameMap.put(email, name);
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
            List<String> temp = new ArrayList<>();
            temp.add(nameMap.get(root));
            temp.addAll(groups.get(root));
            result.add(temp);
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
        String pa = find(parent, a);
        String pb = find(parent, b);

        if (!pa.equals(pb)) {
            parent.put(pb, pa);
        }
    }
}