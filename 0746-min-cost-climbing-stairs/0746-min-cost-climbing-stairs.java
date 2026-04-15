class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int first = 0, second = 0;

        for (int i = 2; i <= cost.length; i++) {
            int third = Math.min(second + cost[i - 1], first + cost[i - 2]);
            first = second;
            second = third;
        }

        return second;
    }
}