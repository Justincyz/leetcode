/*334. Increasing Triplet Subsequence
链接：https://leetcode.com/problems/increasing-triplet-subsequence/
Medium: 
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
*/

/*解题思路
这道题目让我们判断数组中是否有三个整数subsequence是呈递增态势的。
首先这道题目和longest increasing subsequence的最优解的做法是一样的，
只是这里我们只需要两个变量而已。

首先设置最小的和次小的两个数字为整形最大值，因为这样我们才方便每一次取较小
的值，此时我们遍历数组，直接举例子。
[5,8,2,5,6]

我们首先判断第一个数字是不是大于second smallest(ss)元素。如果是的话，
说明在当前数字之前我们已经找到了两个比当前数小的数。
当然 5 < MAX_VALUE,此时我们判断 5 < small, 所以我们先把5赋值给small.
此时我们再看下一位的8，我们发现8是小于ss (此时值为MAX)，且大于small=5的。
所以第二小的值就变成了8.
重点来了，当我们遇到2的时候怎么办?是应该把最小和次小的值恢复成MAX的大小？
还是应该替换最小的值，同时赋值secsmall = small呢？
答案是都不是,而是只要让最小的值被替换掉就可以了。
在我们遇到2 之前，我们可以凑成[5,8]，此时我们也不知道接下来能不能遇到
大于8的数，但是我们知道，如果可以的话，那这个数x肯定大于8，且跟第一位的
5关系只是在于顺序问题，因为我们赋值的时候就确定第二位大于第一位的值。
所以在遇到2的时候，我们把5替换成2。
此时我们还是知道目前为止可以构成最长两位的递增，但是我们把递增的阈值从5降为2了。
(从上帝视角我们知道结果为[2,5,6])，所以降低阈值只有好处没有坏处。
当下一位遇到5时，我们又把8替换成5，降低第二位的阈值。最后遍历到6的
时候发现前面已经可以组成两位连续递增的值，且最大的值小于6。


*/
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) return false;
        int n = nums.length;
        int small = Integer.MAX_VALUE, secsmall = Integer.MAX_VALUE;
        
        for(int num: nums){
            if(num > secsmall) return true;
            else{
                if(num < secsmall && num > small){
                    secsmall = num;
                }else if(num < small){
                    
                    small = num;
                }
            }
        }
        return false;
    }
}


class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) return false;
        int n = nums.length;
        int[] lessThan = new int[n], greaterThan = new int[n];
        lessThan[0] = nums[0]; greaterThan[n-1] = nums[n-1];
        for(int i=1; i<n; i++) lessThan[i] = Math.min(nums[i], lessThan[i-1]);
        for(int i=n-2; i>=0; i--) greaterThan[i] = Math.max(nums[i], greaterThan[i+1]);
        
        for(int i=1; i<n-1; i++){

            if(greaterThan[i+1] > nums[i] && lessThan[i-1] < nums[i]) return true;
        }
        return false;
    }
}