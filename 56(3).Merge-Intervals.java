/*56. Merge Intervals
Medium
链接: https://leetcode.com/problems/merge-intervals/

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/

/*解题思路
题目要求我们对所有重合的时间进行融合，然后输出所有融合过的时间段。我们要做的就是
先按照起始时间对所有的intervals 排序。假设a在b的前面，如果a的结束时间比b的起始时间还晚，
那么说明a和b可以融合, 如果a的结束时间比b的起始时间早，说明a和b不能融合，且a和b中间
不会出现一个c，使得 c的起始时间早于a的结束时间。所以我们把a存入结果中。如果b的起始时间
早于a的结束时间，说明两个时间段可以融合。且融合之后的结束时间取两个时间段中较大的结束时间
的值。

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[][]{};
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        
        int[] prev = intervals[0];
        
        for(int i=1; i< intervals.length; i++){
            int[] cur = intervals[i];
            if(prev[1] >= cur[0]){
                prev[1] = Math.max(prev[1], cur[1]);
            }else{
                res.add(prev);
                prev = cur;
            }
        }
        res.add(prev);
        
        return res.toArray(new int[res.size()][]);
    }
}