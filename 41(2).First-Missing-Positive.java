/*41. First Missing Positive
Hard
链接: https://leetcode.com/problems/first-missing-positive/
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.


*/

/*解题思路
这道题目最大的障碍就是在O(n)时间和O(1)空间下找到答案。
我们先来看一下我一开始写的一个O(N)时间O(N)空间的解
直接用一个hashset出差呢所有的元素，然后从1开始找在hashset中
不存在的元素，如果可以找到的话就继续，否则直接output结果
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i: nums) set.add(i);
        int key=1; 
        while(set.contains(key)){
            key++;
        }
        return key;
    }
}
/*
这个就是O(N)时间，O(1)空间的解法
假设数组长度为n，其实要找到第一个数组中不存在的元素，且大于0的，范围
最多在1-n之间，为啥呢？因为我们根本不用考虑小于等于0的数字，且也不需要
考虑所有大于n的数字，因为最坏的情况就是所有的数字都在1到n-1之间，那么
我们要找的就是n, 这是最极端的情况，一般情况下在1到n之间一定留有缝隙，
只需要将1-(n-1)之间的元素放在正确的数组位置上就可以了。然后再遍历
一次数组的每一位，碰到第一个元素和位置不相符，(比如说第3位上不是3)
的情况，说明这一位是最小的，且是空出来的。就是结果了
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length, i=0;
        while(i < n){
            if(nums[i] <=0 || nums[i]>n || nums[i] == i+1) i++;
            //如果当前位置上的元素处于1到n-1的区间中，且没在自己正确的位置上，则交换，有duplication也无所谓
            else if(nums[nums[i]-1] != nums[i]) swap(nums, i, nums[i]-1);
            else i++;
        }
       
        int pt = 1; 
        while(pt <= n && pt == nums[pt-1]) pt++;
        return pt;
        
    }
    
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}