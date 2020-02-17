/*287. Find the Duplicate Number
链接：https://leetcode.com/problems/find-the-duplicate-number/
Medium: 
Given an array nums containing n + 1 integers where each integer is 
between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

/*解题思路
我们有一个长度为n+1的数组，数组里面每一个元素的范围都是1-n之间。题目
让我们找到某一个数重复次数大于一次，保证这样的数字只有一个。

这道题目我会从面试的角度一步步的来接近最优解法。
首先我们不要在意题目给出的限制条件. 
1: 不能更改array. 
2: 只能用O(1) space
最简单的解法，利用一个新的数组计算每一个元素出现的次数。如果某一位的值
大于1的话，说明这个就是我们要找的元素。这样的话时间复杂度是O(N),但是空间
复杂度就是O(N)，而不是O(1)了。
*/

class Solution {
    public int findDuplicate(int[] nums) {
        int[] list = new int[nums.length];
        for(int num: nums){
            list[num-1]++;
        }
        for(int i =0; i<list.length; i++){
            if(list[i] >1) return i+1;
        }
        return -1;
    }
}

/*
这是一种用O(1)空间，但是用了O(nlogn)时间的解法。
在区间 [1, n] 中搜索，首先求出中点 mid，然后遍历整个数组，统计所有小于等于 mid 的数的个数，
如果个数小于等于 mid，则说明重复值在 [mid+1, n] 之间，反之，
重复值应在 [1, mid-1] 之间，然后依次类推，直到搜索完成，
此时的 high 就是我们要求的重复值，
*/

class Solution {
    public int findDuplicate(int[] nums) {
        int low =1, high = nums.length;
        
        while(low < high){
            int mid = low+(high-low)/2;
            int count =0;
            for(int i: nums){
                if(i <= mid) count++;
            }
            
            if(count > mid) high = mid;
            else low = mid+1;
        }
        return high;
    }

}


/*
这是最快的解法:
核心思想快慢指针在之前的题目 Linked List Cycle II 
中就有应用，这里应用的更加巧妙一些，由于题目限定了区间 [1,n]，所以可以
巧妙的利用坐标和数值之间相互转换，而由于重复数字的存在，那么一定会形成
环，用快慢指针可以找到环并确定环的起始位置，确实是太巧妙了！


*/

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        while(true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow) break;
        }
        
        fast =0;
        while(true){
            fast = nums[fast];
            slow = nums[slow];
            if(fast == slow) break;
        }
        return fast;
        
    }
}