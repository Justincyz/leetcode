/*581. Shortest Unsorted Continuous Subarray
链接：https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
Easy: 
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/

/*解题思路 1
这道题让我们在一个无序数组中，找到一段连续的数组，如果将这一段数组
排序好的话那么整个数组就是排序好的数组。那么这一段数组的左边都是排序
好的数组且每一位都小于中间数组，右边同理且都大于中间数组。
最简单的办法就是创建一个新的数组，将老数组的元素都拷贝进来然后进行排序。
再用一个for loop来比较新，老数组中每一个元素是否相同。我们找到第一个不相同
和最后一个不相同的元素，这种中间的所有元素都是需要排序的，由此我们也可以
知道这一段数组长度有多长。

*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] nums2 = new int[nums.length];
        for(int i=0; i<nums.length; i++) nums2[i] = nums[i];
        Arrays.sort(nums2);
        int l = -1, r = -1;
        for(int i=0; i< nums.length; i++){
            if(nums[i] == nums2[i]) continue;
            if(l == -1) l =i;
            else r = i;
        }
        if(l == -1) return 0;
        return r-l+1;
    }
}

/*解题思路 2
这个办法是O(n) time + O(1) space的做法，很聪明。实际上我们对于
我们要找的数组段落k来说，肯定是因为K的两端是有错位的现象，比如说
末端的元素小于这个元素之前的某一个元素，开头端的元素大于了某一个
在这之后的元素，才会产生错位。那么我们使用四个变量。S代表这个数组
段的头部位置，e代表这个数组段的尾部位置。同时维持两个变量一个是min
代表了从后往前最小的值(划重点)，max代表了从前往后最大的值(划重点)。
因为如果是全排序好的话，比如说[1,2,3,4],那么不会出现 max > nums[i]
或者 min < nums[n-1-i]的情况，因为每一次的更新都会使 max == nums[i]
和 min == nums[n-1-i]。只有产生错位的情况下才会进入两个if statement.
此时我们再更新相对应的 s 和 e.
如果下次不理解的话只要画个图就知道了，

*/

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length, s = -1, e = -2;
        int min = nums[n-1], max = nums[0];
        for(int i=1; i<n; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n-1-i]);
            if(max > nums[i]) e = i;
            if(min < nums[n-1-i]) s = n-1-i;
        }
        return e - s +1;
    }
}



