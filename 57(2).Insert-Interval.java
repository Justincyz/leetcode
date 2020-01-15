/*57. Insert Interval
链接：https://leetcode.com/problems/insert-interval/
Hard: 
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

/*解题思路
看起来是hard的题目，实际上还是比较简单的。主要是newinterval只有一个，
所以只需要带着这个newInterval去intervals找插入位置就可以了。
O(n)时间，beats 99%
*/


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        //如果长度为0，返回newInterval的长度
        if(intervals.length == 0){
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }
        //l1带着走，l2是我们融合前/后的interval
        int[] l1 = intervals[0], l2 = newInterval;
        int p1 =0;
        
        while(p1 < intervals.length){
        	//如果有交集，则融合进l2
            if(l1[1] >= l2[0] && l1[0] <= l2[1]){
                l2[0] = Math.min(l1[0], l2[0]);
                l2[1] = Math.max(l1[1], l2[1]);
                p1++;
                if(p1 == intervals.length)  break;
                l1 = intervals[p1];
            }//在目前的位置下，未能遇到l2，则将原区间加入到结果中
            else if(l1[1] < l2[0]){
                list.add(l1);
                p1++;
                //一定要检查是否越界
                if(p1 == intervals.length)  break;   
                l1 = intervals[p1];
            }//已经融合完成，接下来intervals中的区间无法与l2进行融合了，因为全部大于l2，直接Break
            else if(l1[0] > l2[1]){

                break;
            }
        }
        //添加融合区间
        list.add(l2);
        //如果有剩余的区间，则都加入结果中
        while(p1 < intervals.length){
            list.add(intervals[p1++]);
        }
        
        //非得变化成array的形式
        int[][] res = new int[list.size()][2];
        for(int i=0; i< list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}