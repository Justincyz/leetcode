/*924. Minimize Malware Spread
Hard: Union Find, DFS
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/

/*解题思路
因为我们要找连续的组合，所以 nums里面的每个值都不会重复出现。
需要的就是一个hashSet, 先把每个数字存在hashset中。然后遍历
一次nums, 对每个值都往上和往下去找值，如果找到了那就count++并且
remove在hashset中的存在。这样下一次在hashset中没有的值就可以直接跳过
*/



class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);

        int res = 0;
        for(int num: nums){
            if(!set.contains(num)) continue;
            int up = num+1, down = num-1, max=1;
            set.remove(num);
            while(!set.isEmpty() &&(set.contains(up) || set.contains(down))){
                if(set.contains(up)){
                    set.remove(up);
                    max++;
                    up++;   
                }
                if(set.contains(down)){
                    set.remove(down);
                    max++;
                    down--;
                }
            }
            res = Math.max(res, max);
        }
        return res;
    }
}


