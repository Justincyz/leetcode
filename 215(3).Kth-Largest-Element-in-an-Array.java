/*215. Kth Largest Element in an Array
Medium
链接: https://leetcode.com/problems/kth-largest-element-in-an-array/
Find the kth largest element in an unsorted array. Note that it is 
the kth largest element in the sorted order, not the kth distinct 
element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.


*/

/*解题思路
题目让我们找到第k大的数，最简单的办法要不就是sort一下，要不就是用priorityQueue
来做。
这道题目其实想考的是用quicksort的办法来做。每一次对数组进行partition。
直到找到第k个大小的数。这样的时间复杂度理论上来说是为O(n)+O(n/2)+O(n/4)...1 < 2xO(n)。
当然最坏的partition的情况还是O(n)+O(n-1)+O(n-2)+...+O(1)变成O(n^2)

*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums==null||nums.length==0) return 0;

        int lo = 0, hi = nums.length-1;
        while (lo<hi) {
            
            int where = partitian(nums, lo, hi);
            int pos = nums.length-where;
            if (k>pos) {
                hi = where-1;
            } else if (k==pos) return nums[where];
            else lo=where+1;
        }
        return nums[lo];
    }
    
    int partitian(int[] nums, int lo, int hi) {
        int st = lo, pivot = nums[hi];
        while (lo<=hi) {
            if (nums[lo]<pivot ) {
                swap(nums, st, lo);
                st++;
            }
            lo++;
        }
        swap(nums, st, hi);
        return st;
    }
    
        void swap(int[] nums, int lo, int hi) {
           
        int temp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = temp;
            
    }
}

//简单做法
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i: nums){
            pq.add(i);
            if(pq.size() > k){
                pq.poll();
            } 
        }
        return pq.peek();
    }
}